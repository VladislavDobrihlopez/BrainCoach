package com.voitov.braincoach.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.voitov.braincoach.databinding.FragmentGameplayBinding
import com.voitov.braincoach.domain.entity.GameResults

class GameplayFragment : Fragment() {
    private val level by lazy {
        val args = GameplayFragmentArgs.fromBundle(requireArguments())
        args.level
    }
    private val viewModelFactory by lazy {
        GameplayViewModelFactory(level, requireActivity().application)
    }
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        ).get(
            GameplayViewModel::class.java
        )
    }
    private val textViewOptions by lazy {
        ArrayList<TextView>(level.levelSettings.countOfOptions)
    }
    private var _binding: FragmentGameplayBinding? = null
    private val binding: FragmentGameplayBinding
        get() = _binding ?: throw RuntimeException("FragmentGameplayBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(textViewOptions) {
            with(binding) {
                add(textViewFirstAnswer)
                add(textViewSecondAnswer)
                add(textViewThirdAnswer)
                add(textViewFourthAnswer)
                add(textViewFifthAnswer)
                add(textViewSixthAnswer)
            }
        }

        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        for (textView in textViewOptions) {
            textView.setOnClickListener {
                viewModel.chooseAnswer(textView.text.toString().toInt())
            }
        }
    }

    private fun setupObservers() {
        viewModel.question.observe(viewLifecycleOwner, Observer {
            with(binding) {
                textViewVisibleNumber.text = it.visiblePart.toString()
                textViewExpressionResult.text = it.expressionResult.toString()

                for ((index, _) in textViewOptions.withIndex()) {
                    textViewOptions[index].text = it.options[index].toString()
                }
            }
        })

        viewModel.progressValueIsEnough.observe(viewLifecycleOwner, Observer {
            binding.progressBarPercentageOfRightAnswers.progressTintList =
                ColorStateList.valueOf(getColorByState(it))
        })

        viewModel.progressValue.observe(viewLifecycleOwner, Observer {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.progressBarPercentageOfRightAnswers.setProgress(it, true)
            } else {
                binding.progressBarPercentageOfRightAnswers.progress = it
            }
        })

        viewModel.minPercentage.observe(viewLifecycleOwner, Observer {
            binding.progressBarPercentageOfRightAnswers.secondaryProgress = it
        })

        viewModel.progressAnswersIsEnough.observe(viewLifecycleOwner, Observer {
            binding.textViewRightAnswersCounter.setTextColor(
                getColorByState(it)
            )
        })

        viewModel.progressAnswers.observe(viewLifecycleOwner, Observer {
            binding.textViewRightAnswersCounter.text = it.toString()
        })

        viewModel.formattedTime.observe(viewLifecycleOwner, Observer {
            binding.textViewTimer.text = it
        })

        viewModel.gameResultsOnFinishingGame.observe(viewLifecycleOwner, Observer {
            launchGameFinishedFragment(it)
        })
    }

    private fun getColorByState(enoughValue: Boolean): Int {
        return if (enoughValue) {
            ContextCompat.getColor(
                requireContext(),
                android.R.color.holo_green_light
            )
        } else {
            ContextCompat.getColor(
                requireContext(),
                android.R.color.holo_red_light
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(gameResults: GameResults) {
        findNavController().navigate(
            GameplayFragmentDirections.actionGameplayFragmentToGameFinishedFragment(
                gameResults
            )
        )
    }
}

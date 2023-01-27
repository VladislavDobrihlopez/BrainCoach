package com.voitov.braincoach.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.voitov.braincoach.R
import com.voitov.braincoach.databinding.FragmentGameFinishedBinding
import com.voitov.braincoach.domain.entity.GameResults

class GameFinishedFragment : Fragment() {
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is null")
    private lateinit var gameResults: GameResults

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    private fun parseParams() {
        requireArguments().getParcelable<GameResults>(KEY_GAME_RESULTS)?.let {
            gameResults = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        bindViews()
    }

    private fun setupListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    tryAgain()
                }
            })
        binding.buttonRetryGame.setOnClickListener {
            tryAgain()
        }
    }

    private fun bindViews() {
        with(binding) {
            with(requireContext()) {
                if (gameResults.isWinner) {
                    Glide.with(this)
                        .load(ContextCompat.getDrawable(this, R.drawable.ic_smile))
                        .into(imageViewEmojiResult)
                } else {
                    Glide.with(this)
                        .load(ContextCompat.getDrawable(this, R.drawable.ic_sad))
                        .into(imageViewEmojiResult)
                }

                textViewCurrentPercentageOfRightAnswers.text = String.format(
                    getString(R.string.current_percentage_of_right_answers),
                    calculatePercentageOfRightAnswers()
                )
                textViewCurrentRightAnswers.text = String.format(
                    getString(R.string.current_right_answers),
                    gameResults.countOfRightAnswers
                )
                textViewRequiredPercentageOfRightAnswers.text = String.format(
                    getString(R.string.required_percentage_of_right_answers),
                    gameResults.levelSettings.minPercentageOfRightAnswers
                )
                textViewRequiredRightAnswers.text = String.format(
                    getString(R.string.required_right_answers),
                    gameResults.levelSettings.minCountOfRightAnswers
                )
            }
        }
    }

    private fun calculatePercentageOfRightAnswers(): Int {
        return if (gameResults.countOfAnswers == 0) {
            0
        } else {
            (gameResults.countOfRightAnswers * 100) / gameResults.countOfAnswers
        }
    }

    private fun tryAgain() {
        requireActivity().supportFragmentManager.popBackStack(
            GameplayFragment.FRAGMENT_NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_GAME_RESULTS = "game_results"
        fun newInstance(gameResults: GameResults): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULTS, gameResults)
                }
            }
        }
    }
}
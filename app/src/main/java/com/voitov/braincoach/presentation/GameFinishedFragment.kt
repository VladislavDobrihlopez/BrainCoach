package com.voitov.braincoach.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.voitov.braincoach.R
import com.voitov.braincoach.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is null")
    private val gameResults by lazy {
        GameFinishedFragmentArgs.fromBundle(requireArguments()).gameResults
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
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
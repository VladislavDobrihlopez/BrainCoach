package com.voitov.braincoach.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.gameResultsOnFinishingGame.observe(viewLifecycleOwner, Observer {
            launchGameFinishedFragment(it)
        })
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

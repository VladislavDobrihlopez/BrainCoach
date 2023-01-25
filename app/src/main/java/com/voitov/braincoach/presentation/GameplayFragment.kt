package com.voitov.braincoach.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.voitov.braincoach.R
import com.voitov.braincoach.databinding.FragmentGameplayBinding

class GameplayFragment : Fragment() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
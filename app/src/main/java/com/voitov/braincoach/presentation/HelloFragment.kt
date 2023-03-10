package com.voitov.braincoach.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.voitov.braincoach.R
import com.voitov.braincoach.databinding.FragmentHelloBinding

class HelloFragment : Fragment() {
    private var _binding: FragmentHelloBinding? = null
    private val binding: FragmentHelloBinding
        get() = _binding ?: throw RuntimeException("FragmentHelloBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelloBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonContinue.setOnClickListener {
            launchChoosingLevelFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchChoosingLevelFragment() {
        findNavController().navigate(R.id.action_helloFragment_to_choosingLevelFragment)
    }
}
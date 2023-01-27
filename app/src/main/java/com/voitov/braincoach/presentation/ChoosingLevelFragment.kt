package com.voitov.braincoach.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.voitov.braincoach.databinding.FragmentChooseLevelBinding
import com.voitov.braincoach.domain.entity.Level

class ChoosingLevelFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(ChoosingLevelViewModel::class.java)
    }
    private lateinit var adapter: LevelsAdapter
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("ChoosingLevelFragment is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = LevelsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAllLevels()
        binding.recyclerViewLevels.adapter = adapter
        viewModel.levels.observe(viewLifecycleOwner, Observer {
            adapter.levels = it
        })
        adapter.onButtonClick = {
            launchGameplayFragment(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameplayFragment(level: Level) {
        findNavController().navigate(
            ChoosingLevelFragmentDirections.actionChoosingLevelFragmentToGameplayFragment(
                level
            )
        )
    }
}
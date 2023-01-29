package com.voitov.braincoach.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voitov.braincoach.R
import com.voitov.braincoach.databinding.LevelItemBinding
import com.voitov.braincoach.domain.entity.Level

class LevelsAdapter : RecyclerView.Adapter<LevelsAdapter.LevelsViewHolder>() {
    var levels = listOf<Level>()
        set(value) {
            val callback = LevelsDiffCallback(field, value)
            val diffResults = DiffUtil.calculateDiff(callback)
            diffResults.dispatchUpdatesTo(this)
            field = value
        }
    var onButtonClick: ((Level) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsViewHolder {
        val layout = when (viewType) {
            DEFAULT_TYPE -> R.layout.level_item
            else -> throw RuntimeException("Unknown viewType = $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return LevelsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LevelsViewHolder, position: Int) {
        val level = levels[position]

        val binding = holder.binding

        when (binding) {
            is LevelItemBinding -> {
                binding.level = level
                if (level.tapped) {
                    binding.linearLayoutExtraInformation.visibility = View.VISIBLE
                    binding.buttonLetsGetIsStarted.setOnClickListener {
                        onButtonClick?.invoke(level)
                    }
                } else {
                    binding.linearLayoutExtraInformation.visibility = View.GONE
                }
            }
        }
        with(binding) {
            root.setOnClickListener {
                level.tap()
                notifyItemChanged(holder.adapterPosition)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return DEFAULT_TYPE
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    class LevelsViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val DEFAULT_TYPE = 100
    }
}
package com.voitov.braincoach.presentation

import androidx.recyclerview.widget.DiffUtil
import com.voitov.braincoach.domain.entity.Level

class LevelsDiffCallback(
    private val oldItems: List<Level>,
    private val newItems: List<Level>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
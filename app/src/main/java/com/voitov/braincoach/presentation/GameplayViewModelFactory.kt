package com.voitov.braincoach.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voitov.braincoach.domain.entity.Level

class GameplayViewModelFactory(
    private val level: Level,
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameplayViewModel::class.java)) {
            return GameplayViewModel(level, application) as T
        }
        throw RuntimeException("Failed attempt to create $modelClass")
    }
}
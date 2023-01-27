package com.voitov.braincoach.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voitov.braincoach.data.GameRepositoryImpl
import com.voitov.braincoach.domain.entity.Level
import com.voitov.braincoach.domain.usecases.GetLevelsUseCase

class ChoosingLevelViewModel : ViewModel() {
    private val repository = GameRepositoryImpl
    private val getLevelsUseCase = GetLevelsUseCase(repository)
    private var _levels = MutableLiveData<List<Level>>()
    val levels: LiveData<List<Level>>
        get() = _levels

    fun getAllLevels() {
        _levels.value = getLevelsUseCase.invoke()
    }
}
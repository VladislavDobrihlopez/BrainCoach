package com.voitov.braincoach.domain.usecases

import com.voitov.braincoach.domain.entity.Level
import com.voitov.braincoach.domain.repository.GameRepository

class GetLevelsUseCase(
    private val repository: GameRepository
) {
    fun invoke(): List<Level> {
        return repository.getLevels()
    }
}
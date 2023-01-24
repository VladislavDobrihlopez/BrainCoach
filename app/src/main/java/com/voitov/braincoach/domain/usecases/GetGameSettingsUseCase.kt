package com.voitov.braincoach.domain.usecases

import com.voitov.braincoach.domain.entity.GameSettings
import com.voitov.braincoach.domain.entity.Level
import com.voitov.braincoach.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository,
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettingsUseCase(level)
    }
}
package com.voitov.braincoach.domain.repository

import com.voitov.braincoach.domain.entity.GameSettings
import com.voitov.braincoach.domain.entity.Level
import com.voitov.braincoach.domain.entity.Question

interface GameRepository {
    fun generateQuestionUseCase(gameSettings: GameSettings): Question
    fun getGameSettingsUseCase(level: Level): GameSettings
    fun getLevels(): List<Level>
}
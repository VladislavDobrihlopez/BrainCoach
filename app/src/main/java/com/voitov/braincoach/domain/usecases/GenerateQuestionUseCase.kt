package com.voitov.braincoach.domain.usecases

import com.voitov.braincoach.domain.entity.GameSettings
import com.voitov.braincoach.domain.entity.Question
import com.voitov.braincoach.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository,
) {
    operator fun invoke(gameSettings: GameSettings): Question {
        return repository.generateQuestionUseCase(gameSettings)
    }
}
package com.voitov.braincoach.domain.usecases

import com.voitov.braincoach.domain.entity.LevelSettings
import com.voitov.braincoach.domain.entity.Question
import com.voitov.braincoach.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository,
) {
    operator fun invoke(levelSettings: LevelSettings): Question {
        return repository.generateQuestionUseCase(levelSettings)
    }
}
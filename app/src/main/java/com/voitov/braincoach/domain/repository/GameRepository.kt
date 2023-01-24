package com.voitov.braincoach.domain.repository

import com.voitov.braincoach.domain.entity.LevelSettings
import com.voitov.braincoach.domain.entity.Level
import com.voitov.braincoach.domain.entity.Question

interface GameRepository {
    fun generateQuestionUseCase(levelSettings: LevelSettings): Question
    fun getLevels(): List<Level>
}
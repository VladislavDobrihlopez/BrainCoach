package com.voitov.braincoach.domain.entity

data class GameResults(
    val isWinner: Boolean,
    val countOfRightAnswers: Int,
    val countOfAnswers: Int,
    val levelSettings: LevelSettings,
)
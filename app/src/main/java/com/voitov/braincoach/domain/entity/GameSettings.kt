package com.voitov.braincoach.domain.entity

data class GameSettings(
    val maxExpressionValue: Int,
    val minExpressionValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentageOfRightAnswers: Int,
    val gameTimeInSeconds: Int,
    val countOfOptions: Int,
)
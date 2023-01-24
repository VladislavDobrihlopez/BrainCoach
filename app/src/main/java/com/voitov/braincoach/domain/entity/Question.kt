package com.voitov.braincoach.domain.entity

data class Question(
    val expressionResult: Int,
    val visiblePart: Int,
    val options: List<Int>,
)
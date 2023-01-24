package com.voitov.braincoach.domain.entity

data class Level(
    val id: Int,
    val name: String,
    val levelSettings: LevelSettings,
)
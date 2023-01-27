package com.voitov.braincoach.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResults(
    val isWinner: Boolean,
    val countOfRightAnswers: Int,
    val countOfAnswers: Int,
    val levelSettings: LevelSettings,
) : Parcelable

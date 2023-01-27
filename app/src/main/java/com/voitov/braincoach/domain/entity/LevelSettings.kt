package com.voitov.braincoach.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LevelSettings(
    val maxExpressionValue: Int,
    val minExpressionValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentageOfRightAnswers: Int,
    val gameTimeInSeconds: Int,
    val countOfOptions: Int,
) : Parcelable
package com.voitov.braincoach.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Level(
    val id: Int,
    val name: String,
    val levelSettings: LevelSettings,
    var tapped: Boolean = false,
) : Parcelable {
    fun tap() {
        tapped = !tapped
    }
}
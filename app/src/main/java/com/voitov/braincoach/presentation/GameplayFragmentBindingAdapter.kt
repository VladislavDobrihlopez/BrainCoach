package com.voitov.braincoach.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("progressPercentageValue")
fun bindProgressPercentageValue(progressBar: ProgressBar, value: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        progressBar.setProgress(value, true)
    } else {
        progressBar.progress = value
    }
}

@BindingAdapter("progressPercentageColor")
fun bindProgressPercentageColor(progressBar: ProgressBar, isEnough: Boolean) {
    progressBar.progressTintList =
        ColorStateList.valueOf(getColorByState(progressBar.context, isEnough))
}

@BindingAdapter("secondaryProgressMinPercentageAllowed")
fun bindSecondaryProgressValue(progressBar: ProgressBar, value: Int) {
    progressBar.secondaryProgress = value
}

@BindingAdapter("progressAnswersColor")
fun bindProgressAnswersColor(textView: TextView, isEnough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, isEnough))
}

private fun getColorByState(context: Context, enoughValue: Boolean): Int {
    return if (enoughValue) {
        ContextCompat.getColor(
            context,
            android.R.color.holo_green_light
        )
    } else {
        ContextCompat.getColor(
            context,
            android.R.color.holo_red_light
        )
    }
}

@BindingAdapter("onOptionClick")
fun bindOnOptionClick(textView: TextView, onClickListener: OnOptionClickListener) {
    textView.setOnClickListener { onClickListener.onOptionClick(textView.text.toString().toInt()) }
}

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}
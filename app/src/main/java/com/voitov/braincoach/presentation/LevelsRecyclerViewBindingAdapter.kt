package com.voitov.braincoach.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.voitov.braincoach.R

@BindingAdapter("maxExpressionValue")
fun bindInfoMaxExpressionValue(textView: TextView, value: Int) {
    textView.text = String.format(textView.context.getString(R.string.maxExpressionValue), value)
}

@BindingAdapter("minExpressionValue")
fun bindInfoMinExpressionValue(textView: TextView, value: Int) {
    textView.text = String.format(textView.context.getString(R.string.minExpressionValue), value)
}

@BindingAdapter("minCountOfRightAnswers")
fun bindInfoMinCountOfRightAnswers(textView: TextView, value: Int) {
    textView.text =
        String.format(textView.context.getString(R.string.minCountOfRightAnswers), value)
}

@BindingAdapter("minPercentageOfRightAnswers")
fun bindInfoMinPercentageOfRightAnswers(textView: TextView, value: Int) {
    textView.text =
        String.format(textView.context.getString(R.string.minPercentageOfRightAnswers), value)
}

@BindingAdapter("gameTimeInSeconds")
fun bindInfoGameTimeInSeconds(textView: TextView, value: Int) {
    textView.text = String.format(textView.context.getString(R.string.gameTimeInSeconds), value)
}

@BindingAdapter("countOfOptions")
fun bindInfoCountOfOptions(textView: TextView, value: Int) {
    textView.text = String.format(textView.context.getString(R.string.countOfOptions), value)
}
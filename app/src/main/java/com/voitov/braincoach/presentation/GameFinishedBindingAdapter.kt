package com.voitov.braincoach.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.voitov.braincoach.R
import com.voitov.braincoach.domain.entity.GameResults

@BindingAdapter("smile")
fun bindSmile(imageView: ImageView, isWinner: Boolean) {
    val context = imageView.context
    if (isWinner) {
        Glide.with(context)
            .load(ContextCompat.getDrawable(context, R.drawable.ic_smile))
            .into(imageView)
    } else {
        Glide.with(context)
            .load(ContextCompat.getDrawable(context, R.drawable.ic_sad))
            .into(imageView)
    }
}

@BindingAdapter("minCountOfRightAnswers")
fun bindMinCountOfRightAnswers(textView: TextView, minCountOfRightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_right_answers),
        minCountOfRightAnswers
    )
}

@BindingAdapter("currentRightAnswers")
fun bindCurrentRightAnswers(textView: TextView, currentRightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.current_right_answers),
        currentRightAnswers
    )
}

@BindingAdapter("minPercentageOfRightAnswers")
fun bindMinPercentageOfRightAnswers(textView: TextView, minPercentageOfRightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.minPercentageOfRightAnswers),
        minPercentageOfRightAnswers
    )
}

@BindingAdapter("currentPercentageOfRightAnswers")
fun bindCurrentPercentageOfRightAnswers(textView: TextView, gameResults: GameResults) {
    textView.text = String.format(
        textView.context.getString(R.string.current_percentage_of_right_answers),
        calculatePercentageOfRightAnswers(gameResults)
    )
}

private fun calculatePercentageOfRightAnswers(gameResults: GameResults): Int {
    return if (gameResults.countOfAnswers == 0) {
        0
    } else {
        (gameResults.countOfRightAnswers * 100) / gameResults.countOfAnswers
    }
}
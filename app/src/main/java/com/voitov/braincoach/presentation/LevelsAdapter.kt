package com.voitov.braincoach.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voitov.braincoach.R
import com.voitov.braincoach.domain.entity.Level

class LevelsAdapter : RecyclerView.Adapter<LevelsAdapter.LevelsViewHolder>() {
    var levels = listOf<Level>()
        set(value) {
            val callback = LevelsDiffCallback(field, value)
            val diffResults = DiffUtil.calculateDiff(callback)
            diffResults.dispatchUpdatesTo(this)
            field = value
        }
    var onButtonClick: ((Level) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.level_item, parent, false)
        return LevelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelsViewHolder, position: Int) {
        val level = levels[position]

        with(holder) {
            textViewLevelName.text = level.name
            if (level.tapped) {
                linearLayoutExtraInformation.visibility = View.VISIBLE
                with(level.levelSettings) {
                    with(holder.itemView.context) {
                        textViewMaxExpressionValue.text = getString(
                            R.string.maxExpressionValue,
                            maxExpressionValue
                        )
                        textViewMinExpressionValue.text = getString(
                            R.string.minExpressionValue,
                            minExpressionValue
                        )
                        textViewMinCountOfRightAnswers.text = getString(
                            R.string.minCountOfRightAnswers,
                            minCountOfRightAnswers
                        )
                        textViewMinPercentageOfRightAnswers.text = getString(
                            R.string.minPercentageOfRightAnswers,
                            minPercentageOfRightAnswers
                        )
                        textViewGameTimeInSeconds.text = getString(
                            R.string.gameTimeInSeconds,
                            gameTimeInSeconds
                        )
                        textViewCountOfOptions.text = getString(
                            R.string.countOfOptions,
                            countOfOptions
                        )
                        buttonLetsGetIsStarted.setOnClickListener {
                            onButtonClick?.invoke(level)
                        }
                    }
                }
            } else {
                linearLayoutExtraInformation.visibility = View.GONE
            }
            itemView.setOnClickListener {
                level.tap()
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    class LevelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLevelName = itemView.findViewById<TextView>(R.id.textViewLevelName)
        val linearLayoutExtraInformation =
            itemView.findViewById<LinearLayout>(R.id.linearLayoutExtraInformation)
        val textViewMaxExpressionValue =
            itemView.findViewById<TextView>(R.id.textViewMaxExpressionValue)
        val textViewMinExpressionValue =
            itemView.findViewById<TextView>(R.id.textViewMinExpressionValue)
        val textViewMinCountOfRightAnswers =
            itemView.findViewById<TextView>(R.id.textViewMinCountOfRightAnswers)
        val textViewMinPercentageOfRightAnswers =
            itemView.findViewById<TextView>(R.id.textViewMinPercentageOfRightAnswers)
        val textViewGameTimeInSeconds =
            itemView.findViewById<TextView>(R.id.textViewGameTimeInSeconds)
        val textViewCountOfOptions =
            itemView.findViewById<TextView>(R.id.textViewCountOfOptions)
        val buttonLetsGetIsStarted =
            itemView.findViewById<TextView>(R.id.buttonLetsGetIsStarted)
    }
}
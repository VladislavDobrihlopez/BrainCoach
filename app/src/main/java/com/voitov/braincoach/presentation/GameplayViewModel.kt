package com.voitov.braincoach.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voitov.braincoach.R
import com.voitov.braincoach.data.GameRepositoryImpl
import com.voitov.braincoach.domain.entity.GameResults
import com.voitov.braincoach.domain.entity.Level
import com.voitov.braincoach.domain.entity.Question
import com.voitov.braincoach.domain.usecases.GenerateQuestionUseCase

class GameplayViewModel(
    private val level: Level,
    private val application: Application,
) : ViewModel() {
    private lateinit var timer: CountDownTimer
    private val repository = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private var correctAnswers = 0
    private var answers = 0

    private var _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private var _progressValue = MutableLiveData<Int>()
    val progressValue: LiveData<Int>
        get() = _progressValue

    private var _progressValueIsEnough = MutableLiveData<Boolean>(true)
    val progressValueIsEnough: LiveData<Boolean>
        get() = _progressValueIsEnough

    private var _progressAnswers = MutableLiveData<String>("")
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private var _progressAnswersIsEnough = MutableLiveData<Boolean>(false)
    val progressAnswersIsEnough: LiveData<Boolean>
        get() = _progressAnswersIsEnough

    private var _minPercentage = MutableLiveData<Int>()
    val minPercentage: LiveData<Int>
        get() = _minPercentage

    private var _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var _gameResultsOnFinishingGame = MutableLiveData<GameResults>()
    val gameResultsOnFinishingGame: LiveData<GameResults>
        get() = _gameResultsOnFinishingGame

    init {
        startGame()
    }

    private fun startGame() {
        _minPercentage.value = level.levelSettings.minPercentageOfRightAnswers
        getQuestion()
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            level.levelSettings.gameTimeInSeconds * MILLIS_IN_TIME_UNIT,
            TIMER_TICK,
        ) {
            override fun onTick(millisUntilFinished: Long) {
                val time = formatTime(millisUntilFinished)
                _formattedTime.value = time
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        var seconds = millisUntilFinished / MILLIS_IN_TIME_UNIT
        val minutes = seconds / SECONDS_IN_MINUTE
        seconds -= minutes * SECONDS_IN_MINUTE
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun finishGame() {
        _gameResultsOnFinishingGame.value =
            GameResults(playerHasWonGame(), correctAnswers, answers, level.levelSettings)
    }

    private fun playerHasWonGame() =
        calculatePercentageOfRightAnswers() >= level.levelSettings.minPercentageOfRightAnswers
                && correctAnswers >= level.levelSettings.minCountOfRightAnswers

    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        getQuestion()
    }

    private fun checkAnswer(number: Int) {
        val rightAnswer = _question.value?.getRightAnswer()
        if (number == rightAnswer) {
            markAsCorrect()
        } else {
            markAsIncorrect()
        }
    }

    private fun getQuestion() {
        val question = generateQuestionUseCase(level.levelSettings)
        _question.value = question
    }

    private fun markAsCorrect() {
        correctAnswers++
        answers++
    }

    private fun markAsIncorrect() {
        answers++
    }

    private fun updateProgress() {
        updateProgressBarValue()
        updateProgressBarColor()
        updateProgressAnswers()
        updateProgressAnswersColor()
    }

    private fun updateProgressBarValue() {
        _progressValue.value = calculatePercentageOfRightAnswers()
    }

    private fun calculatePercentageOfRightAnswers(): Int {
        return if (answers == 0) {
            0
        } else {
            (correctAnswers * 100) / answers
        }
    }

    private fun updateProgressAnswers() {
        _progressAnswers.value = setProgressAnswersValue()
    }

    private fun setProgressAnswersValue() = String.format(
        application.getString(R.string.right_answers),
        correctAnswers,
        level.levelSettings.minCountOfRightAnswers
    )

    private fun updateProgressBarColor() {
        _progressValueIsEnough.value =
            calculatePercentageOfRightAnswers() >= level.levelSettings.minPercentageOfRightAnswers
    }

    private fun updateProgressAnswersColor() {
        _progressAnswersIsEnough.value =
            correctAnswers >= level.levelSettings.minCountOfRightAnswers
    }

    override fun onCleared() {
        timer.cancel()
    }

    companion object {
        private const val TIMER_TICK = 1000L
        private const val MILLIS_IN_TIME_UNIT = 1000L
        private const val SECONDS_IN_MINUTE = 60
    }
}
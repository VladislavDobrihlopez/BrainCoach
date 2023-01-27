package com.voitov.braincoach.data

import com.voitov.braincoach.domain.entity.Level
import com.voitov.braincoach.domain.entity.LevelSettings
import com.voitov.braincoach.domain.entity.Question
import com.voitov.braincoach.domain.repository.GameRepository
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {
    private val levels = listOf<Level>(
        Level(
            0,
            "Тестовый уровень", LevelSettings(
                20,
                -20,
                10,
                50,
                10,
                6
            )
        ),
        Level(
            1,
            "Сложение. Лёгкий ур.", LevelSettings(
                10,
                -10,
                10,
                50,
                30,
                6
            )
        ),
        Level(
            2,
            "Сложение. Средний ур.", LevelSettings(
                100,
                -100,
                20,
                70,
                30,
                6
            )
        ),
        Level(
            3,
            "Сложение. Сложный ур.", LevelSettings(
                1000,
                -1000,
                30,
                90,
                30,
                6
            )
        )
    )

    override fun generateQuestionUseCase(levelSettings: LevelSettings): Question {
        with(levelSettings) {
            var exprValue: Int
            var visiblePart: Int
            var possibleAnswers: List<Int>
            do {
                possibleAnswers = (1..countOfOptions).map {
                    Random.nextInt(
                        minExpressionValue,
                        maxExpressionValue + 1
                    )
                }
                exprValue = Random.nextInt(minExpressionValue, maxExpressionValue + 1)
                visiblePart = exprValue - possibleAnswers[Random.nextInt(0, possibleAnswers.size)]

                val set = HashSet<Int>(possibleAnswers)

            } while (set.size != levelSettings.countOfOptions)

            return Question(exprValue, visiblePart, possibleAnswers)
        }
    }

    override fun getLevels(): List<Level> {
        return levels
    }
}
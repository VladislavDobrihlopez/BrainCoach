package com.voitov.braincoach.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.voitov.braincoach.R
import com.voitov.braincoach.data.GameRepositoryImpl
import com.voitov.braincoach.domain.entity.LevelSettings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val q = GameRepositoryImpl.generateQuestionUseCase(LevelSettings(10, 0, 5, 50, 3, 10))
        Log.d("MainActivity", q.toString())

        if (savedInstanceState == null) {
            launchHelloFragment()
        }
    }

    private fun launchHelloFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerMain, HelloFragment.newInstance())
            .commit()
    }
}
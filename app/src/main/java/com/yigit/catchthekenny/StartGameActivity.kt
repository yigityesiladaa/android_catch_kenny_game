package com.yigit.catchthekenny

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class StartGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
    }

    fun startGame(view: View) {
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.putExtra("isStarted",true)
        startActivity(intent)
    }
}
package com.example.haikupuzzle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            run {
                val intent = Intent(Intent(this, MainActivity::class.java))
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}
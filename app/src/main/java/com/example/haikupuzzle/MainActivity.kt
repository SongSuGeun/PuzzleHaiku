package com.example.haikupuzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment).commit()
    }
}
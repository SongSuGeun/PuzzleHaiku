package com.example.haikupuzzle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haikupuzzle.menu.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_navigation_home -> {
                    println("song--1")
                    true
                }
                else -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    // TODO 既存のACTIVITYの画面にinflateするように変わること。
                    startActivity(intent)
                    true
                }
            }
        }

        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment).commit()
    }
}
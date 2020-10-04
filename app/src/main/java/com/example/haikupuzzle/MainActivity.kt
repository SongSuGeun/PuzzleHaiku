package com.example.haikupuzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.haikupuzzle.setting.ShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_navigation_home -> {
                    navigateToMainFragment()
                    true
                }
                else -> {
                    val fragment = ShowFragment.nweInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFrameLayout, fragment)
                        .commit()
                    true
                }
            }
        }
        navigateToMainFragment()
    }

    private fun navigateToMainFragment(){
        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment).commit()
    }
}
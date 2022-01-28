package com.example.enot.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.enot.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val controller = findNavController(R.id.fragmentContainer)

        val fragments = setOf<Int>(
            R.id.home,
            R.id.profile
        )

        setupActionBarWithNavController(controller, AppBarConfiguration(fragments))
        bottomNavView.setupWithNavController(controller)

    }
}
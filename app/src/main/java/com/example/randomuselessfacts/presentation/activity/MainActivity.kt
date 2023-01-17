package com.example.randomuselessfacts.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.domian.model.FactModel
import com.example.domian.usecase.*
import com.example.randomuselessfacts.R
import com.example.randomuselessfacts.app.App
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private var backPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.fragment_container)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.fragment_fact,
            R.id.fragment_history_facts,
            R.id.fragment_favourite_facts
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if (findNavController(R.id.fragment_container).navigateUp().not()) {
            if (backPressed) {
                backPressed = false
                finishAffinity()
            } else {
                backPressed = true

                Handler().postDelayed(Runnable {
                    backPressed = false
                }, 3000)

                Toast
                    .makeText(this, "Press again to exit", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
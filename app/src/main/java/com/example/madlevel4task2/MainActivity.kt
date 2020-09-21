package com.example.madlevel4task2

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        invalidateOptionsMenu()
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            menuBtnToggle(menu)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.game_history -> {
                navController.navigate(R.id.action_GameFragment_to_GameHistoryFragment)
                Log.i("MainActivity", "Clicked on game history app bar option")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun menuBtnToggle(menu: Menu) {
        val gameHistoryButton = menu.findItem(R.id.game_history)
        val clearGameHistory = menu.findItem(R.id.clear_game_history)
        navController.addOnDestinationChangedListener { _, destination,
                                                        _ ->
            if (destination.id in arrayOf(R.id.GameHistory)) {
                gameHistoryButton.isVisible = false
                clearGameHistory.isVisible = true
            } else if (destination.id in arrayOf(R.id.GameFragment)) {
                gameHistoryButton.isVisible = true
                clearGameHistory.isVisible = false
            }
        }
    }
}
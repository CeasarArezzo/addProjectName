package com.study.addprojectname

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MenuActivity : AppCompatActivity() {
    //todo list: remove when finished
    //TODO: zapisywanie wynikow przez baze firebase, leaderboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun buttonPressed(view : View)
    {
        when (view.id)
        {
            R.id.quickGameButton ->{
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
            R.id.chooseLevelButton ->{
                val intent = Intent(this, LevelChooseActivity::class.java)
                startActivity(intent)
            }
            R.id.tutorialButton ->{
                val intent = Intent(this, TutorialActivity::class.java)
                startActivity(intent)
            }
            R.id.settingsButton ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.leaderBoardButton -> {
                val intent = Intent(this, LeaderBoardActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
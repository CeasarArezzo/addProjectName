package com.study.addprojectname

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //added comments
        //second commit
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
        }
    }
}
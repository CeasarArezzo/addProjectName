package com.study.addprojectname

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //added comments
        //second commit
    }


    fun turnOnSettingsMenu (view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)

    }

    fun turnOnTutorial (view: View) {
        val intent = Intent(this, TutorialActivity::class.java)
        startActivity(intent)

    }
    fun turnOnLevelChoose (view: View) {
        val intent = Intent(this, LevelChooseActivity::class.java)
        startActivity(intent)

    }
    fun turnOnQuickGame (view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)

    }
}
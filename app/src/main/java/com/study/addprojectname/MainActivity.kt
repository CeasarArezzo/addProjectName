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


    fun turnOnSettingsMenu (view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivityForResult(intent, 0)

    }

    fun turnOnTutorial (view: View) {
        val intent = Intent(this, TutorialActivity::class.java)
        startActivityForResult(intent, 0)

    }
    fun turnOnLevelChoose (view: View) {
        val intent = Intent(this, LevelChooseActivity::class.java)
        startActivityForResult(intent, 0)

    }
    fun turnOnQuickGame (view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivityForResult(intent, 0)

    }
}
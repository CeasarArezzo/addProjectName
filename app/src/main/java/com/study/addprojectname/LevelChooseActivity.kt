package com.study.addprojectname

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LevelChooseActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private var levelList: ArrayList<Level> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_choose)
        initLevels()
        levelList.sortBy{it.levelNumber}
        initRecyclerView()
    }

    private fun initLevels() {
        levelList.add(Level(0,"Black Dragon", 1, 4, 4,8,1,4,1,8,0,6,5,0, Int.MAX_VALUE,0, 150))
        levelList.add(Level(1,"Blue Dragon", 3, 6, 8,4,1,8,0,0,8,0,6,0, Int.MAX_VALUE,0, 200))
        levelList.add(Level(2,"Death", 10, 8,4,4,10,6,1,10,6,4,8,0, Int.MAX_VALUE, 0,250))
        levelList.add(Level(3,"Cthulhu", 11, 10,6,2,6,10,0,10,6,10,10,0, Int.MAX_VALUE,0, 300))
        levelList.add(Level(4,"Demon", 46, 12, 2,10,4,4,10,4,6,10,12,0, Int.MAX_VALUE, 0,350))
        levelList.add(Level(5,"Vampire", 53, 14,50,0,0,0,0,0,0,0,14,0, Int.MAX_VALUE, 0,400))
        levelList.add(Level(6,"Bigfoot", 342, 20,8,8,8,8,8,8,8,8,0,0, Int.MAX_VALUE, 0,450))

    }


    private fun initRecyclerView()
    {
        recyclerView = findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        recyclerView.adapter = LevelAdapter(this, levelList)
    }



}
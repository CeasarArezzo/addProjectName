package com.study.addprojectname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        initLevels();
        levelList.sortBy{it.levelNumber}
        initRecyclerView()
    }

    private fun initLevels() {
        levelList.add(Level(0,"Black Dragon", 1, 10))
        levelList.add(Level(1,"Blue Dragon", 1, 10))
        levelList.add(Level(2,"Death", 1, 10))
        levelList.add(Level(3,"Cthulhu", 1, 10))
        levelList.add(Level(4,"Demon", 1, 10))
        levelList.add(Level(5,"Vampire", 1, 10))
        levelList.add(Level(6,"Bigfoot", 1, 10))


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
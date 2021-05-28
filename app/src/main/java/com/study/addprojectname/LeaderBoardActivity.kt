package com.study.addprojectname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LeaderBoardActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private var leaderBoardList: ArrayList<LeaderBoardRecord> = ArrayList()
    private val database = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)
        initRecyclerView()
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
        Log.i("am2021", "list size " + leaderBoardList.size)
        recyclerView.adapter = LeaderBoardAdapter(this, leaderBoardList)

        if (intent.hasExtra("LevelNum")){
            val levelNum = intent.getIntExtra("LevelNum", -1)
            createLevelList(levelNum)
        }
        else{
            createAllLevelList()
        }
    }

    private fun createLevelList(levelNum : Int){

        database.child("leaderboard").child(levelNum.toString()).get().addOnSuccessListener {
            Log.i("am2021", "Got value ${it.value}")
            for (child in it.children) {
                if (child.key != null && child.value != null) {
                    leaderBoardList.add(LeaderBoardRecord(levelNum.toString(), child.key.toString(), child.value.toString()))
//                    Log.i("am2021", "LEVEL $levelNum Got key ${newList.last()}")
                }
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun createAllLevelList(){

        for(i in 0..6) {
            database.child("leaderboard").child(i.toString()).get().addOnSuccessListener {
                Log.i("am2021", "Got value ${it.value}")
                for (child in it.children) {
                    if (child.key != null && child.value != null) {
                        leaderBoardList.add(
                            LeaderBoardRecord(
                                i.toString(),
                                child.key.toString(),
                                child.value.toString()
                            )
                        )
//                        Log.i("am2021", "new list size = " + newList.size)
//                        Log.i("am2021", "ALL LEVELS Got key ${newList.last()}")
                    }
                }
                recyclerView.adapter?.notifyDataSetChanged()
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
        }
    }
}
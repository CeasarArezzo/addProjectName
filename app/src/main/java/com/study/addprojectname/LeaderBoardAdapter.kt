package com.study.addprojectname

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class LeaderBoardAdapter(private val context : Context, private val list: List<LeaderBoardRecord>) : RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {

        val playerName : TextView = view.findViewById(R.id.playerName)
        val monster : ImageView  = view.findViewById(R.id.monsterImg)
        val points : TextView = view.findViewById(R.id.points)
        lateinit var thisLeaderBoardRecord: LeaderBoardRecord
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leader_board_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isNotEmpty())
        {
            holder.thisLeaderBoardRecord = list[position]
            holder.playerName.text = list[position].playerName
            holder.points.text = (list[position].score + "points")
            val res = context.resources
            val imgNumb = list[position].levelName.toInt()
            holder.monster.setImageResource( res.getIdentifier("level$imgNumb" + "_icon", "drawable", context.packageName) )
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}
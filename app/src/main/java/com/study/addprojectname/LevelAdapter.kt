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

class LevelAdapter(private val context : Context, private val list: List<Level>) : RecyclerView.Adapter<LevelAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val levelName : TextView = view.findViewById(R.id.levelName)
        val enemyHealthPoints : TextView = view.findViewById(R.id.enemyHP)
        val monsterImg : ImageView = view.findViewById(R.id.monsterImg)
        val bgLayout : ConstraintLayout = view.findViewById(R.id.bg_layout)
        lateinit var thisLevel : Level
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_level, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isNotEmpty())
        {
            holder.thisLevel = list[position]
            holder.levelName.text = list[position].enemyName
            holder.levelName.movementMethod = ScrollingMovementMethod()
            holder.enemyHealthPoints.text = list[position].healthPoints.toString()
            val res = context.resources
            val imgNumb = list[position].levelNumber
            setListeners(holder, holder.bgLayout)
            holder.monsterImg.setImageResource( res.getIdentifier("level$imgNumb" + "_icon", "drawable", context.packageName) )
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun setListeners(holder: ViewHolder, bgLayout: ConstraintLayout)
    {
        bgLayout.setOnClickListener() {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra("opponent", holder.thisLevel)
            (context as Activity).startActivity(intent)

            true
        }

    }
}
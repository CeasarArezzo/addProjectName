package com.study.addprojectname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import com.study.addprojectname.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    val gems : IntArray = getArrayOfGems()
    val noOfBlocks = 8
    var widthOfBlock = 2
    var widthOfScreen = 0
    var heightOfScreen = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val displayMetrics = DisplayMetrics()
        widthOfScreen = windowManager.currentWindowMetrics.bounds.width()
        heightOfScreen = windowManager.currentWindowMetrics.bounds.height()
        widthOfBlock = widthOfScreen / noOfBlocks
        createBoard()
    }

    private fun createBoard() {
        val gridLayout = binding.gameBoard
        gridLayout.rowCount = noOfBlocks
        gridLayout.columnCount = noOfBlocks
        gridLayout.layoutParams.width = widthOfScreen
        gridLayout.layoutParams.height = widthOfScreen

        for (i in 1..noOfBlocks)
        {
            for (j in 1..noOfBlocks)
            {
                val imageView = ImageView(this)
                imageView.id = i*100+j
                imageView.layoutParams = ViewGroup.LayoutParams(widthOfBlock, widthOfBlock)
                imageView.maxHeight = widthOfBlock
                imageView.maxWidth = widthOfBlock
                val randomCandy = gems.random()
                imageView.setImageResource(randomCandy)
                gridLayout.addView(imageView)
            }
        }
    }

    private fun getArrayOfGems(): IntArray {
        return intArrayOf(
                R.drawable.gems_blue,
                R.drawable.gems_green,
                R.drawable.gems_grey,
                R.drawable.gems_pink,
                R.drawable.gems_red,
                R.drawable.gems_yellow
        )
    }
}
package com.study.addprojectname

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.study.addprojectname.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    private val gems : IntArray = getArrayOfGems()
    private val noOfBlocks = 8
    private var widthOfBlock = 2
    private var widthOfScreen = 0
    private var heightOfScreen = 0
    var gemViewsList = arrayListOf<ImageView>()


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

        for (view : ImageView in gemViewsList)
        {
            view.setOnTouchListener(object : OnSwipeListener(applicationContext) {
                override fun onSwipeLeft() {
                    Log.i("am2021", "left swipe")
                    super.onSwipeLeft()
                }
                override fun onSwipeRight() {
                    Log.i("am2021", "right swipe")
                    super.onSwipeRight()
                }
                override fun onSwipeUp() {
                    Log.i("am2021", "up swipe")
                    super.onSwipeUp()
                }
                override fun onSwipeDown() {
                    Log.i("am2021", "down swipe")
                    super.onSwipeDown()
                }
            })
        }
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
                gemViewsList.add(imageView)
                gridLayout.addView(imageView)
            }
        }
        //TODO: pozbyc sie gotowych polaczen
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
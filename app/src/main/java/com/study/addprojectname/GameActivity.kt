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
    var gemSelected = -1
    var gemToSwitch = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        val viewToBind = binding.root
        setContentView(viewToBind)

//        val displayMetrics = DisplayMetrics()
        widthOfScreen = windowManager.currentWindowMetrics.bounds.width()
        heightOfScreen = windowManager.currentWindowMetrics.bounds.height()
        widthOfBlock = widthOfScreen / noOfBlocks
        createBoard()

        for (imageView : ImageView in gemViewsList)
        {
            imageView.setOnTouchListener(object : OnSwipeListener(applicationContext) {
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    Log.i("am2021", "left swipe")
                    gemSelected = imageView.id
                    gemToSwitch = gemSelected - 1
                    swapGems(gemSelected, gemToSwitch)
                }
                override fun onSwipeRight() {
                    super.onSwipeRight()
                    gemSelected = imageView.id
                    gemToSwitch = gemSelected + 1
                    Log.i("am2021", "right swipe")
                }
                override fun onSwipeUp() {
                    super.onSwipeUp()
                    Log.i("am2021", "up swipe")
                }
                override fun onSwipeDown() {                    super.onSwipeDown()

                    Log.i("am2021", "down swipe")
                }
            })
        }
    }

    private fun swapGems(gemSelected : Int, gemToSwitch: Int)
    {
        var background1 = gemViewsList[gemSelected].tag as Int
        var background2 = gemViewsList[gemToSwitch].tag as Int
        gemViewsList[gemSelected].setImageResource(background2)
        gemViewsList[gemToSwitch].setImageResource(background1)
        gemViewsList[gemSelected].tag = background2
        gemViewsList[gemToSwitch].tag = background1
    }

    private fun createBoard() {
        val gridLayout = binding.gameBoard
        gridLayout.rowCount = noOfBlocks
        gridLayout.columnCount = noOfBlocks
        gridLayout.layoutParams.width = widthOfScreen
        gridLayout.layoutParams.height = widthOfScreen

        for (i in 1..noOfBlocks*noOfBlocks)
        {
            val imageView = ImageView(this)
            imageView.id = i-1
            imageView.layoutParams = ViewGroup.LayoutParams(widthOfBlock, widthOfBlock)
            imageView.maxHeight = widthOfBlock
            imageView.maxWidth = widthOfBlock
            val randomCandy = gems.random()
            imageView.setImageResource(randomCandy)
            imageView.tag = randomCandy
            gemViewsList.add(imageView)
            gridLayout.addView(imageView)
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
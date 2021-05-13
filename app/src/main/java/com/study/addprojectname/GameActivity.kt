package com.study.addprojectname

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.study.addprojectname.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    private val gems : IntArray = getArrayOfGems()
    private val noOfBlocks = 8
    private var widthOfBlock = 2
    private var widthOfScreen = 0
    private var heightOfScreen = 0
    private var gemViewsList = arrayListOf<ImageView>()
    var gemSelected = -1
    var gemToSwitch : Int? = -1
    var nonGem : Int = R.drawable.ic_launcher_background


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
                    gemSelected = imageView.id
                    gemToSwitch = leftNeighbour(gemSelected)
                    Log.i("am2021", "left swipe")
                    gemToSwitch?.let {
                        swapGems(gemSelected, it) }
                }
                override fun onSwipeRight() {
                    super.onSwipeRight()
                    gemSelected = imageView.id
                    Log.i("am2021", "right swipe")
                    gemToSwitch = rightNeighbour(gemSelected)
                    gemToSwitch?.let { swapGems(gemSelected, it) }
                }
                override fun onSwipeUp() {
                    super.onSwipeUp()
                    gemSelected = imageView.id
                    Log.i("am2021", "top swipe")
                    gemToSwitch = topNeighbour(gemSelected)
                    gemToSwitch?.let { swapGems(gemSelected, it) }
                }
                override fun onSwipeDown() {
                    super.onSwipeDown()
                    gemSelected = imageView.id
                    Log.i("am2021", "down swipe")
                    gemToSwitch = lowerNeighbour(gemSelected)
                    gemToSwitch?.let { swapGems(gemSelected, it) }
                }
            })
        }
    }

    private fun lowerNeighbour(gemSelected: Int): Int?
    {
        return if (gemSelected + noOfBlocks < noOfBlocks * noOfBlocks)
        {
            gemSelected + noOfBlocks
        }
        else
        {
            null
        }
    }

    private fun topNeighbour(gemSelected: Int): Int?
    {
        return if (gemSelected - noOfBlocks >= 0)
        {
            gemSelected - noOfBlocks
        }
        else
        {
            null
        }
    }

    private fun leftNeighbour(gemSelected: Int): Int?
    {
        return if ((gemSelected - 1) / noOfBlocks == gemSelected / noOfBlocks && gemSelected != 0) {
            gemSelected - 1
        } else {
            null
        }
    }

    private fun rightNeighbour(gemSelected: Int): Int?
    {
        return if ((gemSelected + 1) / noOfBlocks == gemSelected / noOfBlocks ) {
            gemSelected + 1
        } else {
            null
        }
    }

    private fun checkForGroups(gameOn: Boolean)
    {
        for (i in 0 until noOfBlocks*noOfBlocks)
        {
            checkRow(i)
            checkCol(i)
        }
        popAll(gameOn)
    }

    private fun checkCol(gemId: Int)
    {
        var next1 = lowerNeighbour(gemId)
        if (next1 != null)
        {
            var next2 = lowerNeighbour(next1)
            if (next2 != null)
            {
                if (gemViewsList[gemId].tag == gemViewsList[next1].tag && gemViewsList[next1].tag == gemViewsList[next2].tag)
                {
                    markColumns(gemId)
                }
            }
        }
    }

    private fun checkRow(gemId: Int)
    {
        var next1 = rightNeighbour(gemId)
        if (next1 != null)
        {
            var next2 = rightNeighbour(next1)
            if (next2 != null)
            {
                if (gemViewsList[gemId].tag == gemViewsList[next1].tag && gemViewsList[next1].tag == gemViewsList[next2].tag)
                {
                    markRows(gemId)
                }
            }
        }
    }

    private fun markColumns(gemId: Int)
    {
        var current : Int? = gemId;
        while (current != null)
        {
            gemViewsList[current].tag = nonGem
            current = lowerNeighbour(current)
        }
    }

    private fun markRows(gemId: Int)
    {
        var current : Int? = gemId;
        while (current != null)
        {
            gemViewsList[current].tag = nonGem
            current = lowerNeighbour(current)
        }
    }

    private fun popAll(gameOn: Boolean)
    {
        allFallDown(gameOn)
    }

    private fun allFallDown(gameOn: Boolean)
    {
        for (i in noOfBlocks*noOfBlocks-1 downTo 0 )
        {
            if (gemViewsList[i].tag == nonGem)
            {
                Log.i("am2021", "nonGem")
            }
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
        checkForGroups(true)
    }

    private fun createBoard() {
        val gridLayout = binding.gameBoard
        gridLayout.rowCount = noOfBlocks
        gridLayout.columnCount = noOfBlocks
        gridLayout.layoutParams.width = widthOfScreen
        gridLayout.layoutParams.height = widthOfScreen

        for (i in 0 until noOfBlocks*noOfBlocks)
        {
            val imageView = ImageView(this)
            imageView.id = i
            imageView.layoutParams = ViewGroup.LayoutParams(widthOfBlock, widthOfBlock)
            imageView.maxHeight = widthOfBlock
            imageView.maxWidth = widthOfBlock
            val randomCandy = gems.random()
            imageView.setImageResource(randomCandy)
            imageView.tag = randomCandy
            gemViewsList.add(imageView)
            gridLayout.addView(imageView)
        }

        gemViewsList[0].setImageResource(R.drawable.ic_launcher_background)
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
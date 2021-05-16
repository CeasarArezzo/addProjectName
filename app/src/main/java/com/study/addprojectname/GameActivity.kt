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
    private var popAgain : Boolean = false
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

    private fun resolveGemEffect(current: Int)
    {
        Log.i("am2021", "resolving effect: $current")
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
            checkRow(i, gameOn)
            checkCol(i, gameOn)
        }
        if (popAgain)
        {
            popAgain = false
            checkForGroups(gameOn)
        }
    }

    private fun checkCol(gemId: Int, gameOn: Boolean)
    {
        val next1 = lowerNeighbour(gemId)
        if (next1 != null)
        {
            val next2 = lowerNeighbour(next1)
            if (next2 != null)
            {
                if (gemViewsList[gemId].tag == gemViewsList[next1].tag && gemViewsList[next1].tag == gemViewsList[next2].tag)
                {
                    popColumn(gemId, gameOn)
                }
            }
        }
    }

    private fun checkRow(gemId: Int, gameOn: Boolean)
    {
        val next1 = rightNeighbour(gemId)
        if (next1 != null)
        {
            val next2 = rightNeighbour(next1)
            if (next2 != null)
            {
                if (gemViewsList[gemId].tag == gemViewsList[next1].tag && gemViewsList[next1].tag == gemViewsList[next2].tag)
                {
                    popRow(gemId, gameOn)
                }
            }
        }
    }

    private fun popColumn(gemId: Int, gameOn: Boolean)
    {
        var current : Int? = gemId
        val initTag = gemViewsList[current!!].tag
        while (current != null && gemViewsList[current].tag == initTag)
        {
            popSingle(current, gameOn)
//            gemViewsList[current].setImageResource( nonGem )
            current = lowerNeighbour(current)
        }
    }

    private fun popRow(gemId: Int, gameOn: Boolean)
    {
        var current : Int? = gemId
        val initTag = gemViewsList[current!!].tag
        while (current != null && gemViewsList[current].tag == initTag)
        {
            popSingle(current, gameOn)
//            gemViewsList[current].setImageResource( nonGem )
            current = rightNeighbour(current)
        }
    }

    private fun popSingle(current: Int, gameOn: Boolean)
    {
        if (gameOn)
        {
            Log.i("am2021", "resolving effect at $current")
            resolveGemEffect(gemViewsList[current].tag as Int)
        }
        gemViewsList[current].tag = nonGem
        var top = topNeighbour(current)
        var actual = current
        while (top != null)
        {
            swapGems(actual, top, false)
            actual = top
            top = topNeighbour(top)
        }
        val randomGem = gems.random()
        gemViewsList[actual].setImageResource(randomGem)
        gemViewsList[actual].tag = randomGem
        popAgain = true

    }


//    private fun popAll(gameOn: Boolean)
//    {
//        allFallDown(gameOn)
//    }
//
//    private fun allFallDown(gameOn: Boolean)
//    {
//        for (i in noOfBlocks*noOfBlocks-1 downTo 0 )
//        {
//            if (gemViewsList[i].tag == nonGem)
//            {
//                Log.i("am2021", "nonGem")
//            }
//        }
//    }

    private fun swapGems(gemSelected : Int, gemToSwitch: Int, shouldCheck : Boolean)
    {
        val background1 = gemViewsList[gemSelected].tag as Int
        val background2 = gemViewsList[gemToSwitch].tag as Int
        gemViewsList[gemSelected].setImageResource(background2)
        gemViewsList[gemToSwitch].setImageResource(background1)
        gemViewsList[gemSelected].tag = background2
        gemViewsList[gemToSwitch].tag = background1
        if (shouldCheck)
        {
            checkForGroups(true)
        }
    }

    private fun swapGems(gemSelected : Int, gemToSwitch: Int)
    {
        swapGems(gemSelected, gemToSwitch, true)
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
            val randomGem = gems.random()
            imageView.setImageResource(randomGem)
            imageView.tag = randomGem
            gemViewsList.add(imageView)
            gridLayout.addView(imageView)
        }

        checkForGroups(false)
        //TODO: pozbyc sie gotowych polaczen
    }

    private fun getArrayOfGems(): IntArray {
        return intArrayOf(
                R.drawable.gems_water,
                R.drawable.gems_grass,
                R.drawable.gems_balance,
                R.drawable.gems_life,
                R.drawable.gems_fire,
                R.drawable.gems_electricity
        )
    }
}
package com.study.addprojectname

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.study.addprojectname.databinding.ActivityGameBinding


class   GameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    private val gems : IntArray = getArrayOfGems()
    private var score : Int = 0
    private val noOfBlocks = 8
    private var widthOfBlock = 2
    private var widthOfScreen = 0
    private var heightOfScreen = 0
    private var gemViewsList = arrayListOf<ImageView>()
    private var popAgain : Boolean = false
    var gemSelected = -1
    var gemToSwitch : Int? = -1
    private var nonGem : Int = R.drawable.ic_launcher_background
    private lateinit var opponent : Level



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        val viewToBind = binding.root
        setContentView(viewToBind)

        widthOfScreen = (windowManager.currentWindowMetrics.bounds.width() * 0.9).toInt()
        heightOfScreen = windowManager.currentWindowMetrics.bounds.height()
        widthOfBlock = widthOfScreen / noOfBlocks



        if (intent.hasExtra("opponent"))
        {
            val tmpOpponent = intent.getParcelableExtra<Level>("opponent")
            if (tmpOpponent != null)
            {
                opponent = tmpOpponent
            }
        }
        else
        {
            opponent = Level(0,"Black Dragon", 1, 4, 4,8,1,4,1,8,0,6,5,0, Int.MAX_VALUE,0, 150)

        }

        loadOpponent(opponent)
        createBoard()
        setOnSwipeListeners()
    }

    private fun loadOpponent(opponent: Level)
    {
        binding.scoreTextView.text = "0"
        binding.opponentImage.setImageResource(
            applicationContext.resources.getIdentifier("level" + opponent.levelNumber + "_icon_l", "drawable", applicationContext.packageName) )
        binding.opponentName.text = opponent.enemyName
        binding.enemyHP.max = opponent.healthPoints
        binding.enemyHP.progress = binding.enemyHP.max
        binding.playerHP.max= opponent.health
        binding.playerHP.progress=  binding.playerHP.max

    }

    private fun resolveGemEffect(current: Int)
    {
        var damage = 0
        when (current) {
            R.drawable.gems_water -> {
                Log.i("am2021", "water")

                damage = opponent.waterDamage
            }
            R.drawable.gems_grass -> {
                Log.i("am2021", "grass")

                damage = opponent.grassDamage
            }
            R.drawable.gems_balance -> {
                Log.i("am2021", "balance")
                val temp = binding.playerHP.progress
                binding.playerHP.progress = ( binding.playerHP.max.toFloat() * (( binding.playerHP.progress.toFloat() / binding.playerHP.max.toFloat() +  binding.enemyHP.progress.toFloat() / opponent.healthPoints.toFloat()) /2f)).toInt() + opponent.damagePerTurn
                damage = (binding.enemyHP.max.toFloat() * ((binding.enemyHP.progress.toFloat() / opponent.healthPoints.toFloat() - temp.toFloat() / binding.playerHP.max.toFloat())/2f)).toInt()


            }
            R.drawable.gems_life -> {
                Log.i("am2021", "life")

                binding.playerHP.progress += opponent.damagePerTurn /3
            }
            R.drawable.gems_fire -> {
                Log.i("am2021", "fire")

                damage = opponent.fireDamage
            }
            else -> {
                Log.i("am2021", "elect")

                damage = opponent.electricityDamage
            }
        }
        damageEnemy(damage)


    }



    private fun setOnSwipeListeners() {
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
            checkRow(i, gameOn)
            checkCol(i, gameOn)
        }
        if (popAgain)
        {
            popAgain = false
            checkForGroups(gameOn)
        }

    }

    private fun gameWon() : Boolean {
        return binding.enemyHP.progress <= 0
    }

    private fun gameLost() : Boolean {
        return binding.playerHP.progress <= 0

    }

    private fun openDialog(Message : String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(Message)
        builder.setPositiveButton("OK"){ dialog, which ->
            finish()
        }
        val dialog = builder.show()
        val messageText = dialog.findViewById<View>(android.R.id.message) as TextView
        messageText.gravity = Gravity.CENTER
        dialog.show()
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
        var points = 0
        while (current != null && gemViewsList[current].tag == initTag)
        {
            points += popSingle(current, gameOn)
//            gemViewsList[current].setImageResource( nonGem )
            current = lowerNeighbour(current)
        }
        getPoints(points)

    }

    private fun popRow(gemId: Int, gameOn: Boolean)
    {
        var current : Int? = gemId
        val initTag = gemViewsList[current!!].tag
        var points = 0
        while (current != null && gemViewsList[current].tag == initTag)
        {
            points += popSingle(current, gameOn)
//            gemViewsList[current].setImageResource( nonGem )
            current = rightNeighbour(current)
        }
        getPoints(points)
    }

    private fun popSingle(current: Int, gameOn: Boolean) : Int
    {
        var points = 0
        if (gameOn)
        {
            Log.i("am2021", "resolving effect at $current")
            resolveGemEffect(gemViewsList[current].tag as Int)
            points = 1
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
        return points
    }

    private fun getPoints(points : Int) {

        if (points  != 0) {
            val toAdd = points * 10
            score += toAdd
            val n = binding.scoreTextView.text.toString().toInt()
            binding.scoreTextView.text = (n + toAdd).toString()
        }
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


                if (gameWon()) {
                    openDialog("GAME WON \n CONGRATULATIONS!")
                }
                else
                {
                    damagePlayer()
                    if (gameLost()) {
                        openDialog("GAME LOST \n GET LUCK NEXT TIME!")
                    }
                }


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

    private fun damagePlayer() {
        binding.playerHP.progress -= opponent.damagePerTurn
    }
    private fun damageEnemy(damage: Int) {

        Log.i("am2021", "damage for: $damage")

        binding.enemyHP.progress -= damage

        YoYo.with(Techniques.Wobble).duration(700).playOn(binding.opponentImage)
    }

    
}
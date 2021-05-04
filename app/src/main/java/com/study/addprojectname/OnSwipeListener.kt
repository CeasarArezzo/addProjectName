package com.study.addprojectname

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

open class OnSwipeListener(context: Context) : View.OnTouchListener{
    private var gestureDetector : GestureDetector
    companion object{
        const val SWIPE_THRESHOLD = 100
        const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    init
    {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean
    {
        return gestureDetector.onTouchEvent(event)
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener()
    {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            val result : Boolean
            if (e1 != null && e2 != null)
            {
                val yDiff : Float = e2.y - e1.y
                val xDiff : Float = e2.x - e1.x
                if (abs(xDiff) > abs(yDiff))
                {
                    //left or right
                    if (abs(xDiff) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
                    {
                        if (xDiff > 0)
                        {
                            onSwipeRight()
                        }
                        else
                        {
                            onSwipeLeft()
                        }
                        result = true
                    }
                }
                else if (abs(yDiff) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD)
                {
                    if (yDiff > 0)
                    {
                        onSwipeDown()
                    }
                    else
                    {
                        onSwipeUp()
                    }
                    result = true
                }

            }

            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

    open fun onSwipeLeft()
    {

    }

    open fun onSwipeRight()
    {

    }

    open fun onSwipeUp()
    {

    }

    open fun onSwipeDown()
    {

    }

}
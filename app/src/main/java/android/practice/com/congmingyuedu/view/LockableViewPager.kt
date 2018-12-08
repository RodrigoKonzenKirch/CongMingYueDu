package android.practice.com.congmingyuedu.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class LockableViewPager : ViewPager{

    private var swipeLocked: Boolean = false

    constructor(context: Context): super(context)

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)

    fun getSwipeLocked(): Boolean{return swipeLocked}

    fun setSwipeLocked(locked: Boolean){
        this.swipeLocked = locked
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return !swipeLocked && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return !swipeLocked && super.onInterceptTouchEvent(ev)
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return !swipeLocked && super.canScrollHorizontally(direction)
    }

}


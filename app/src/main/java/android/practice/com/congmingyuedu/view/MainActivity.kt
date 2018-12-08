package android.practice.com.congmingyuedu.view

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        viewpager_main.setSwipeLocked(true)

        tabs_main.setupWithViewPager(viewpager_main)
    }

}
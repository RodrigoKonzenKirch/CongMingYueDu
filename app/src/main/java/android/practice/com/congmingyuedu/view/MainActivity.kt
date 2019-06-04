package android.practice.com.congmingyuedu.view

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDrawerLayout = findViewById(R.id.main_drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.main_nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()

            //TODO: Implement click event on navigation drawer items
            when(menuItem.itemId){
                R.id.nav_read_text -> Toast.makeText(this, "read not implemented yet", Toast.LENGTH_LONG).show()
                R.id.nav_open_text -> Toast.makeText(this, "open not implemented yet", Toast.LENGTH_LONG).show()
                R.id.nav_import_text -> Toast.makeText(this, "import not implemented yet", Toast.LENGTH_LONG).show()
                R.id.nav_delete_text -> Toast.makeText(this, "delete not implemented yet", Toast.LENGTH_LONG).show()
                else -> Toast.makeText(this, "ELSE", Toast.LENGTH_LONG).show()

            }

            true
        }


    }

}
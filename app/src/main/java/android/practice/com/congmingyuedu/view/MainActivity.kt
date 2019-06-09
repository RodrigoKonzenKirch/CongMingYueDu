package android.practice.com.congmingyuedu.view

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph, main_drawer_layout)
        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)

        mDrawerLayout = findViewById(R.id.main_drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.main_nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()

            //TODO: Implement click event on navigation drawer items
            when(menuItem.itemId){
                R.id.nav_read_text -> nav_host_fragment.findNavController().navigate(R.id.textFragment)
                R.id.nav_open_text -> Toast.makeText(this, "open not implemented yet", Toast.LENGTH_LONG).show()
                R.id.nav_import_text -> nav_host_fragment.findNavController().navigate(R.id.importFragment)
                R.id.nav_delete_text -> Toast.makeText(this, "delete not implemented yet", Toast.LENGTH_LONG).show()
                else -> Toast.makeText(this, "ELSE", Toast.LENGTH_LONG).show()

            }

            true
        }

    }
}
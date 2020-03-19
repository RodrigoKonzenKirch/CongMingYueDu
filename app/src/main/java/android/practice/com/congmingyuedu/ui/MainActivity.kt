package android.practice.com.congmingyuedu.ui

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
companion object{
    const val KEY_PREF_TEXT_FONT_SIZE = "MAIN_TEXT_FONT_SIZE"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        androidx.preference.PreferenceManager.setDefaultValues(this, R.xml.preferences, false)

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

            //TODO: Implement click event on navigation drawer delete
            when(menuItem.itemId){
                R.id.nav_read_text -> nav_host_fragment.findNavController().navigate(R.id.textFragment)
                R.id.nav_open_text -> nav_host_fragment.findNavController().navigate(R.id.openTextFragment)
                R.id.nav_import_text -> nav_host_fragment.findNavController().navigate(R.id.importFragment)
                R.id.nav_delete_text -> nav_host_fragment.findNavController().navigate(R.id.deleteTextFragment)
                R.id.nav_show_vocabulary -> nav_host_fragment.findNavController().navigate(R.id.showVocabularyFragment)
                R.id.nav_settings -> nav_host_fragment.findNavController().navigate(R.id.settingsFragment)
                else -> Toast.makeText(this, "ELSE", Toast.LENGTH_LONG).show()
            }

            true
        }

    }

}
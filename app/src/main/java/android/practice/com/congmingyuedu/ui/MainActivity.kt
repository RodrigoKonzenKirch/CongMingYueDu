/* 聪明阅读(CongMingYueDu) Chinese text reader with tools to learn vocabulary
Copyright (C) 2020 Rodrigo Konzen Kirch

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.*/

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
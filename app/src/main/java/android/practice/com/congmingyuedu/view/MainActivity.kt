package android.practice.com.congmingyuedu.view

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.model.AppDatabase
import android.practice.com.congmingyuedu.model.ChineseDictionary
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private val chineseDictionaryDao = AppDatabase.getInstance(application)!!.chineseDictionaryDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateDatabase()
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
                R.id.nav_open_text -> nav_host_fragment.findNavController().navigate(R.id.openTextFragment)
                R.id.nav_import_text -> nav_host_fragment.findNavController().navigate(R.id.importFragment)
                R.id.nav_delete_text -> Toast.makeText(this, "delete not implemented yet", Toast.LENGTH_LONG).show()
                R.id.nav_show_vocabulary -> nav_host_fragment.findNavController().navigate(R.id.showVocabularyFragment)
                else -> Toast.makeText(this, "ELSE", Toast.LENGTH_LONG).show()
            }

            true
        }

    }

    fun populateDatabase(){
        GlobalScope.launch(Dispatchers.IO) {
            if (chineseDictionaryDao.getCountDictionary() <= 0) {

                val listOfWordsFromFile = application.assets.open("cedict_ts.u8").bufferedReader().readLines()

                val listOfWordsFormatedForDB = mutableListOf<ChineseDictionary>()

                listOfWordsFromFile.forEach {
                    var tempString = it
                    val wordTraditional: String
                    val wordSimplified: String
                    val wordPinyin: String
                    var wordTranslation: String

                    if (tempString.indexOf(" ") > -1 &&
                        tempString.indexOf("[") > -1 &&
                        tempString.indexOf("]") > -1 &&
                        tempString.indexOf("/") > -1 &&
                        !tempString[0].equals("#")) {

                        wordTraditional = tempString.substring(0, tempString.indexOf(" "))
                        tempString = tempString.substring(tempString.indexOf(" ") + 1)
                        wordSimplified = tempString.substring(0, tempString.indexOf(" "))
                        wordPinyin = tempString.substring(tempString.indexOf("[")+1, tempString.indexOf("]"))
                        wordTranslation = tempString.substring(tempString.indexOf("/")+1)
                        wordTranslation = wordTranslation.removeSuffix("/")

                        listOfWordsFormatedForDB.add(ChineseDictionary(null, wordTraditional, wordSimplified, wordPinyin, wordTranslation))
                    }
                }
                chineseDictionaryDao.insertAll(listOfWordsFormatedForDB.toList())
            }
        }
    }
}
package android.practice.com.congmingyuedu.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [(ChineseText::class),
                        (Vocabulary::class),
                        (ChineseDictionary::class)],
                        version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun textDao(): ChineseTextDao
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun chineseDictionaryDao(): ChineseDictionaryDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database.db"
                ).addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch(Dispatchers.IO) {
                            val chineseDictionaryDao = getInstance(context)!!.chineseDictionaryDao()
                            if (chineseDictionaryDao.getCountDictionary() <= 0) {

                                val listOfWordsFromFile = context.assets.open("cedict_ts.u8").bufferedReader().readLines()

                                val listOfWordsFormattedForDB = mutableListOf<ChineseDictionary>()

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
                                        tempString[0] != '#') {

                                        wordTraditional = tempString.substring(0, tempString.indexOf(" "))
                                        tempString = tempString.substring(tempString.indexOf(" ") + 1)
                                        wordSimplified = tempString.substring(0, tempString.indexOf(" "))
                                        wordPinyin = tempString.substring(tempString.indexOf("[")+1, tempString.indexOf("]"))
                                        wordTranslation = tempString.substring(tempString.indexOf("/")+1)
                                        wordTranslation = wordTranslation.removeSuffix("/")

                                        listOfWordsFormattedForDB.add(ChineseDictionary(null, wordTraditional, wordSimplified, wordPinyin, wordTranslation))
                                    }
                                }
                                chineseDictionaryDao.insertAll(listOfWordsFormattedForDB.toList())
                            }
                        }
                    }

                }).build()
                INSTANCE = instance
                return instance
                }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

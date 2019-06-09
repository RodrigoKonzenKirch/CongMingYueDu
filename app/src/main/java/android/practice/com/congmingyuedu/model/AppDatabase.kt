package android.practice.com.congmingyuedu.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [(Text::class),
                        (Vocabulary::class),
                        (ChineseDictionary::class)],
                        version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun textDao(): TextDao
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun chineseDictionaryDao(): ChineseDictionaryDao

    companion object {
        var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database.db"
                    ).build()
                }
                return INSTANCE
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

//    TODO: [Implement] Finish populate database
/*    fun populateDictionary(context: Context){

        val dictionaryAsListOfStrings = context.assets.open("cedict_ts.u8").bufferedReader().readLines()

        //List containing all entries of the dictionary, ready to
        //populate the database
        val dictEntries = mutableListOf<ChineseDictionary>()

        dictionaryAsListOfStrings.forEach {
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

                dictEntries.add(ChineseDictionary(0, wordTraditional, wordSimplified, wordPinyin, wordTranslation))
            }
        }
        System.out.println("DICT: "+dictEntries[2010])

    }*/
}

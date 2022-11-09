package android.practice.com.congmingyuedu

import android.content.Context
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseDictionary
import android.practice.com.congmingyuedu.data.local.ChineseDictionaryDao
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class ChineseDictionaryDaoTest {

    private lateinit var chineseDictionaryDao: ChineseDictionaryDao
    private lateinit var db: AppDatabase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb(){
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        chineseDictionaryDao = db.chineseDictionaryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun insertAllAndGetAllFromChineseDictionaryDao(){
        val word1 = ChineseDictionary(0, "個", "个", "ge", "counter")
        val word2 = ChineseDictionary(1, "誰", "谁", "shei", "who")
        val word3 = ChineseDictionary(2, "什麼", "什么", "shenme", "what")

        val allWordsToBeInserted = listOf(word1, word2, word3)

        chineseDictionaryDao.insertAll(allWordsToBeInserted)
        val allWordsFromDb = chineseDictionaryDao.getAll()

        assertEquals(allWordsFromDb.size, 3)
        assertEquals(allWordsFromDb[0], word1)
        assertEquals(allWordsFromDb[1], word2)
        assertEquals(allWordsFromDb[2], word3)
    }

    @Test
    @Throws(Exception::class)
    fun getWord() = runBlocking{
        val word1 = ChineseDictionary(0, "個", "个", "ge", "counter")
        val word2 = ChineseDictionary(1, "誰", "谁", "shei", "who")
        val word3 = ChineseDictionary(2, "什麼", "什么", "shenme", "what")

        val allWordsToBeInserted = listOf(word1, word2, word3)

        chineseDictionaryDao.insertAll(allWordsToBeInserted)

        var wordFromDb = chineseDictionaryDao.getWord("什么")
        assertEquals(wordFromDb, word3)
        wordFromDb = chineseDictionaryDao.getWord("个")
        assertEquals(wordFromDb, word1)

    }
}
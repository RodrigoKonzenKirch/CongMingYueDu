package android.practice.com.congmingyuedu

import android.content.Context
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseText
import android.practice.com.congmingyuedu.data.local.ChineseTextDao
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class ChineseTextDaoTest {

    private lateinit var chineseTextDao: ChineseTextDao
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

        chineseTextDao = db.textDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertChineseTextAndGetById() = runBlocking{
        val dummyText = ChineseText(0, "Text Title", "Text Content", false)

        chineseTextDao.insert(dummyText)

        val chineseTextFromDb = chineseTextDao.getTextById(0)
        assertEquals(chineseTextFromDb.getOrAwaitValue(), dummyText)
    }

    @Test
    @Throws(Exception::class)
    fun insertThreeTexts_getAllReturnsThreeTextsOrderedByTitle() = runBlocking{
        val dummyText1 = ChineseText(0, "Text Title c", "Text Content 11", false)
        val dummyText2 = ChineseText(1, "Text Title a", "Text Content 22", false)
        val dummyText3 = ChineseText(2, "Text Title b", "Text Content 33", false)

        chineseTextDao.insert(dummyText1)
        chineseTextDao.insert(dummyText2)
        chineseTextDao.insert(dummyText3)

        val allTextsFromDb = chineseTextDao.getAll()

        assertEquals(allTextsFromDb.getOrAwaitValue()[0], dummyText2)
        assertEquals(allTextsFromDb.getOrAwaitValue()[1], dummyText3)
        assertEquals(allTextsFromDb.getOrAwaitValue()[2], dummyText1)
    }

    @Test
    @Throws(Exception::class)
    fun getTextContentById() = runBlocking{
        val expectedTextContent = "This is the text content that should return from the database"
        val dummyText1 = ChineseText(0, "Text Title", expectedTextContent, false)

        chineseTextDao.insert(dummyText1)

        val textFromDb = chineseTextDao.getTextContentById(0)
        assertEquals(textFromDb.getOrAwaitValue(), expectedTextContent)
    }

    @Test
    @Throws(Exception::class)
    fun deleteTextById() = runBlocking {
        val dummyText1 = ChineseText(0, "Text Title a", "Text Content 11", false)
        val dummyText2 = ChineseText(1, "Text Title b", "Text Content 22", false)
        val dummyText3 = ChineseText(2, "Text Title c", "Text Content 33", false)

        chineseTextDao.insert(dummyText1)
        chineseTextDao.insert(dummyText2)
        chineseTextDao.insert(dummyText3)

        chineseTextDao.deleteTextById(1)

        val allTextsFromDb = chineseTextDao.getAll()
        assertEquals(allTextsFromDb.getOrAwaitValue()[0], dummyText1)
        assertEquals(allTextsFromDb.getOrAwaitValue()[1], dummyText3)

    }
}
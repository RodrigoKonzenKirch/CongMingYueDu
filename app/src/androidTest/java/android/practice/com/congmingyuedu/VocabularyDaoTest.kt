package android.practice.com.congmingyuedu

import android.content.Context
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.data.local.VocabularyDao
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4ClassRunner::class)
class VocabularyDaoTest {

    private lateinit var vocabularyDao: VocabularyDao
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

        vocabularyDao = db.vocabularyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetById() = runBlocking{
        val vocab = Vocabulary(0, "a", false, "")
        vocabularyDao.insert(vocab)
        val vocabCollected = vocabularyDao.getVocabularyById(0)

        assertEquals(vocabCollected, vocab)

    }

    @Test
    @Throws(Exception::class)
    fun getAll_ReturnsInsertedValues() = runBlocking{
        val vocab1 = Vocabulary(0, "a", false, "")
        val vocab2 = Vocabulary(1, "b", false, "")
        val vocab3 = Vocabulary(2, "c", false, "")

        vocabularyDao.insert(vocab1)
        vocabularyDao.insert(vocab2)
        vocabularyDao.insert(vocab3)

        val allVocabFromDb = vocabularyDao.getAll()

        assertEquals(allVocabFromDb.getOrAwaitValue().size, 3)

        assertEquals(allVocabFromDb.getOrAwaitValue()[0], vocab1)
        assertEquals(allVocabFromDb.getOrAwaitValue()[1], vocab2)
        assertEquals(allVocabFromDb.getOrAwaitValue()[2], vocab3)
    }

    @Test
    @Throws(Exception::class)
    fun setTrueOrFalseToVocabularyStarredCorrectlySaveValueToDatabase() = runBlocking{
        val vocab1 = Vocabulary(0, "a", false, "")

        vocabularyDao.insert(vocab1)

        vocabularyDao.setVocabularyStarred(false, 0)
        var vocabFromDb = vocabularyDao.getVocabularyById(0)
        assertFalse(vocabFromDb.vocabularyStarred)

        vocabularyDao.setVocabularyStarred(true, 0)
        vocabFromDb = vocabularyDao.getVocabularyById(0)
        assertTrue(vocabFromDb.vocabularyStarred)
    }

    @Test
    @Throws(Exception::class)
    fun deleteVocabularyByIdCorrectlyRemovesVocabFromDb() = runBlocking{
        val vocab1 = Vocabulary(0, "a", false, "")
        val vocab2 = Vocabulary(1, "b", false, "")
        val vocab3 = Vocabulary(2, "c", false, "")

        vocabularyDao.insert(vocab1)
        vocabularyDao.insert(vocab2)
        vocabularyDao.insert(vocab3)

        vocabularyDao.deleteVocabularyById(1)


        val allVocabFromDb = vocabularyDao.getAll()

        assertEquals(allVocabFromDb.getOrAwaitValue().size, 2)

        assertEquals(allVocabFromDb.getOrAwaitValue()[0], vocab1)
        assertEquals(allVocabFromDb.getOrAwaitValue()[1], vocab3)

    }

}
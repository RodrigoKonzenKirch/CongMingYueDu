package android.practice.com.congmingyuedu

import android.practice.com.congmingyuedu.model.ChineseText
import android.practice.com.congmingyuedu.model.TextDao
import android.practice.com.congmingyuedu.model.Vocabulary
import android.practice.com.congmingyuedu.model.VocabularyDao
import androidx.annotation.WorkerThread

class TextRepository(private val textDao: TextDao, private val vocabularyDao: VocabularyDao) {

    @WorkerThread
    fun insertText(chineseText: ChineseText){
        textDao.insert(chineseText)
    }

    fun insertVocabulary(vocabulary: Vocabulary){
        vocabularyDao.insert(vocabulary)
    }
}
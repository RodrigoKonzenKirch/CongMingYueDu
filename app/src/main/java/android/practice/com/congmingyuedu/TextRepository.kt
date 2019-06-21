package android.practice.com.congmingyuedu

import android.practice.com.congmingyuedu.model.ChineseText
import android.practice.com.congmingyuedu.model.TextDao
import android.practice.com.congmingyuedu.model.Vocabulary
import android.practice.com.congmingyuedu.model.VocabularyDao
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TextRepository(private val textDao: TextDao, private val vocabularyDao: VocabularyDao) {

    val allVocabulary: LiveData<List<Vocabulary>> = vocabularyDao.getAll()

    @WorkerThread
    fun insertText(chineseText: ChineseText){
        textDao.insert(chineseText)
    }

    fun insertVocabulary(vocabulary: Vocabulary){
        if (!vocabularyIsOnDatabase(vocabulary)){
            vocabularyDao.insert(vocabulary)
        }
    }

    private fun vocabularyIsOnDatabase(vocabulary: Vocabulary):Boolean{
        var isOnDatabase = false
        val allVocabularyTemp = allVocabulary.value
        if (!allVocabularyTemp.isNullOrEmpty()) {
            allVocabularyTemp.forEach {
                if (vocabulary.vocabularyContent == it.vocabularyContent)
                    isOnDatabase=true
            }
        }
        return isOnDatabase
    }
}
package android.practice.com.congmingyuedu

import android.practice.com.congmingyuedu.model.ChineseText
import android.practice.com.congmingyuedu.model.ChineseTextDao
import android.practice.com.congmingyuedu.model.Vocabulary
import android.practice.com.congmingyuedu.model.VocabularyDao
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TextRepository(private val chineseTextDao: ChineseTextDao, private val vocabularyDao: VocabularyDao) {

    val allVocabulary: LiveData<List<Vocabulary>> = vocabularyDao.getAll()
//    val currentText: LiveData<ChineseText>

    @WorkerThread
    fun insertText(chineseText: ChineseText){
        chineseTextDao.insert(chineseText)
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
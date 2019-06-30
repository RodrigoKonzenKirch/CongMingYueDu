package android.practice.com.congmingyuedu

import android.content.Context
import android.practice.com.congmingyuedu.model.*
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TextRepository(private val chineseTextDao: ChineseTextDao, private val vocabularyDao: VocabularyDao, context: Context) {

    val allVocabulary: LiveData<List<Vocabulary>> = vocabularyDao.getAll()
    private val sharedPref: SharedPreference = SharedPreference(context)
    var currentText: LiveData<ChineseText> = chineseTextDao.getTextById(sharedPref.getValueInt(sharedPref.PREF_NAME))

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

    fun setCurrentTextId(id:Int){
        sharedPref.save(sharedPref.PREF_NAME, id)
    }
}
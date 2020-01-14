package android.practice.com.congmingyuedu

import android.content.Context
import android.practice.com.congmingyuedu.model.*
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TextRepository(private val chineseTextDao: ChineseTextDao, private val vocabularyDao: VocabularyDao,
                     private val chineseDictionaryDao: ChineseDictionaryDao, context: Context) {

    val allVocabulary: LiveData<List<Vocabulary>> = vocabularyDao.getAll()
    //val allVocabularyWithDictDefinition: LiveData<List<ChineseDictionary>> = getVocabWithDefinition()
    val allTexts: LiveData<List<ChineseText>> = chineseTextDao.getAll()
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

//    private fun getVocabWithDefinition():MutableLiveData<List<ChineseDictionary>>{
//        val resultList = MutableLiveData<List<ChineseDictionary>>()
//        val allVocabularyTemp = allVocabulary.value
//        val tempListOfWords = mutableListOf<ChineseDictionary>()
//
//        if (!allVocabularyTemp.isNullOrEmpty()) {
//            allVocabularyTemp.forEach{
//                val wordWithDefinition = getWordFromChineseDictionary(it.vocabularyContent)
//
//                if (wordWithDefinition.wordSimplified.isBlank()){
//                    tempListOfWords.add(ChineseDictionary(0, "", it.vocabularyContent, "", ""))
//                } else {
//                    tempListOfWords.add(wordWithDefinition)
//                }
//            }
//            resultList.value = tempListOfWords
//        }
//        return resultList
//    }

    fun setCurrentTextId(id:Int){
        sharedPref.save(sharedPref.PREF_NAME, id)
    }

    suspend fun getWordFromChineseDictionary(word: String): ChineseDictionary {
        return chineseDictionaryDao.getWord(word)
    }

    fun setVocabularyStarred(isStarred: Boolean, id: Long){
        vocabularyDao.setVocabularyStarred(isStarred, id)
    }

    suspend fun getVocabularyById(id: Long): Vocabulary {
        return vocabularyDao.getVocabularyById(id)
    }

    fun setCurrentTextPage(page: Int, id: Int){
        chineseTextDao.setPageById(page, id)
    }
}
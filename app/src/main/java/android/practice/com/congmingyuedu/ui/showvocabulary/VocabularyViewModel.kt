package android.practice.com.congmingyuedu.ui.showvocabulary

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseDictionary
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.data.local.VocabularyDetails
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository

    val allVocabulary: LiveData<List<Vocabulary>>
    var vocabularyDetails : MutableLiveData<VocabularyDetails>

    init {
        val textDao = AppDatabase.getInstance(application)!!.textDao()
        val vocabularyDao = AppDatabase.getInstance(application)!!.vocabularyDao()
        val dictionaryDao = AppDatabase.getInstance(application)!!.chineseDictionaryDao()
        repository = TextRepository(
            textDao,
            vocabularyDao,
            dictionaryDao,
            application
        )
        allVocabulary = repository.allVocabulary
        vocabularyDetails = MutableLiveData(
            VocabularyDetails(
                "simp",
                "trad",
                "pin",
                "tran",
                "info",
                "ex"
            )
        )
    }

    fun insertVocabulary(vocabulary: Vocabulary) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertVocabulary(vocabulary)
    }

    fun setVocabularyStared(isStarred: Boolean, id: Long) = viewModelScope.launch(Dispatchers.IO){
        repository.setVocabularyStarred(isStarred, id)
    }

    private suspend fun getWordFromChineseDictionary(word: String): ChineseDictionary {
        return repository.getWordFromChineseDictionary(word)
    }

    private suspend fun getVocabularyById(id: Long): Vocabulary {
        return repository.getVocabularyById(id)
    }

    fun setUpVocabularyDetailsById(id: Long) {
        var vocabularyContentFromDictionary: ChineseDictionary
        var word: Vocabulary

        viewModelScope.launch(Dispatchers.IO){
            word = getVocabularyById(id)
            vocabularyContentFromDictionary = getWordFromChineseDictionary(word.vocabularyContent)

            if (vocabularyContentFromDictionary == null || vocabularyContentFromDictionary.wordSimplified.isNullOrEmpty()){

                vocabularyDetails.value?.simplified = word.vocabularyContent
                vocabularyDetails.value?.info = word.vocabularyExtraInfo
            }else {
                vocabularyDetails.value?.simplified = vocabularyContentFromDictionary.wordSimplified
                vocabularyDetails.value?.traditional =
                    vocabularyContentFromDictionary.wordTraditional
                vocabularyDetails.value?.pinyin = vocabularyContentFromDictionary.wordPinyin
                vocabularyDetails.value?.translation =
                    vocabularyContentFromDictionary.wordTranslation
                vocabularyDetails.value?.info = word.vocabularyExtraInfo

            }
            //TODO populate vocabularyExamples
            vocabularyDetails.value?.examples = "Examples"
        }
    }
}
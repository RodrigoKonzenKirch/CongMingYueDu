package android.practice.com.congmingyuedu.ui.vocabularydetails

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseDictionary
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.data.local.VocabularyDetails
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository

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
        vocabularyDetails = MutableLiveData(
            VocabularyDetails(
                false,
                "simp",
                "trad",
                "pin",
                "tran",
                "info",
                "ex"
            )
        )
    }

    private suspend fun getWordFromChineseDictionary(word: String): ChineseDictionary {
        return repository.getWordFromChineseDictionary(word)
    }

    private suspend fun getVocabularyById(id: Long): Vocabulary {
        return repository.getVocabularyById(id)
    }

    // get current star status from DB and set the opposite
    fun invertStarStatusById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        repository.setVocabularyStarred(!repository.getVocabularyById(id).vocabularyStarred, id)
    }

    fun setUpVocabularyDetailsById(id: Long) {
        var vocabularyContentFromDictionary: ChineseDictionary
        var word: Vocabulary

        viewModelScope.launch(Dispatchers.IO){
            word = getVocabularyById(id)
            vocabularyContentFromDictionary = getWordFromChineseDictionary(word.vocabularyContent)

            if (vocabularyContentFromDictionary == null || vocabularyContentFromDictionary.wordSimplified.isNullOrEmpty()){

                vocabularyDetails.value?.isStared = word.vocabularyStarred
                vocabularyDetails.value?.simplified = word.vocabularyContent
                vocabularyDetails.value?.info = word.vocabularyExtraInfo
            }else {
                vocabularyDetails.value?.isStared = word.vocabularyStarred
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

    fun setVocabularyStarred(isStarred: Boolean, id: Long) = viewModelScope.launch(Dispatchers.IO) {
        repository.setVocabularyStarred(isStarred, id)
    }

    fun deleteVocabularyById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteVocabularyById(id)
        }
    }
}
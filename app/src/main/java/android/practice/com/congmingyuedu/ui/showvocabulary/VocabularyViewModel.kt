package android.practice.com.congmingyuedu.ui.showvocabulary

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.Vocabulary
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository

    val allVocabulary: LiveData<List<Vocabulary>>

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
    }

    fun insertVocabulary(vocabulary: Vocabulary) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertVocabulary(vocabulary)
    }

    fun setVocabularyStared(isStarred: Boolean, id: Long) = viewModelScope.launch(Dispatchers.IO){
        repository.setVocabularyStarred(isStarred, id)
    }

}
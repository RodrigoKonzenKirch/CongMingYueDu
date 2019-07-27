package android.practice.com.congmingyuedu.viewmodel

import android.app.Application
import android.practice.com.congmingyuedu.TextRepository
import android.practice.com.congmingyuedu.model.AppDatabase
import android.practice.com.congmingyuedu.model.ChineseText
import android.practice.com.congmingyuedu.model.Vocabulary
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TextViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository
    val allVocabulary: LiveData<List<Vocabulary>>
    val allTexts: LiveData<List<ChineseText>>
    val currentText: LiveData<ChineseText>

    init {
        val textDao = AppDatabase.getInstance(application)!!.textDao()
        val vocabularyDao = AppDatabase.getInstance(application)!!.vocabularyDao()
        val dictionaryDao = AppDatabase.getInstance(application)!!.chineseDictionaryDao()
        repository = TextRepository(textDao, vocabularyDao, dictionaryDao, application)
        allVocabulary = repository.allVocabulary
        allTexts = repository.allTexts
        currentText = repository.currentText
    }

    fun insertText(chineseText: ChineseText) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertText(chineseText)
    }

    fun insertVocabulary(vocabulary: Vocabulary) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertVocabulary(vocabulary)
    }

    fun setCurrentText(id: Int){
        repository.setCurrentTextId(id)
    }
}

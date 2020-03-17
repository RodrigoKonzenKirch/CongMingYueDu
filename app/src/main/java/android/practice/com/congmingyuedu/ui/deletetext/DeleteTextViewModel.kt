package android.practice.com.congmingyuedu.ui.deletetext

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseText
import android.practice.com.congmingyuedu.data.local.Vocabulary
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteTextViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository

    val allTexts: LiveData<List<ChineseText>>

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
        allTexts = repository.allTexts
    }

    fun deleteTextById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTextById(id)
        }
    }

}

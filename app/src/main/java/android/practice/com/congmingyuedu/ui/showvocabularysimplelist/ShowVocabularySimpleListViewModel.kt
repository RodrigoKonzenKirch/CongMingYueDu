package android.practice.com.congmingyuedu.ui.showvocabularysimplelist

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.Vocabulary
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ShowVocabularySimpleListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository
    var vocabularyList: LiveData<List<Vocabulary>>

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
        vocabularyList = repository.allVocabulary
    }
}
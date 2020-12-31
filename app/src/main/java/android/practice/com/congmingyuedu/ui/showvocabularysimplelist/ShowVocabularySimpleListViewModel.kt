package android.practice.com.congmingyuedu.ui.showvocabularysimplelist

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.Vocabulary
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ShowVocabularySimpleListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository = TextRepository(
        AppDatabase.getInstance(application)!!,
        application
    )
    var vocabularyList: LiveData<List<Vocabulary>>

    init {
        vocabularyList = repository.allVocabulary
    }
}
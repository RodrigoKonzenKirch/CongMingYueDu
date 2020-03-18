package android.practice.com.congmingyuedu.ui.glossary

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.Vocabulary
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class GlossaryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository
    val vocabularyList: LiveData<List<Vocabulary>>
    var mCurrentText : LiveData<String>

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
        mCurrentText = repository.getCurrentTextAsString()
    }
}

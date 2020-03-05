package android.practice.com.congmingyuedu.ui.text

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TextViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository

    val currentText: LiveData<ChineseText>

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
        currentText = repository.currentText
    }

}

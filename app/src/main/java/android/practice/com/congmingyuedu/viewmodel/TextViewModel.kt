package android.practice.com.congmingyuedu.viewmodel

import android.app.Application
import android.practice.com.congmingyuedu.TextRepository
import android.practice.com.congmingyuedu.model.AppDatabase
import android.practice.com.congmingyuedu.model.ChineseText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TextViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository

    init {
        val textDao = AppDatabase.getInstance(application)!!.textDao()
        repository = TextRepository(textDao)
    }

    fun insertText(chineseText: ChineseText) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertText(chineseText)
    }
    // TODO: Implement the ViewModel
}

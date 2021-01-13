/* 聪明阅读(CongMingYueDu) Chinese text reader with tools to learn vocabulary
Copyright (C) 2020 Rodrigo Konzen Kirch

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.*/

package android.practice.com.congmingyuedu.ui.vocabularydetails

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseDictionary
import android.practice.com.congmingyuedu.data.local.Vocabulary
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocabularyDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository = TextRepository(
        AppDatabase.getInstance(application)!!,
        application
    )
    var allVocab:LiveData<List<Vocabulary>>

    init {
        allVocab = repository.allVocabulary
    }

     suspend fun getWordFromChineseDictionary(word: String): ChineseDictionary {
        return repository.getWordFromChineseDictionary(word)
    }

    // get current star status from DB and set the opposite
    fun invertStarStatusById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        repository.setVocabularyStarred(!repository.getVocabularyById(id).vocabularyStarred, id)
    }

    fun deleteVocabularyById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteVocabularyById(id)
        }
    }
}
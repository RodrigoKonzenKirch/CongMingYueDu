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

        viewModelScope.launch{
            word = getVocabularyById(id)
            vocabularyContentFromDictionary = getWordFromChineseDictionary(word.vocabularyContent)

            if (vocabularyContentFromDictionary == null || vocabularyContentFromDictionary.wordSimplified.isNullOrEmpty()){

                vocabularyDetails = MutableLiveData(
                    VocabularyDetails(word.vocabularyStarred,
                    word.vocabularyContent,
                    "",
                    "",
                    "",
                    word.vocabularyExtraInfo,
                    "Examples to be implemented")
                )
            }else {
                vocabularyDetails = MutableLiveData(VocabularyDetails(
                    word.vocabularyStarred,
                    vocabularyContentFromDictionary.wordSimplified,
                    vocabularyContentFromDictionary.wordSimplified,
                    vocabularyContentFromDictionary.wordPinyin,
                    vocabularyContentFromDictionary.wordTranslation,
                    word.vocabularyExtraInfo, "Examples to be implemented"))

            }
            //TODO populate vocabularyExamples
        }
    }

    fun deleteVocabularyById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteVocabularyById(id)
        }
    }
}
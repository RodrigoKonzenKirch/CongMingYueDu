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

package android.practice.com.congmingyuedu.ui.deletetext

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.ChineseText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteTextViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository = TextRepository(
        AppDatabase.getInstance(application)!!,
        application
    )

    val allTexts: LiveData<List<ChineseText>>

    init {
//        val textDao = AppDatabase.getInstance(application)!!.textDao()
//        val vocabularyDao = AppDatabase.getInstance(application)!!.vocabularyDao()
//        val dictionaryDao = AppDatabase.getInstance(application)!!.chineseDictionaryDao()
        allTexts = repository.allTexts
    }

    fun deleteTextById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTextById(id)
        }
    }

}

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

package android.practice.com.congmingyuedu.ui.glossary

import android.app.Application
import android.practice.com.congmingyuedu.data.TextRepository
import android.practice.com.congmingyuedu.data.local.AppDatabase
import android.practice.com.congmingyuedu.data.local.Vocabulary
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class GlossaryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TextRepository = TextRepository(
        AppDatabase.getInstance(application)!!,
        application
    )
    val vocabularyList: LiveData<List<Vocabulary>>
    var mCurrentText : LiveData<String>

    init {
        vocabularyList = repository.allVocabulary
        mCurrentText = repository.getCurrentTextAsString()
    }
}

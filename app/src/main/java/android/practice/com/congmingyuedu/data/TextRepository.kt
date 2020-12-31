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

package android.practice.com.congmingyuedu.data

import android.content.Context
import android.practice.com.congmingyuedu.data.local.*
import androidx.lifecycle.LiveData

class TextRepository(private val database: AppDatabase, context: Context) {

    val allVocabulary: LiveData<List<Vocabulary>> = database.vocabularyDao().getAll()
    val allTexts: LiveData<List<ChineseText>> = database.textDao().getAll()
    private val sharedPref: SharedPreference =
        SharedPreference(context)
    var currentText: LiveData<ChineseText> = database.textDao().getTextById(sharedPref.getValueInt(sharedPref.PREF_NAME))

    fun insertText(chineseText: ChineseText){
        database.textDao().insert(chineseText)
    }

    fun insertVocabulary(vocabulary: Vocabulary){
            database.vocabularyDao().insert(vocabulary)
    }

    fun setCurrentTextId(id:Int){
        sharedPref.save(sharedPref.PREF_NAME, id)
    }

    suspend fun getWordFromChineseDictionary(word: String): ChineseDictionary {
        return database.chineseDictionaryDao().getWord(word)
    }

    fun setVocabularyStarred(isStarred: Boolean, id: Long){
        database.vocabularyDao().setVocabularyStarred(isStarred, id)
    }

    suspend fun getVocabularyById(id: Long): Vocabulary {
        return database.vocabularyDao().getVocabularyById(id)
    }

    suspend fun deleteVocabularyById(id: Long){
        database.vocabularyDao().deleteVocabularyById(id)
    }

    suspend fun deleteTextById(id: Long){
        database.textDao().deleteTextById(id)

    }

    fun getCurrentTextAsString(): LiveData<String>{
        return database.textDao().getTextContentById(sharedPref.getValueInt(sharedPref.PREF_NAME).toLong())
    }

}
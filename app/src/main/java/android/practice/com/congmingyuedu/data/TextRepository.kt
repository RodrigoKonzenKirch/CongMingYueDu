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
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TextRepository(private val chineseTextDao: ChineseTextDao, private val vocabularyDao: VocabularyDao,
                     private val chineseDictionaryDao: ChineseDictionaryDao, context: Context) {

    val allVocabulary: LiveData<List<Vocabulary>> = vocabularyDao.getAll()
    val allTexts: LiveData<List<ChineseText>> = chineseTextDao.getAll()
    private val sharedPref: SharedPreference =
        SharedPreference(context)
    var currentText: LiveData<ChineseText> = chineseTextDao.getTextById(sharedPref.getValueInt(sharedPref.PREF_NAME))

    @WorkerThread
    fun insertText(chineseText: ChineseText){
        chineseTextDao.insert(chineseText)
    }

    fun insertVocabulary(vocabulary: Vocabulary){
        if (!vocabularyAlreadyExist(vocabulary)){
            vocabularyDao.insert(vocabulary)
        }
    }

    private fun vocabularyAlreadyExist(vocabulary: Vocabulary):Boolean{
        var alreadyExist = false
        val allVocabularyTemp = allVocabulary.value
        if (!allVocabularyTemp.isNullOrEmpty()) {
            allVocabularyTemp.forEach {
                if (vocabulary.vocabularyContent == it.vocabularyContent)
                    alreadyExist=true
            }
        }
        return alreadyExist
    }

    fun setCurrentTextId(id:Int){
        sharedPref.save(sharedPref.PREF_NAME, id)
    }

    suspend fun getWordFromChineseDictionary(word: String): ChineseDictionary {
        return chineseDictionaryDao.getWord(word)
    }

    fun setVocabularyStarred(isStarred: Boolean, id: Long){
        vocabularyDao.setVocabularyStarred(isStarred, id)
    }

    suspend fun getVocabularyById(id: Long): Vocabulary {
        return vocabularyDao.getVocabularyById(id)
    }

    suspend fun deleteVocabularyById(id: Long){
        vocabularyDao.deleteVocabularyById(id)
    }

    suspend fun deleteTextById(id: Long){
        chineseTextDao.deleteTextById(id)

    }

    fun getCurrentTextAsString(): LiveData<String>{
        return chineseTextDao.getTextContentById(sharedPref.getValueInt(sharedPref.PREF_NAME).toLong())
    }

}
package android.practice.com.congmingyuedu

import android.practice.com.congmingyuedu.model.ChineseText
import android.practice.com.congmingyuedu.model.TextDao
import androidx.annotation.WorkerThread

class TextRepository(private val textDao: TextDao) {



    @WorkerThread
    fun insertText(chineseText: ChineseText){
        textDao.insert(chineseText)
    }
}
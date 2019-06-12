package android.practice.com.congmingyuedu

import android.practice.com.congmingyuedu.model.Text
import android.practice.com.congmingyuedu.model.TextDao
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TextRepository(private val textDao: TextDao) {



    @WorkerThread
    suspend fun insert(text: Text){
        textDao.insert(text)
    }
}
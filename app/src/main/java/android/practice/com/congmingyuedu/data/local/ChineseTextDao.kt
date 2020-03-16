package android.practice.com.congmingyuedu.data.local

import android.practice.com.congmingyuedu.data.local.ChineseText
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChineseTextDao {

    @Query("SELECT * FROM texts ORDER BY text_title")
    fun getAll(): LiveData<List<ChineseText>>

    @Query("SELECT * FROM texts WHERE id = :id LIMIT 1")
    fun getTextById(id: Int): LiveData<ChineseText>

    @Insert
    fun insert(chineseText: ChineseText)

    @Query("UPDATE texts SET page = :page WHERE id = :id")
    fun setPageById(page: Int, id: Int)
}
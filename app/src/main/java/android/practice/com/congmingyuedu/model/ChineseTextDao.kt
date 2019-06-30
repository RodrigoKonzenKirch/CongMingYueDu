package android.practice.com.congmingyuedu.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChineseTextDao {

    @Query("SELECT * FROM texts")
    fun getAll(): List<ChineseText>

    @Query("SELECT * from texts where id = :id LIMIT 1")
    fun getTextById(id: Int): LiveData<ChineseText>

    @Insert
    fun insert(chineseText: ChineseText)

}
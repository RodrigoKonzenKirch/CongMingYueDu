package android.practice.com.congmingyuedu.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TextDao {

    @Query("SELECT * FROM texts")
    fun getAll(): List<ChineseText>

    @Query("SELECT * from texts where id = :id LIMIT 1")
    fun getTextById(id: Int): ChineseText

    @Insert
    fun insert(chineseText: ChineseText)

}
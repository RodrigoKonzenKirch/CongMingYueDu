package android.practice.com.congmingyuedu.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TextDao {

    @Query("SELECT * FROM texts")
    fun getAll(): List<Text>

    @Query("SELECT * from texts where id = :id LIMIT 1")
    fun getTextById(id: Int): Text

    @Insert
    fun insert(text: Text)

}
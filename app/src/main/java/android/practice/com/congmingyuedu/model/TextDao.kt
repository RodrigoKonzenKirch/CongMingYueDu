package android.practice.com.congmingyuedu.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TextDao {

    @Query("SELECT * FROM texts")
    fun getAll(): List<Text>

    @Insert
    fun insert(text: Text)

}
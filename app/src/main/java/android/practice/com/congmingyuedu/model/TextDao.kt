package android.practice.com.congmingyuedu.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TextDao {

    @Query("SELECT * FROM texts")
    fun getAll(): List<Text>

    @Insert
    fun insert(text: Text)

}
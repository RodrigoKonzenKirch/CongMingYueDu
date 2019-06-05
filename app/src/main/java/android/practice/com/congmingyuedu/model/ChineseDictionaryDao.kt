package android.practice.com.congmingyuedu.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChineseDictionaryDao {

    @Query("SELECT * FROM chinese_dictionary")
    fun getAll(): List<ChineseDictionary>

    @Insert
    fun insert(chineseDictionary: ChineseDictionary)

}
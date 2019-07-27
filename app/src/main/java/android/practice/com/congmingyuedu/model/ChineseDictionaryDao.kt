package android.practice.com.congmingyuedu.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChineseDictionaryDao {

    @Query("SELECT * FROM chinese_dictionary")
    fun getAll(): List<ChineseDictionary>

    @Query("SELECT COUNT(id) FROM chinese_dictionary")
    fun getCountDictionary(): Int

    @Insert
    fun insert(chineseDictionary: ChineseDictionary)

    @Insert
    fun insertAll(chineseDictionary: List<ChineseDictionary>)


}
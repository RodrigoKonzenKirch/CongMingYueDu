package android.practice.com.congmingyuedu.data.local

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

    @Query("SELECT text_content FROM texts WHERE id = :id")
    fun getTextContentById(id: Long): LiveData<String>

    @Insert
    fun insert(chineseText: ChineseText)

    @Query("UPDATE texts SET page = :page WHERE id = :id")
    fun setPageById(page: Int, id: Int)

    @Query("DELETE FROM texts WHERE id = :id")
    suspend fun deleteTextById(id: Long)
}
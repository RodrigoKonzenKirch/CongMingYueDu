package android.practice.com.congmingyuedu.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VocabularyDao {

    @Query("SELECT * FROM vocabulary ORDER BY vocabulary_content ASC")
    fun getAll(): LiveData<List<Vocabulary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vocabulary: Vocabulary)

    @Query("UPDATE vocabulary SET vocabulary_starred = :isStarred WHERE id = :id")
    fun setVocabularyStarred(isStarred: Boolean, id: Long)

    @Query("SELECT * FROM vocabulary WHERE id = :id")
    suspend fun getVocabularyById(id: Long): Vocabulary

    @Query("DELETE FROM vocabulary WHERE id = :id")
    suspend fun deleteVocabularyById(id: Long)
}
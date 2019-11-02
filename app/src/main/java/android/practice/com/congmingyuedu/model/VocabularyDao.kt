package android.practice.com.congmingyuedu.model

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
}
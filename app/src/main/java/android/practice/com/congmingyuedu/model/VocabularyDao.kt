package android.practice.com.congmingyuedu.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VocabularyDao {

    @Query("SELECT * FROM vocabulary ORDER BY vocabulary_content ASC")
    fun getAll(): LiveData<List<Vocabulary>>

    @Insert
    fun insert(vocabulary: Vocabulary)
}
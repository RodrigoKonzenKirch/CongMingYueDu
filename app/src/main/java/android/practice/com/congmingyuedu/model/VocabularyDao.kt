package android.practice.com.congmingyuedu.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VocabularyDao {

    @Query("SELECT * FROM vocabulary")
    fun getAll(): List<Vocabulary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vocabulary: Vocabulary)
}
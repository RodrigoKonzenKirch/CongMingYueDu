/* 聪明阅读(CongMingYueDu) Chinese text reader with tools to learn vocabulary
Copyright (C) 2020 Rodrigo Konzen Kirch

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.*/

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
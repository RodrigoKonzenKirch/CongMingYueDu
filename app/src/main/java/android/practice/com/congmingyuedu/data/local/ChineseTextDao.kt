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

    @Query("DELETE FROM texts WHERE id = :id")
    suspend fun deleteTextById(id: Long)
}
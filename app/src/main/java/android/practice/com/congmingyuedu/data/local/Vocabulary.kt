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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary", indices = [Index(value = ["vocabulary_content"], unique = true)])
data class Vocabulary(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name="vocabulary_content") var vocabularyContent: String,
    @ColumnInfo(name = "vocabulary_starred") var vocabularyStarred: Boolean,
    @ColumnInfo(name = "vocabulary_extra_info") var vocabularyExtraInfo: String
) {
    constructor() : this(null, "", false, "")
}
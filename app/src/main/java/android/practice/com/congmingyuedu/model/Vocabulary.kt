package android.practice.com.congmingyuedu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary", indices = [Index(value = ["vocabulary_content"], unique = true)])
data class Vocabulary(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name="vocabulary_content") var vocabularyContent: String,
    @ColumnInfo(name = "vocabulary_starred") var vocabularyStarred: Boolean
) {
    constructor() : this(null, "", false)
}
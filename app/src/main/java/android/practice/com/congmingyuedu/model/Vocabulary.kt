package android.practice.com.congmingyuedu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary")
data class Vocabulary(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name="vocabulary_content") var vocabularyContent: String
) {
    constructor() : this(null, "")
}
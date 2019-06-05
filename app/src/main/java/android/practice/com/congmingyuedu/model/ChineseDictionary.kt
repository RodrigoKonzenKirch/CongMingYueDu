package android.practice.com.congmingyuedu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chinese_dictionary")
data class ChineseDictionary(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "word_traditional") var wordTraditional: String,
    @ColumnInfo(name = "word_simplified") var wordSimplified: String,
    @ColumnInfo(name = "word_pinyin") var wordPinyin: String,
    @ColumnInfo(name = "word_translation") var wordTranslation: String
) {
    constructor() : this(null, "", "", "", "")
}
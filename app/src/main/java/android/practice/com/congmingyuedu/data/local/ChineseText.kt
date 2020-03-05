package android.practice.com.congmingyuedu.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "texts")
data class ChineseText(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name="text_title") var textTitle: String,
    @ColumnInfo(name = "text_content") var textContent: String,
    @ColumnInfo(name = "page") var page: Int
) {
    constructor() : this(null, "", "", 0)
}

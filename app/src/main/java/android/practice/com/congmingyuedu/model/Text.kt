package android.practice.com.congmingyuedu.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "texts")
data class Text(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name="text_title") var textTitle: String,
    @ColumnInfo(name = "text_content") var textContent: String
) {
    constructor() : this(null, "", "")
}

package android.practice.com.congmingyuedu.data.local

data class VocabularyDetails(
    var isStared: Boolean = false,
    var simplified: String = "",
    var traditional: String = "",
    var pinyin: String = "",
    var translation: String = "",
    var info: String = "",
    var examples: String = "")
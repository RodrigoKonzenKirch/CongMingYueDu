package android.practice.com.congmingyuedu.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {
    val PREF_NAME = "text_options"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, value: Int){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.commit()
    }

    fun getValueInt(KEY_NAME: String): Int{
        return (sharedPref.getInt(KEY_NAME, 1))
    }
}
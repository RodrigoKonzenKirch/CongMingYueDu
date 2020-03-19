package android.practice.com.congmingyuedu.ui.settings

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}
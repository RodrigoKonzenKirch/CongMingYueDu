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

package android.practice.com.congmingyuedu.ui.addvocabulary

import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.utils.hideKeyboard
import android.practice.com.congmingyuedu.ui.showvocabulary.VocabularyViewModel
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_vocabulary.*

class AddVocabularyFragment : Fragment() {

    private val vocabularyViewModel: VocabularyViewModel by viewModels()
    private lateinit var myClipboard: ClipboardManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_vocabulary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        myClipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        editTextAddVocabulary.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buttonAddVocabulary.isEnabled = !editTextAddVocabulary.text.isBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        imageButtonClipboard.setOnClickListener {
            val mClipboardContent = myClipboard.primaryClip
            editTextAddVocabulary.text.append(mClipboardContent?.getItemAt(0)?.text.toString())
        }

        buttonAddVocabulary.setOnClickListener {
            vocabularyViewModel.insertVocabulary(
                Vocabulary(
                    null,
                    editTextAddVocabulary.text.toString(),
                    false,
                    editTextExtraInfo.text.toString()
                )
            )
            editTextAddVocabulary.text.clear()
            editTextExtraInfo.text.clear()
            hideKeyboard()
            Snackbar.make(
                addVocabularyLayout,
                resources.getString(R.string.add_vocabulary_success),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

}

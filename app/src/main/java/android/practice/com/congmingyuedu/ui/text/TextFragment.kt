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

package android.practice.com.congmingyuedu.ui.text

import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.utils.hideKeyboard
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_text.*
import kotlinx.android.synthetic.main.fragment_text.editTextAddVocabulary

class TextFragment : Fragment() {

    private val textViewModel: TextViewModel by viewModels()
    private lateinit var myClipboard: ClipboardManager

    companion object {
        const val KEY_PREF_TEXT_FONT_SIZE = "MAIN_TEXT_FONT_SIZE"
        const val KEY_PREF_HIGHLIGHT_COLOR = "COLOR_OF_HIGHLIGHTING"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val highlightColorDexCode: String
        val sharedPref: SharedPreferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val textPref: Int = sharedPref.getInt(KEY_PREF_TEXT_FONT_SIZE, 16)
        textViewTextFragment.textSize = textPref.toFloat()

        val highlightColorPref: String? = sharedPref.getString(KEY_PREF_HIGHLIGHT_COLOR, "Yellow")
        highlightColorDexCode = when (highlightColorPref) {
            "Yellow" -> "#FFFF55"
            "Green" -> "#90EE90"
            "Blue" -> "#99CCFF"
            "Grey" -> "#D3D3D3"
            else -> "#FFFF55"
        }

        var mCurrentTextAsString = ""
        var mVocabularyList = listOf<Vocabulary>()

        textViewModel.currentText.observe(viewLifecycleOwner, {
            if(it != null){
                activity?.title = it.textTitle
                mCurrentTextAsString = it.textContent
            }

            if (mVocabularyList.isNotEmpty()) {
                textViewTextFragment.text =
                    highlightText(mCurrentTextAsString, mVocabularyList, highlightColorDexCode)
            }
        })

        textViewModel.vocabularyList.observe(viewLifecycleOwner, {
            mVocabularyList = it
            if (mCurrentTextAsString.isNotEmpty()) {
                textViewTextFragment.text =
                    highlightText(mCurrentTextAsString, mVocabularyList, highlightColorDexCode)
            }
        })

        buttonTextFragmentFlipToGlossaryLeft.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.glossaryFragment)
        }

        buttonTextFragmentFlipToGlossaryRight.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.glossaryFragment)
        }

        buttonTextFragmentToAddVocabulary.setOnClickListener {
            when(constraintLayoutAddVocabulary.visibility) {
                View.GONE -> constraintLayoutAddVocabulary.visibility = View.VISIBLE
                View.VISIBLE -> constraintLayoutAddVocabulary.visibility = View.GONE
            }
            //nav_host_fragment.findNavController().navigate(R.id.addVocabularyFragment)
        }

        myClipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        editTextAddVocabulary.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buttonAddVocabulary.isEnabled = editTextAddVocabulary.text.isNotBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        imageButtonClipboard.setOnClickListener {
            val mClipboardContent = myClipboard.primaryClip
            editTextAddVocabulary.text.append(mClipboardContent?.getItemAt(0)?.text.toString())
        }

        buttonAddVocabulary.setOnClickListener {
            textViewModel.insertVocabulary(
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
                constraintLayoutTextFragment,
                resources.getString(R.string.add_vocabulary_success),
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    private fun highlightText(
        textToBeHighlighted: String,
        vocabularyList: List<Vocabulary>,
        highlightColor: String
    ): SpannableString {
        // Get indexes of words in the string containing the text to be highlighted
        val indexesAndWordSizes = mutableListOf<Pair<Int, Int>>()

        if (vocabularyList.isNotEmpty()) {
            vocabularyList.forEach {
                var index = textToBeHighlighted.indexOf(it.vocabularyContent, 0)
                while (index != -1) {
                    indexesAndWordSizes.add(Pair(index, it.vocabularyContent.length))
                    index = textToBeHighlighted.indexOf(it.vocabularyContent, index + 1)
                }
            }
        }

        // Highlight words using list of indexes of each word
        val result = SpannableString(textToBeHighlighted)
        indexesAndWordSizes.forEach {
            result.setSpan(
                BackgroundColorSpan(Color.parseColor(highlightColor)),
                it.first,
                it.first + it.second,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return result
    }

}

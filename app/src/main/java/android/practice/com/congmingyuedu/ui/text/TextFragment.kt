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

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.text_fragment.*

class TextFragment : Fragment() {

    private val textViewModel: TextViewModel by viewModels()
    companion object{
        const val KEY_PREF_TEXT_FONT_SIZE = "MAIN_TEXT_FONT_SIZE"
        const val KEY_PREF_HIGHLIGHT_COLOR = "COLOR_OF_HIGHLIGHTING"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.text_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val highlightColorDexCode: String
        val sharedPref: SharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        val textPref: Int = sharedPref.getInt(KEY_PREF_TEXT_FONT_SIZE, 16)
        textViewTextFragment.textSize = textPref.toFloat()

        val highlightColorPref: String? = sharedPref.getString(KEY_PREF_HIGHLIGHT_COLOR, "Yellow")
        highlightColorDexCode = when(highlightColorPref){
            "Yellow" -> "#FFFF55"
            "Green" -> "#90EE90"
            "Blue" -> "#99CCFF"
            "Grey" -> "#D3D3D3"
            else -> "#FFFF55"
        }

        var mCurrentTextAsString = ""
        var mVocabularyList = listOf<Vocabulary>()

        textViewModel.currentText.observe(viewLifecycleOwner, Observer {
            mCurrentTextAsString = it.textContent
            if (mVocabularyList.isNotEmpty()){
                textViewTextFragment.text = highlightText(mCurrentTextAsString, mVocabularyList, highlightColorDexCode)
            }
        })

        textViewModel.vocabularyList.observe(viewLifecycleOwner, Observer {
            mVocabularyList = it
            if (mCurrentTextAsString.isNotEmpty()){
                textViewTextFragment.text = highlightText(mCurrentTextAsString, mVocabularyList, highlightColorDexCode)
            }
        })


        buttonTextFragmentFlipToGlossaryLeft.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.glossaryFragment)
        }

        buttonTextFragmentFlipToGlossaryRight.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.glossaryFragment)
        }

        buttonTextFragmentToAddVocabulary.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.addVocabularyFragment)
        }
    }

    private fun highlightText(textToBeHighlighted: String, vocabularyList: List<Vocabulary>, highlightColor: String): SpannableString{
        // Get indexes of words in the string containing the text to be highlighted
        val indexesAndWordSizes = mutableListOf<Pair<Int, Int>>()

        if (vocabularyList.isNotEmpty()){
            vocabularyList.forEach{
                var index = textToBeHighlighted.indexOf(it.vocabularyContent, 0)
                while (index != -1){
                    indexesAndWordSizes.add(Pair(index, it.vocabularyContent.length))
                    index = textToBeHighlighted.indexOf(it.vocabularyContent, index+1)
                }
            }
        }

        // Highlight words using list of indexes of each word
        val result = SpannableString(textToBeHighlighted)
        indexesAndWordSizes.forEach{
            result.setSpan(BackgroundColorSpan(Color.parseColor(highlightColor)), it.first, it.first+it.second,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )
        }
        return result
    }

    /*
     *
     * green 90ee90
     * blue 99ccff
     * grey d3d3d3
     * yellow ffff55
     *
     *
     * **/

}

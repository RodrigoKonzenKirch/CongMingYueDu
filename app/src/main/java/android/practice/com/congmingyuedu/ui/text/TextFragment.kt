package android.practice.com.congmingyuedu.ui.text

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.text_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var mCurrentTextAsString = ""
        var mVocabularyList = listOf<Vocabulary>()

        textViewModel.currentText.observe(this, Observer {
            mCurrentTextAsString = it.textContent
            if (mVocabularyList.isNotEmpty()){
                textViewTextFragment.text = highlightText(mCurrentTextAsString, mVocabularyList)
            }
        })

        textViewModel.vocabularyList.observe(this, Observer {
            mVocabularyList = it
            if (mCurrentTextAsString.isNotEmpty()){
                textViewTextFragment.text = highlightText(mCurrentTextAsString, mVocabularyList)
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

    private fun highlightText(textToBeHighlighted: String, vocabularyList: List<Vocabulary>): SpannableString{
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
            result.setSpan(BackgroundColorSpan(Color.parseColor("#FFFF55")), it.first, it.first+it.second,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )
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

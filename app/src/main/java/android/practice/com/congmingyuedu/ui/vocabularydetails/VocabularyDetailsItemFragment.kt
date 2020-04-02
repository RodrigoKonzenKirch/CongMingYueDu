package android.practice.com.congmingyuedu.ui.vocabularydetails

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.adapters.ARG_POS
import android.practice.com.congmingyuedu.adapters.ARG_SIMP
import android.practice.com.congmingyuedu.adapters.ARG_SIZE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_vocabulary_details_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VocabularyDetailsItemFragment: Fragment() {

    private val vocabularyDetailsViewModel: VocabularyDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vocabulary_details_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var position = 1

        arguments?.takeIf { it.containsKey(ARG_SIMP) }?.apply {

//            textViewSimplified.text = getString(ARG_SIMP)
//            textViewPinyin.text = "PinNoArgYet"
//            textViewTranslation.text = "TranslationNoArgYet"
//            textViewInfo.text = " InfoNoArgYet"
//            textViewExample.text = "ExampleNoArgYet"
            textViewPageCounter.text = getString(R.string.vocabulary_details_page_counter,
                getInt(ARG_POS)+1, getInt(ARG_SIZE))
            position = getInt(ARG_POS)
        }
        vocabularyDetailsViewModel.allVocab.observe(viewLifecycleOwner, Observer {vocabList ->
            val iuScope = CoroutineScope(Dispatchers.Main)
            iuScope.launch {
                val ww = withContext(Dispatchers.IO){
                    vocabularyDetailsViewModel.getWordFromChineseDictionary(vocabList[position].vocabularyContent)
                }
                if (ww != null && ww.wordSimplified.isNotBlank() ){
                    textViewSimplified.text = ww.wordSimplified
                    textViewTraditional.text = ww.wordTraditional
                    textViewPinyin.text = ww.wordPinyin
                    textViewTranslation.text = ww.wordTranslation
                    textViewInfo.text = vocabList[position].vocabularyExtraInfo
                }else{
                    textViewSimplified.text = vocabList[position].vocabularyContent
                    textViewInfo.text = vocabList[position].vocabularyExtraInfo
                }

            }
        })

    }
}
package android.practice.com.congmingyuedu.ui.showvocabularysimplelist

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_show_vocabulary_simple_list.*

class ShowVocabularySimpleList : Fragment() {

    private val showVocabularySimpleListViewModel: ShowVocabularySimpleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_vocabulary_simple_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showVocabularySimpleListViewModel.vocabularyList.observe(
            viewLifecycleOwner,
            { vocabList ->
                var mFormattedText = ""
                vocabList.forEach { mFormattedText += "${it.vocabularyContent} " }
                textViewVocabularySimpleList.text = mFormattedText
                textViewTitle.text =
                    getString(R.string.vocabulary_simple_list_title, vocabList.size)
            })

    }
}
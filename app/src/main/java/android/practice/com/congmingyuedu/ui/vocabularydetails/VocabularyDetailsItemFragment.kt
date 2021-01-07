package android.practice.com.congmingyuedu.ui.vocabularydetails

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_vocabulary_details_item.*
import kotlinx.android.synthetic.main.fragment_vocabulary_details_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VocabularyDetailsItemFragment: Fragment() {

    private val vocabularyDetailsViewModel: VocabularyDetailsViewModel by viewModels()
    private var currentId: Long = -1

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

            textViewPageCounter.text = getString(R.string.vocabulary_details_page_counter,
                getInt(ARG_POS)+1, getInt(ARG_SIZE))
            position = getInt(ARG_POS)
        }
        vocabularyDetailsViewModel.allVocab.observe(viewLifecycleOwner, { vocabList ->
            currentId = vocabList[position].id ?: -1
            val iuScope = CoroutineScope(Dispatchers.Main)
            iuScope.launch {
                val wordFromDictionary = withContext(Dispatchers.IO){
                    vocabularyDetailsViewModel.getWordFromChineseDictionary(vocabList[position].vocabularyContent)
                }
                if (wordFromDictionary != null && wordFromDictionary.wordSimplified.isNotBlank() ){
                    view.textViewSimplified.text = wordFromDictionary.wordSimplified
                    view.textViewTraditional.text = wordFromDictionary.wordTraditional
                    view.textViewPinyin.text = wordFromDictionary.wordPinyin
                    view.textViewTranslation.text = wordFromDictionary.wordTranslation
                    view.textViewInfo.text = vocabList[position].vocabularyExtraInfo
                }else{
                    view.textViewSimplified.text = vocabList[position].vocabularyContent
                    view.textViewInfo.text = vocabList[position].vocabularyExtraInfo
                }

            }

            when(vocabList[position].vocabularyStarred){
                true -> view.imageViewStar.setImageResource(R.drawable.ic_starred_true50x50)
                false -> view.imageViewStar.setImageResource(R.drawable.ic_starred_false50x50)
            }

        })

        view.imageViewStar.setOnClickListener {
            if (currentId >= 0)
                vocabularyDetailsViewModel.invertStarStatusById(currentId)
        }

        view.imageButtonDelete.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.alert_dialog_delete_confirmation))
            .setCancelable(false)
            .setPositiveButton("Yes"){ _, _ ->
                vocabularyDetailsViewModel.deleteVocabularyById(currentId)
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

}
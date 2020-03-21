package android.practice.com.congmingyuedu.ui.addvocabulary


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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_vocabulary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        editTextAddVocabulary.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                buttonAddVocabulary.isEnabled = !editTextAddVocabulary.text.isBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

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
            Snackbar.make(addVocabularyLayout, resources.getString(R.string.add_vocabulary_success), Snackbar.LENGTH_LONG).show()
        }
    }

}

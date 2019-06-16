package android.practice.com.congmingyuedu.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.model.Vocabulary
import android.practice.com.congmingyuedu.viewmodel.TextViewModel
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_vocabulary.*
import kotlinx.android.synthetic.main.fragment_import.*

class AddVocabularyFragment : Fragment() {

    private lateinit var textViewModel: TextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_vocabulary, container, false)
    }

    override fun onStart() {
        super.onStart()

        textViewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)
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
            textViewModel.insertVocabulary(Vocabulary(null, editTextAddVocabulary.text.toString()))
            editTextAddVocabulary.text.clear()
            Snackbar.make(addVocabularyLayout, resources.getString(R.string.add_vocabulary_success), Snackbar.LENGTH_LONG).show()
        }
    }

}

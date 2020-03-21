package android.practice.com.congmingyuedu.ui.importtext

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.ChineseText
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_import_text.*
import android.practice.com.congmingyuedu.utils.hideKeyboard
import androidx.fragment.app.viewModels

class ImportTextFragment : Fragment() {

    private val importTextViewModel: ImportTextViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_import_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Only enable save button when title and content are not empty
        class MyTextWatcher: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                buttonSave.isEnabled = (!editTextTitle.text.isBlank() && !editTextContent.text.isBlank())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        editTextTitle.addTextChangedListener(MyTextWatcher())
        editTextContent.addTextChangedListener(MyTextWatcher())

        buttonSave.setOnClickListener {
            importTextViewModel.insertText(
                ChineseText(
                    null,
                    editTextTitle.text.toString(),
                    editTextContent.text.toString(),
                    0
                )
            )
            editTextTitle.text.clear()
            editTextContent.text.clear()
            hideKeyboard()
            Snackbar.make(frameLayout, resources.getString(R.string.import_success), Snackbar.LENGTH_LONG).show()
        }

    }
}

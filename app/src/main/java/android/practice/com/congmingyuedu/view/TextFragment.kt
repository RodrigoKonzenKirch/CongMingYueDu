package android.practice.com.congmingyuedu.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.viewmodel.TextViewModel
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.text_fragment.*

class TextFragment : Fragment() {

    private lateinit var viewModel: TextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.text_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)

        textViewTextFragment.setOnClickListener{view ->
            view.findNavController().navigate(R.id.glossaryFragment)
        }

        buttonTextFragment.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.addVocabularyFragment)
        }

        viewModel.currentText.observe(this, Observer {
            if (it == null){
                textViewTextFragment.text = resources.getString(R.string.text_empty)
            }else{
                var tempString = it.textContent.substring(it.page * viewModel.numberOfCharsPerPage)
                if (tempString.length > viewModel.numberOfCharsPerPage)
                    tempString = tempString.substring(0, viewModel.numberOfCharsPerPage)
                textViewTextFragment.text = tempString
            }
        })
    }

}

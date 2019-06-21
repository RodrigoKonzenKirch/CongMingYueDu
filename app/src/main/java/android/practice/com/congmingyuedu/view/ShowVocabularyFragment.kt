package android.practice.com.congmingyuedu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.viewmodel.TextViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_show_vocabulary.*
import java.lang.StringBuilder

class ShowVocabularyFragment : Fragment() {

    private lateinit var textViewModel: TextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_vocabulary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textViewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)
        //TODO Change this temporary solution into a more elegant interface with info from the dictionary(not implemented yet)

        var formattedVocabulary: String
        val stringBuilder= StringBuilder()
        textViewModel.allVocabulary.observe(this, Observer { vocabulary ->
            if (vocabulary.isNotEmpty()){
                vocabulary.forEach{
                    stringBuilder.append("- "+it.vocabularyContent+"\n")
                }
                formattedVocabulary = stringBuilder.toString()
            }else{
                formattedVocabulary = "Your vocabulary list is empty!"
            }
            textViewShowVocabulary.text = formattedVocabulary
        })
    }

}

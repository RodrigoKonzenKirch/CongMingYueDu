package android.practice.com.congmingyuedu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.viewmodel.TextViewModel
import android.practice.com.congmingyuedu.viewmodel.VocabularyViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs

class VocabularyDetailsFragment : Fragment() {

    private lateinit var vocabularyViewModel: VocabularyViewModel
    val args: VocabularyDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vocabularyViewModel = ViewModelProviders.of(this).get(VocabularyViewModel::class.java)
        val vocabularyId = args.vocabularyIdArg



    }
}

package android.practice.com.congmingyuedu.ui.vocabularydetails

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.databinding.FragmentVocabularyDetailsBinding
import android.practice.com.congmingyuedu.ui.vocabularydetails.VocabularyDetailsFragmentArgs
import android.practice.com.congmingyuedu.ui.showvocabulary.VocabularyViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

class VocabularyDetailsFragment : Fragment() {

    private val vocabularyViewModel: VocabularyViewModel by viewModels()
    private val args: VocabularyDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentVocabularyDetailsBinding.inflate(inflater, container, false
        ).apply {
            this.lifecycleOwner = this@VocabularyDetailsFragment
            this.viewModel = vocabularyViewModel
            vocabularyViewModel.setUpVocabularyDetailsById(args.vocabularyIdArg)
            when (vocabularyViewModel.vocabularyDetails.value!!.isStared ){
                true -> imageView.setImageResource(R.drawable.starred_true50x50)
                false -> imageView.setImageResource(R.drawable.starred_false50x50)
            }
        }.root
    }

}

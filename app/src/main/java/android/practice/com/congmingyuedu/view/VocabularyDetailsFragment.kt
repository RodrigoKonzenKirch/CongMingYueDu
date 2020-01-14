package android.practice.com.congmingyuedu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.databinding.FragmentVocabularyDetailsBinding
import android.practice.com.congmingyuedu.viewmodel.VocabularyViewModel
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
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vocabularyViewModel.setUpVocabularyDetailsById(args.vocabularyIdArg)
    }
}

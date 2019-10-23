package android.practice.com.congmingyuedu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.databinding.TextFragmentBinding
import android.practice.com.congmingyuedu.viewmodel.TextViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.text_fragment.*

class TextFragment : Fragment() {

    private val textViewModel: TextViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TextFragmentBinding.inflate(inflater, container, false
        ).apply {

            this.lifecycleOwner = this@TextFragment
            this.viewModel = textViewModel
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonTextFragmentFlipToGlossaryLeft.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.glossaryFragment)
        }

        buttonTextFragmentFlipToGlossaryRight.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.glossaryFragment)
        }

        buttonTextFragmentToAddVocabulary.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.addVocabularyFragment)
        }
    }

}

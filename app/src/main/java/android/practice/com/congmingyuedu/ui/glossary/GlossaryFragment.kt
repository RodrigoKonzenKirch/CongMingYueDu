package android.practice.com.congmingyuedu.ui.glossary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.glossary_fragment.*

class GlossaryFragment : Fragment() {

    private val viewModel: GlossaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.glossary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var currentText = ""

        buttonGlossaryFragmentToAddVocabulary.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.addVocabularyFragment)
        }

        buttonGlossaryFragmentFlipToTextLeft.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.textFragment)
        }

        buttonGlossaryFragmentFlipToTextRight.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.textFragment)
        }

        viewModel.mCurrentText.observe(viewLifecycleOwner, Observer { mText ->
            currentText = mText
        })

        viewModel.vocabularyList.observe(viewLifecycleOwner, Observer { vocabList ->
            var formattedString = ""
                vocabList.forEach {
                        when{
                            currentText.contains(it.vocabularyContent) ->
                                formattedString = "$formattedString -->  ${it.vocabularyContent}\n"
                        }
                }

            textViewGlossaryFragment.text = formattedString
        })

    }
}

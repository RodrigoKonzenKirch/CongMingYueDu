package android.practice.com.congmingyuedu.ui.glossary

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.glossary_fragment.*

class GlossaryFragment : Fragment() {

    private lateinit var viewModel: GlossaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.glossary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GlossaryViewModel::class.java)
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

        viewModel.mCurrentText.observe(this, Observer { mText ->
            currentText = mText
        })

        viewModel.vocabularyList.observe(this, Observer { vocabList ->
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

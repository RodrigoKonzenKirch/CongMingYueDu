package android.practice.com.congmingyuedu.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.viewmodel.GlossaryViewModel
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.glossary_fragment.*
import kotlinx.android.synthetic.main.text_fragment.*

class GlossaryFragment : Fragment() {

    companion object {
        fun newInstance() = GlossaryFragment()
    }

    private lateinit var viewModel: GlossaryViewModel
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.glossary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GlossaryViewModel::class.java)

        buttonGlossaryFragmentToAddVocabulary.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.addVocabularyFragment)
        }

        buttonGlossaryFragmentFlipToTextLeft.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.textFragment)
        }

        buttonGlossaryFragmentFlipToTextRight.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.textFragment)
        }

        textView = textViewGlossaryFragment
        textView.setOnClickListener{view ->
            view.findNavController().navigate(R.id.textFragment)}

        // TODO: Use the ViewModel
    }

}

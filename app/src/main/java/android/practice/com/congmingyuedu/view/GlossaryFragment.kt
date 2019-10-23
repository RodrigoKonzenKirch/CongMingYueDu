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
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.glossary_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GlossaryFragment : Fragment() {

    companion object {
        fun newInstance() = GlossaryFragment()
    }

    private lateinit var viewModel: TextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.glossary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)

        buttonGlossaryFragmentToAddVocabulary.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.addVocabularyFragment)
        }

        buttonGlossaryFragmentFlipToTextLeft.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.textFragment)
        }

        buttonGlossaryFragmentFlipToTextRight.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.textFragment)
        }

    }
}

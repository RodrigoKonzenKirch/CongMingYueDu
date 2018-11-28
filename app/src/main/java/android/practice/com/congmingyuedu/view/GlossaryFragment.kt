package android.practice.com.congmingyuedu.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.viewmodel.GlossaryViewModel

class GlossaryFragment : Fragment() {

    companion object {
        fun newInstance() = GlossaryFragment()
    }

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
        // TODO: Use the ViewModel
    }

}

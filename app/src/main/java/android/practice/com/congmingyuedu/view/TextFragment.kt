package android.practice.com.congmingyuedu.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.viewmodel.MainTextViewModel
import android.widget.TextView
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.text_fragment.*

class TextFragment : Fragment() {

    companion object {
        fun newInstance() = TextFragment()
    }

    private lateinit var viewModel: MainTextViewModel
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.text_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainTextViewModel::class.java)

        textView = textViewTextFragment
        textView.setOnClickListener{view ->
        view.findNavController().navigate(R.id.glossaryFragment)}

        // TODO: Use the ViewModel
    }

}

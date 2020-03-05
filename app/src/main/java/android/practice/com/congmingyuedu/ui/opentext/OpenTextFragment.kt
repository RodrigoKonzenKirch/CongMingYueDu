package android.practice.com.congmingyuedu.ui.opentext


import android.os.Bundle
import android.practice.com.congmingyuedu.adapters.TextListAdapter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_open_text.*


class OpenTextFragment : Fragment() {

    private lateinit var openTextViewModel: OpenTextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        openTextViewModel = ViewModelProviders.of(this).get(OpenTextViewModel::class.java)
        val recyclerView = recyclerviewText
        val adapter = TextListAdapter(openTextViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view?.context)


        openTextViewModel.allTexts.observe(this, Observer { texts ->
            texts?.let { adapter.setTexts(it) }
        })

    }


}

package android.practice.com.congmingyuedu.ui.deletetext

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.adapters.DeleteTextListAdapter
import android.practice.com.congmingyuedu.adapters.TextListAdapter
import android.practice.com.congmingyuedu.ui.opentext.OpenTextViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_delete_text.*
import kotlinx.android.synthetic.main.fragment_open_text.*

class DeleteTextFragment : Fragment() {

    private lateinit var deleteTextViewModel: DeleteTextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        deleteTextViewModel = ViewModelProviders.of(this).get(DeleteTextViewModel::class.java)

        val recyclerView = recyclerviewDeleteText
        val adapter = DeleteTextListAdapter(deleteTextViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view?.context)

        deleteTextViewModel.allTexts.observe(this, Observer { texts ->
            texts?.let { adapter.setTexts(it) }
        })

    }

}

package android.practice.com.congmingyuedu.ui.deletetext

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.adapters.DeleteTextListAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_delete_text.*

class DeleteTextFragment : Fragment() {

    private val deleteTextViewModel: DeleteTextViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = recyclerviewDeleteText
        val adapter = DeleteTextListAdapter(deleteTextViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view?.context)

        deleteTextViewModel.allTexts.observe(viewLifecycleOwner, Observer { texts ->
            texts?.let { adapter.setTexts(it) }
        })

    }

}

package android.practice.com.congmingyuedu.ui.showvocabulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.adapters.ShowVocabularyListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_show_vocabulary.*

class ShowVocabularyFragment : Fragment() {

    private lateinit var vocabularyViewModel: VocabularyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_vocabulary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vocabularyViewModel = ViewModelProviders.of(this).get(VocabularyViewModel::class.java)

        val recyclerView = recyclerviewShowVocabulary
        val adapter = ShowVocabularyListAdapter(vocabularyViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view?.context)

        vocabularyViewModel.allVocabulary.observe(this, Observer { vocabularyList ->
            vocabularyList?.let { adapter.setVocabularyList(it) }
        })

    }

}

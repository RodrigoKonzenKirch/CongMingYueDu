/* 聪明阅读(CongMingYueDu) Chinese text reader with tools to learn vocabulary
Copyright (C) 2020 Rodrigo Konzen Kirch

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.*/

package android.practice.com.congmingyuedu.ui.glossary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.practice.com.congmingyuedu.R
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_glossary.*

class GlossaryFragment : Fragment() {

    private val viewModel: GlossaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_glossary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var currentText = ""

        val recyclerView = recyclerViewGlossaryFragment
        val adapter = GlossaryListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view?.context)

        viewModel.mCurrentText.observe(viewLifecycleOwner, { mText ->
            currentText = mText
        })

        viewModel.vocabularyList.observe(viewLifecycleOwner, { vocabularyList ->
            vocabularyList.filter {vocabulary -> currentText.contains(vocabulary.vocabularyContent) }
                .let { adapter.setVocabularyList(it, currentText) }
        })

        buttonGlossaryFragmentFlipToText.setOnClickListener {
            nav_host_fragment.findNavController().navigate(R.id.textFragment)
        }

    }
}

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

import android.graphics.Typeface
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_show_glossary.view.*

class GlossaryListAdapter: RecyclerView.Adapter<GlossaryListAdapter.ShowGlossaryViewHolder>(){

    private var vocabularyList = emptyList<Pair<Int, Vocabulary>>()

    inner class ShowGlossaryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewItem: TextView = itemView.textViewGlossaryContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowGlossaryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_show_glossary, parent, false)
        return ShowGlossaryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowGlossaryViewHolder, position: Int) {
        if (vocabularyList[position].second.vocabularyStarred){
            holder.textViewItem.setTypeface(null, Typeface.BOLD)
        }
        holder.textViewItem.text = vocabularyList[position].second.vocabularyContent
//        holder.textViewItem.setOnClickListener {
//            val action = GlossaryFragmentDirections.actionGlossaryFragmentToVocabularyDetailsFragment(position)
//            Navigation.findNavController(holder.textViewItem).navigate(action)
//        }
    }

    internal fun setVocabularyList(vocabularyList: List<Vocabulary>, currentText: String){
        val sortedVocabularyList = mutableListOf<Pair<Int, Vocabulary>>()

        // Sort list of words by first appearance in the current text
        if (currentText.isNotBlank() && vocabularyList.isNotEmpty()){
            vocabularyList.forEach{
                sortedVocabularyList.add(Pair(currentText.indexOf(it.vocabularyContent), it))
            }
            sortedVocabularyList.sortBy { it.first }
        }

        this.vocabularyList = sortedVocabularyList
        notifyDataSetChanged()
    }

    override fun getItemCount() = vocabularyList.size
}
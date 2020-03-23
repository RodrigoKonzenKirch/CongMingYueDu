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

package android.practice.com.congmingyuedu.adapters

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.ui.glossary.GlossaryFragmentDirections
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_show_glossary.view.*

class ShowGlossaryListAdapter: RecyclerView.Adapter<ShowGlossaryListAdapter.ShowGlossaryViewHolder>(){

    private var vocabularyList = emptyList<Vocabulary>()

    inner class ShowGlossaryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewItem: TextView = itemView.textViewGlossaryContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowGlossaryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_show_glossary, parent, false)
        return ShowGlossaryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowGlossaryViewHolder, position: Int) {
        holder.textViewItem.text = vocabularyList[position].vocabularyContent
        holder.textViewItem.setOnClickListener {
            val action = GlossaryFragmentDirections.actionGlossaryFragmentToVocabularyDetailsFragment(vocabularyList[position].id!!)
            Navigation.findNavController(holder.textViewItem).navigate(action)
        }
    }

    internal fun setVocabularyList(vocabularyList: List<Vocabulary>){
        this.vocabularyList = vocabularyList
        notifyDataSetChanged()
    }

    override fun getItemCount() = vocabularyList.size
}
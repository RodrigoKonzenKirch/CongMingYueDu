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
import android.practice.com.congmingyuedu.ui.showvocabulary.ShowVocabularyFragmentDirections
import android.practice.com.congmingyuedu.ui.showvocabulary.VocabularyViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_show_vocabulary.view.*

class ShowVocabularyListAdapter internal constructor(private val viewModel: VocabularyViewModel): RecyclerView.Adapter<ShowVocabularyListAdapter.ShowVocabularyViewHolder>(){

    private var vocabularyList = emptyList<Vocabulary>()

    inner class ShowVocabularyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageViewItem: ImageView = itemView.imageViewShowVocabulary
        val textViewItem: TextView = itemView.textViewShowVocabulary
        val textViewItemExtraInfo: TextView = itemView.textViewShowVocabularyExtraInfo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowVocabularyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_show_vocabulary, parent, false)
        return ShowVocabularyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowVocabularyViewHolder, position: Int) {

        if (vocabularyList[position].vocabularyStarred)
            holder.imageViewItem.setImageResource(R.drawable.ic_starred_true50x50)
        else
            holder.imageViewItem.setImageResource(R.drawable.ic_starred_false50x50)

        holder.textViewItem.text = vocabularyList[position].vocabularyContent
        holder.imageViewItem.setOnClickListener{
            if (vocabularyList[position].id != null)
                viewModel.setVocabularyStared(!vocabularyList[position].vocabularyStarred, vocabularyList[position].id!!)
        }
        if (vocabularyList[position].vocabularyExtraInfo.isEmpty()){
            holder.textViewItemExtraInfo.visibility = View.GONE
        }else{
            holder.textViewItemExtraInfo.visibility = View.VISIBLE
            holder.textViewItemExtraInfo.text = vocabularyList[position].vocabularyExtraInfo
        }

        holder.textViewItem.setOnClickListener {
            if (vocabularyList[position].id != null){
                val action = ShowVocabularyFragmentDirections.actionShowVocabularyFragmentToVocabularyDetailsFragment(position)
                Navigation.findNavController(holder.textViewItem).navigate(action)
            }
        }

        holder.textViewItemExtraInfo.text = vocabularyList[position].vocabularyExtraInfo
    }


    internal fun setVocabularyList(vocabularyList: List<Vocabulary>){
        this.vocabularyList = vocabularyList
        notifyDataSetChanged()
    }

    override fun getItemCount() = vocabularyList.size


}
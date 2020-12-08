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

package android.practice.com.congmingyuedu.ui.opentext

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.ChineseText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_open_text.view.*

class OpenTextListAdapter internal constructor(private val viewModel: OpenTextViewModel): RecyclerView.Adapter<OpenTextListAdapter.TextViewHolder>(){

    private var texts = emptyList<ChineseText>()

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textItemViewTitle: TextView = itemView.textViewRecyclerViewItemTitle
        val textItemViewContent: TextView = itemView.textViewRecyclerViewItemContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_open_text, parent,false)
        return TextViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        val current = texts[position]
        val formattedContent:String

        if (current.textContent.length > 50){
            formattedContent = current.textContent.substring(0, 50)
        }else{
            formattedContent = current.textContent
        }

        holder.textItemViewTitle.text = current.textTitle
        holder.textItemViewContent.text = formattedContent
        val id: Long? = current.id
        holder.itemView.setOnClickListener{
            if (id != null)
                viewModel.setCurrentText(id.toInt())
            holder.itemView.findNavController().navigate(R.id.textFragment)
        }
    }

    internal fun setTexts(texts: List<ChineseText>) {
        this.texts = texts
        notifyDataSetChanged()
    }

    override fun getItemCount() = texts.size
}
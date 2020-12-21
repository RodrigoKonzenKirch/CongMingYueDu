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

package android.practice.com.congmingyuedu.ui.deletetext

import android.app.AlertDialog
import android.content.Context
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.ChineseText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_delete_text.view.*

class DeleteTextListAdapter internal constructor(private val viewModel: DeleteTextViewModel): RecyclerView.Adapter<DeleteTextListAdapter.DeleteTextViewHolder>(){

    private var texts = emptyList<ChineseText>()

    inner class DeleteTextViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textItemViewTitle: TextView = itemView.textViewRecyclerViewItemTitle
        val textItemViewContent: TextView = itemView.textViewRecyclerViewItemContent
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteTextViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_delete_text, parent, false)
        return DeleteTextViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeleteTextViewHolder, position: Int) {
        val current = texts[position]
        val formattedContent: String

        formattedContent = if(current.textContent.length > 50){
            current.textContent.substring(0, 50)
        } else{
            current.textContent
        }

        holder.textItemViewTitle.text = current.textTitle
        holder.textItemViewContent.text = formattedContent
        holder.itemView.setOnClickListener {

            if (current.id != null)
                showAlertDialogDeleteText(holder.itemView.context, current.id!!, current.textTitle)
        }
        holder.itemView.context
    }

    private fun showAlertDialogDeleteText(context: Context, id: Long, textTitle: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to delete: $textTitle")
            .setCancelable(false)
            .setPositiveButton(context.getString(R.string.yes)){ _, _ ->
                viewModel.deleteTextById(id)
                notifyDataSetChanged()
            }
            .setNegativeButton(context.getString(R.string.no)){ dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    internal fun setTexts(texts: List<ChineseText>) {
        this.texts = texts
        notifyDataSetChanged()
    }

    override fun getItemCount() = texts.size

}
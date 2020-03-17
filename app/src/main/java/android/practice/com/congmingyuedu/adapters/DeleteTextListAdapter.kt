package android.practice.com.congmingyuedu.adapters

import android.app.AlertDialog
import android.content.Context
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.ChineseText
import android.practice.com.congmingyuedu.ui.deletetext.DeleteTextViewModel
import android.practice.com.congmingyuedu.ui.vocabularydetails.VocabularyDetailsFragmentDirections
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerview_item_delete_text.view.*

class DeleteTextListAdapter internal constructor(val viewModel: DeleteTextViewModel): RecyclerView.Adapter<DeleteTextListAdapter.DeleteTextViewHolder>(){

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

        if(current.textContent.length > 50){
            formattedContent = current.textContent.substring(0, 50)
        } else{
            formattedContent = current.textContent
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
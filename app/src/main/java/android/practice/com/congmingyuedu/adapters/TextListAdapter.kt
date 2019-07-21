package android.practice.com.congmingyuedu.adapters

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.model.ChineseText
import android.practice.com.congmingyuedu.viewmodel.TextViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.text_recyclerview_item.view.*

class TextListAdapter internal constructor(val viewModel: TextViewModel): RecyclerView.Adapter<TextListAdapter.TextViewHolder>(){

    private var texts = emptyList<ChineseText>()

    inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textItemViewTitle: TextView = itemView.textViewRecyclerViewItemTitle
        val textItemViewContent: TextView = itemView.textViewRecyclerViewItemContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.text_recyclerview_item, parent,false)
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
        holder.itemView.setOnClickListener{
            viewModel.setCurrentText(position+1)
            holder.itemView.findNavController().navigate(R.id.textFragment)
        }
    }

    internal fun setTexts(texts: List<ChineseText>) {
        this.texts = texts
        notifyDataSetChanged()
    }

    override fun getItemCount() = texts.size
}
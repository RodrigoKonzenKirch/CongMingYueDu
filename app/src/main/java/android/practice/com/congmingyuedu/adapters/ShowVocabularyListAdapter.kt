package android.practice.com.congmingyuedu.adapters

import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.model.Vocabulary
import android.practice.com.congmingyuedu.view.ShowVocabularyFragmentDirections
import android.practice.com.congmingyuedu.viewmodel.TextViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item_show_vocabulary.view.*

class ShowVocabularyListAdapter internal constructor(val viewModel: TextViewModel): RecyclerView.Adapter<ShowVocabularyListAdapter.ShowVocabularyViewHolder>(){

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
            holder.imageViewItem.setImageResource(R.drawable.starred_true50x50)
        else
            holder.imageViewItem.setImageResource(R.drawable.starred_false50x50)

        holder.textViewItem.text = vocabularyList[position].vocabularyContent
        holder.imageViewItem.setOnClickListener{
            if (vocabularyList[position].id != null)
                viewModel.setVocabularyStared(!vocabularyList[position].vocabularyStarred, vocabularyList[position].id!!)
        }

        holder.textViewItem.setOnClickListener {
            if (vocabularyList[position].id != null){
                val action = ShowVocabularyFragmentDirections.actionShowVocabularyFragmentToVocabularyDetailsFragment(vocabularyList[position].id!!.toInt())
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
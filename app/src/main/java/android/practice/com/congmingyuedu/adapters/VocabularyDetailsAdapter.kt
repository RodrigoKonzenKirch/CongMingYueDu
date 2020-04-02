package android.practice.com.congmingyuedu.adapters

import android.os.Bundle
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.data.local.VocabularyDetails
import android.practice.com.congmingyuedu.ui.vocabularydetails.VocabularyDetailsItemFragment
import android.practice.com.congmingyuedu.ui.vocabularydetails.VocabularyDetailsViewModel
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


const val ARG_SIMP = "simplified"
const val ARG_POS = "position"
const val ARG_SIZE = "listSize"


class VocabularyDetailsAdapter(frag: Fragment, private val viewModel: VocabularyDetailsViewModel, private val vocabs: List<Vocabulary>): FragmentStateAdapter(frag) {

    override fun getItemCount(): Int = vocabs.size

    override fun createFragment(position: Int): Fragment {
        val fragment = VocabularyDetailsItemFragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_SIMP, vocabs[position].vocabularyContent)
            putInt(ARG_POS, position)
            putInt(ARG_SIZE, vocabs.size)
        }
        return fragment
    }
}
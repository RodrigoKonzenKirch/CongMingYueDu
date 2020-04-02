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

package android.practice.com.congmingyuedu.ui.vocabularydetails

import android.app.AlertDialog
import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.adapters.VocabularyDetailsAdapter
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.practice.com.congmingyuedu.data.local.VocabularyDetails
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_vocabulary_details.*

class VocabularyDetailsFragment : Fragment() {

    private val vocabularyDetailsViewModel: VocabularyDetailsViewModel by viewModels()
    private val args: VocabularyDetailsFragmentArgs by navArgs()
    private lateinit var vocabularyDetailsAdapter: VocabularyDetailsAdapter
    // TODO: Send real data instead of this mock list
    private var mockContent = arrayListOf(
        VocabularyDetails(false,"ws1", "wt1", "pin1", "translation1", "info1","example1"),
        VocabularyDetails(false,"ws2", "wt2", "pin2", "translation2", "info2","example2"),
        VocabularyDetails(false,"ws3", "wt3", "pin3", "translation3", "info3","example3"),
        VocabularyDetails(false,"ws4", "wt4", "pin4", "translation4", "info4","example4"),
        VocabularyDetails(false,"ws5", "wt5", "pin5", "translation5", "info5","example5")
    )
    private var vocabMockForNow = listOf<Vocabulary>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        vocabularyDetailsViewModel.allVocab.observe(viewLifecycleOwner, Observer {
            vocabMockForNow = it
            vocabularyDetailsAdapter = VocabularyDetailsAdapter(this, vocabularyDetailsViewModel, vocabMockForNow)
            viewPagerVocabularyDetails.adapter = vocabularyDetailsAdapter

//            viewPagerVocabularyDetails.post { viewPagerVocabularyDetails.setCurrentItem(args.vocabularyIdArg,true) }
        })
//        viewPagerVocabularyDetails.currentItem = args.vocabularyIdArg
        Log.d("POSITION", "${args.vocabularyIdArg.toInt()}")
//        vocabularyDetailsViewModel.vocabularyDetails.observe(viewLifecycleOwner, Observer {
//            vocabularyDetailsViewModel.setUpVocabularyDetailsById(args.vocabularyIdArg)
//            when (vocabularyDetailsViewModel.vocabularyDetails.value!!.isStared ){
//                true -> imageViewStar.setImageResource(R.drawable.ic_starred_true50x50)
//                false -> imageViewStar.setImageResource(R.drawable.ic_starred_false50x50)
//            }
//
//            textViewSimplified.text = it.simplified
//            textViewTraditional.text = it.traditional
//            textViewPinyin.text = it.pinyin
//            textViewTranslation.text = it.translation
//            textViewInfo.text = it.info
//            textViewExample.text = it.examples
//        })
//
//        imageButtonDelete.setOnClickListener{
//            showAlertDialog()
//        }
//
//        imageViewStar.setOnClickListener {
//            vocabularyDetailsViewModel.invertStarStatusById(args.vocabularyIdArg)
//        }
    }


    // TODO: Check this alert after implementing real data(consider not navigating away after deletion) correct deleteVocabularyById argument
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.alert_dialog_delete_confirmation))
            .setCancelable(false)
            .setPositiveButton("Yes"){ _, _ ->
                vocabularyDetailsViewModel.deleteVocabularyById(args.vocabularyIdArg.toLong())
                nav_host_fragment.findNavController().navigate(VocabularyDetailsFragmentDirections.actionVocabularyDetailsFragmentToShowVocabularyFragment())
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

}

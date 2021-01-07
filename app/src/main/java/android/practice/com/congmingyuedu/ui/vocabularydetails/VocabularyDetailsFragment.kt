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

import android.os.Bundle
import android.practice.com.congmingyuedu.R
import android.practice.com.congmingyuedu.data.local.Vocabulary
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_vocabulary_details.*

class VocabularyDetailsFragment : Fragment() {

    private val vocabularyDetailsViewModel: VocabularyDetailsViewModel by viewModels()
    private val args: VocabularyDetailsFragmentArgs by navArgs()
    private lateinit var vocabularyDetailsAdapter: VocabularyDetailsAdapter

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


        vocabularyDetailsViewModel.allVocab.observe(viewLifecycleOwner, {
            vocabMockForNow = it
            vocabularyDetailsAdapter = VocabularyDetailsAdapter(this, vocabularyDetailsViewModel, vocabMockForNow)
            viewPagerVocabularyDetails.adapter = vocabularyDetailsAdapter

            viewPagerVocabularyDetails.post { viewPagerVocabularyDetails.setCurrentItem(args.vocabularyIdArg,false) }
        })
    }


    // TODO: Check this alert after implementing real data(consider not navigating away after deletion) correct deleteVocabularyById argument
//    private fun showAlertDialog() {
//        val builder = AlertDialog.Builder(context)
//        builder.setMessage(getString(R.string.alert_dialog_delete_confirmation))
//            .setCancelable(false)
//            .setPositiveButton("Yes"){ _, _ ->
//                vocabularyDetailsViewModel.deleteVocabularyById(args.vocabularyIdArg.toLong())
//                //nav_host_fragment.findNavController().navigate(VocabularyDetailsFragmentDirections.actionVocabularyDetailsFragmentToShowVocabularyFragment())
//            }
//            .setNegativeButton("No"){ dialog, _ ->
//                dialog.dismiss()
//            }
//        val alert = builder.create()
//        alert.show()
//    }

}

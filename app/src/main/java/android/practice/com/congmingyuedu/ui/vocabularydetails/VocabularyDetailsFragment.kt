package android.practice.com.congmingyuedu.ui.vocabularydetails

import android.app.AlertDialog
import android.os.Bundle
import android.practice.com.congmingyuedu.R
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_vocabulary_details.*

class VocabularyDetailsFragment : Fragment() {

    private val vocabularyDetailsViewModel: VocabularyDetailsViewModel by viewModels()
    private val args: VocabularyDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vocabularyDetailsViewModel.vocabularyDetails.observe(viewLifecycleOwner, Observer {
            vocabularyDetailsViewModel.setUpVocabularyDetailsById(args.vocabularyIdArg)
            when (vocabularyDetailsViewModel.vocabularyDetails.value!!.isStared ){
                true -> imageViewStar.setImageResource(R.drawable.ic_starred_true50x50)
                false -> imageViewStar.setImageResource(R.drawable.ic_starred_false50x50)
            }

            textViewSimplified.text = it.simplified
            textViewTraditional.text = it.traditional
            textViewPinyin.text = it.pinyin
            textViewTranslation.text = it.translation
            textViewInfo.text = it.info
            textViewExample.text = it.examples
        })

        imageButtonDelete.setOnClickListener{
            showAlertDialog()
        }

        imageViewStar.setOnClickListener {
            vocabularyDetailsViewModel.invertStarStatusById(args.vocabularyIdArg)
        }
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.alert_dialog_delete_confirmation))
            .setCancelable(false)
            .setPositiveButton("Yes"){ _, _ ->
                vocabularyDetailsViewModel.deleteVocabularyById(args.vocabularyIdArg)
                nav_host_fragment.findNavController().navigate(VocabularyDetailsFragmentDirections.actionVocabularyDetailsFragmentToShowVocabularyFragment())
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

}

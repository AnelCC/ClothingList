package com.anelcc.lululemon.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.anelcc.lululemon.databinding.FragmentAddItemDialogBinding
import com.anelcc.lululemon.domain.DIALOG_DATA
import com.anelcc.lululemon.domain.DialogSpanConfig

class AddItemDialogFragment : DialogFragment() {

    private lateinit var dialogBinding: FragmentAddItemDialogBinding
    private var dialogConfig: DialogSpanConfig? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogBinding = FragmentAddItemDialogBinding.inflate(inflater)
        return dialogBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialogConfig = requireArguments().getParcelable(DIALOG_DATA)

        dialogConfig?.let { config ->


            val positiveOnClickListener = View.OnClickListener {
                val name = dialogBinding.etClothingName.text.toString()
                dialogConfig?.onPositiveClick?.invoke(name)
                dismiss()
            }

            positiveOnClickListener.let {
                dialogBinding?.btnDialogSave?.setOnClickListener(it)
            }

        }
    }


    companion object {
        val TAG = AddItemDialogFragment::class.simpleName

        private fun buildConfigBundle(
            onPositiveClick: (position: String) -> Unit = { }
        ): Bundle {
            return Bundle().apply {
                val config = DialogSpanConfig(onPositiveClick = onPositiveClick)
                putParcelable(DIALOG_DATA, config)
            }
        }

        fun newInstance(
            onPositiveClick: (position: String) -> Unit = { }
        ): AddItemDialogFragment = AddItemDialogFragment().apply {
            arguments = buildConfigBundle(onPositiveClick = onPositiveClick)
        }

    }


}
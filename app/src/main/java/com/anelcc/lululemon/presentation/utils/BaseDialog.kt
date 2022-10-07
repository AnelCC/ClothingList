package com.anelcc.lululemon.presentation.utils

import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.anelcc.lululemon.R

abstract class BaseDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val customDialog = super.onCreateDialog(savedInstanceState)
        val layoutParams = WindowManager.LayoutParams()
        val window = customDialog.window
        window?.let {
            it.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background
                )
            )
            it.setDimAmount(0.2f)

            layoutParams.apply {
                copyFrom(it.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
            it.attributes = layoutParams
        }
        return customDialog
    }
}
package com.cuncis.ticketbookingremake.util


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.cuncis.ticketbookingremake.R


class CustomProgressDialog(context: Context) : AlertDialog(context) {

    override fun show() {
        super.show()
        setContentView(R.layout.custom_progress_dialog)
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    override fun hide() {
        super.hide()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
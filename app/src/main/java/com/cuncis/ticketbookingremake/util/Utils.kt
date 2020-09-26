package com.cuncis.ticketbookingremake.util

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Patterns
import android.webkit.MimeTypeMap
import android.widget.Toast


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun isValidEmail(target: CharSequence): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}


fun Context.getFileExtension(uri: Uri): String? {
    val contextResolver = this.contentResolver
    val mimeTypeMap = MimeTypeMap.getSingleton()
    return mimeTypeMap.getExtensionFromMimeType(contextResolver.getType(uri))
}
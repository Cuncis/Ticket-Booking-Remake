package com.cuncis.ticketbookingremake.ui.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>: ViewModel() {

    private var mNavigator: WeakReference<N>? = null

    var navigator: N?
        get() {
            return mNavigator?.get()
        }
        set(value) {
            mNavigator = WeakReference<N>(value)
        }
}
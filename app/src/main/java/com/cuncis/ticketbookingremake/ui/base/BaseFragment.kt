package com.cuncis.ticketbookingremake.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.cuncis.ticketbookingremake.BR


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<out Any>>: Fragment() {

    private lateinit var dataBinding: T

    @LayoutRes
    abstract fun setLayout(): Int

    private val baseViewModel by lazy { getViewModel() }

    abstract fun getViewModel(): V

    open fun onInitialization() = Unit

    open fun onReadyAction() = Unit

    open fun onObserveAction() = Unit

    open fun getViewDataBinding() = dataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, setLayout(), container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.setVariable(BR.viewModel, baseViewModel)
        onInitialization()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onReadyAction()
        onObserveAction()
    }
}
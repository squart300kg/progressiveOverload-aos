package com.progressive.overload.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

open class BaseCenterDialog<B: ViewDataBinding>(
    private val layoutRes: Int
): DialogFragment() {
    protected lateinit var dataBinding: B


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return dataBinding.root
    }

    fun binding(action: B.() -> Unit) {
        dataBinding.run(action)
    }
}
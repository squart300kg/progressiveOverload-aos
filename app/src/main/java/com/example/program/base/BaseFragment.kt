package com.example.program.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.program.ui.MainActivity
import com.example.program.ui.home.sub.ExerciseTypeActivity

open class BaseFragment<T: ViewDataBinding>(layoutRes: Int) : Fragment() {

    protected lateinit var binding: T

    private val layoutRes = layoutRes

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    protected fun binding(action: T.() -> Unit) {
        binding.run(action)
    }

    protected fun goMain() {
        Intent(requireActivity(),
            MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(requireActivity(),
            message,
            Toast.LENGTH_LONG)
            .show()
    }


}
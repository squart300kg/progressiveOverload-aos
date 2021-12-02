package com.example.program.ui.message

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.program.R
import com.example.program.base.BaseFragment
import com.example.program.databinding.FragmentMessageBinding

class MessageFragment : BaseFragment<FragmentMessageBinding>(R.layout.fragment_message) {

    private val messageViewModel: MessageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}
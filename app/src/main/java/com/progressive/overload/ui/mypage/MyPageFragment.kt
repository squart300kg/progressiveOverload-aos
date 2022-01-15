package com.progressive.overload.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.progressive.overload.R
import com.progressive.overload.base.BaseFragment
import com.progressive.overload.databinding.FragmentMypageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
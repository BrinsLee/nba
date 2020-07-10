package com.brins.nba.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brins.nba.R
import com.brins.nba.databinding.FragmentLiveBinding
import com.brins.nba.utils.InjectorUtil
import com.brins.nba.viewmodel.live.LiveViewModel


class LiveFragment : BaseDBFragment<FragmentLiveBinding>() {

    private lateinit var mLiveViewModel: LiveViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_live
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mLiveViewModel = InjectorUtil.getLiveModelFactory().create(LiveViewModel::class.java)
    }

    override fun initEventAndData() {

    }


}
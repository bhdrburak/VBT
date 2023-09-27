package com.bhdrburak.vbtproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhdrburak.vbtproject.R
import com.bhdrburak.vbtproject.databinding.FragmentMainBinding
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment @Inject constructor(val glide : RequestManager) : Fragment(R.layout.fragment_main) {

    private lateinit var binding : FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

    }
}
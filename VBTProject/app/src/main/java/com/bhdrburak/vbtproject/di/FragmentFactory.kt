package com.bhdrburak.vbtproject.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bhdrburak.vbtproject.fragments.MainFragment
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class FragmentFactory @Inject constructor(val glide : RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MainFragment::class.java.name -> MainFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}
package com.bhdrburak.vbtcase.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

// var userRecyclerViewAdapter: UserRecyclerViewAdapter, val glide : RequestManager
class FragmentFactory @Inject constructor() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            //UserRepoFragment::class.java.name -> UserRepoFragment(userRecyclerViewAdapter)
            //UserRepoDetailFragment::class.java.name -> UserRepoDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}
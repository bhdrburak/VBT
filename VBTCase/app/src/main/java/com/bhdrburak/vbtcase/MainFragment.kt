package com.bhdrburak.vbtcase

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bhdrburak.vbtcase.common.TestActivity
import com.bhdrburak.vbtcase.common.VideoActivity
import com.bhdrburak.vbtcase.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding : FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)


        binding.newFragmentButton.setOnClickListener {

            //findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
            val intent = Intent(requireContext(), PickImageActivity::class.java)
            startActivity(intent)
            /*val text = editText.text.toString()
            val bundle = bundleOf("lat" to 39.845934, "long" to 32.829432)
            findNavController().navigate(R.id.action_mainFragment_to_mapsFragment, bundle)


            if(text.isNotEmpty()){
                //val bundle = bundleOf("passdata" to text, "age" to 29, "user" to UserData(username = "bahadır", 22))


                /*val fragment = MainFragmentDirections.actionMainFragmentToSecondFragment(UserData("Bahadır", 30))

                findNavController().navigate(fragment)*/
            }*/
        }


    }

}
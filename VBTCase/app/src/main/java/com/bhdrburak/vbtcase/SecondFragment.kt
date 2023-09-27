package com.bhdrburak.vbtcase

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bhdrburak.vbtcase.common.UserData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment(R.layout.fragment_second) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var button = view.findViewById<Button>(R.id.backButton)

        var textView = view.findViewById<TextView>(R.id.dataTextView)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            arguments?.getParcelable("user", UserData::class.java)
        } else {
            arguments?.getParcelable("user")
        }

        textView.text = user?.username

        //textView.text = arguments?.getString("passdata")

        button.setOnClickListener {
            findNavController().popBackStack()
        }


    }
}
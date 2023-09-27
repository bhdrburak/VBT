package com.bhdrburak.fupscase.login_feature.presentation

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bhdrburak.fupscase.R
import com.bhdrburak.fupscase.databinding.FragmentLoginBinding
import com.bhdrburak.fupscase.login_feature.domain.model.Login
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment @Inject constructor(private val offerSliderAdapter: OfferSliderAdapter) :
    Fragment(R.layout.fragment_login) {


    private var fragmentBinding: FragmentLoginBinding? = null

    lateinit var viewModel: LoginViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        fragmentBinding = FragmentLoginBinding.bind(view)

        fragmentBinding!!.showAllOffer.setOnClickListener {
            showAllOffer()
        }

        fragmentBinding!!.submitButton.setOnClickListener {
            viewModel.login(
                Login(
                    fragmentBinding!!.countryCode.text.toString() +
                            fragmentBinding!!.phoneNumberEditText.text.toString(),
                    fragmentBinding!!.passwordEditText.text.toString()
                )
            )
        }

        fragmentBinding!!.countryCode.setOnClickListener {
            showCountryCodePicker()
        }

        initRecyclerAdapter()
        subscribeToObservers()
    }


    private fun showAllOffer() {
        val fragment = LoginFragmentDirections.actionLoginFragmentToOfferListFragment()
        findNavController().navigate(fragment)
    }

    private fun initRecyclerAdapter() {
        fragmentBinding!!.offerSliderView.adapter = offerSliderAdapter
    }


    private fun subscribeToObservers() {
        viewModel.offerList.observe(viewLifecycleOwner) {
            offerSliderAdapter.offerList = it
        }

        viewModel.formState.observe(viewLifecycleOwner) {
            if (it.passwordError.isNotEmpty()) {
                showError(
                    fragmentBinding!!.passwordTextInputLayout,
                    fragmentBinding!!.passwordEditText,
                    it.passwordError
                )
            } else {
                hideError(
                    fragmentBinding!!.passwordTextInputLayout,
                    fragmentBinding!!.passwordEditText
                )
            }

            if (it.phoneNumberError.isNotEmpty()) {
                showError(
                    fragmentBinding!!.phoneNumberTextInputLayout,
                    fragmentBinding!!.phoneNumberEditText,
                    it.phoneNumberError
                )
            } else {
                hideError(
                    fragmentBinding!!.phoneNumberTextInputLayout,
                    fragmentBinding!!.phoneNumberEditText
                )
            }
        }

    }

    fun showError(
        textInputLayout: TextInputLayout,
        textInputEditText: TextInputEditText,
        errorString: String
    ) {
        textInputLayout.isHelperTextEnabled = true
        textInputLayout.helperText = errorString
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = errorString
        textInputEditText.setBackground(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.custom_error_edittext
            )
        )

    }


    fun hideError(textInputLayout: TextInputLayout, textInputEditText: TextInputEditText) {
        textInputLayout.isHelperTextEnabled = false
        textInputLayout.isErrorEnabled = false
        textInputLayout.error = ""
        textInputEditText.setBackground(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.custom_edittext
            )
        );

    }

    fun showCountryCodePicker() {
        val picker = CountryCodePicker(requireContext())
        picker.ccpDialogShowFlag = true
        picker.setDialogKeyboardAutoPopup(false)
        picker.setOnCountryChangeListener {
            fragmentBinding!!.countryCode.setText(picker.selectedCountryCodeWithPlus.toString())
        }
        picker.launchCountrySelectionDialog()
    }


    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}
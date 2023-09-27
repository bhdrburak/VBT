package com.bhdrburak.fupscase.login_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhdrburak.fupscase.core.domain.model.Offer
import com.bhdrburak.fupscase.login_feature.domain.model.Login
import com.bhdrburak.fupscase.login_feature.domain.use_case.LoginUseCase
import com.bhdrburak.fupscase.login_feature.domain.use_case.OfferSliderUseCase
import com.bhdrburak.fupscase.login_feature.domain.use_case.validation.ValidatePassword
import com.bhdrburak.fupscase.login_feature.domain.use_case.validation.ValidatePhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val offerUseCase: OfferSliderUseCase
) : ViewModel() {

    private val validatePassword = ValidatePassword()
    private val validatePhoneNumber = ValidatePhoneNumber()


    private val formStates = MutableLiveData<LoginFormState>()
    val formState: LiveData<LoginFormState>
        get() = formStates

    private val offers = MutableLiveData<List<Offer>>()
    val offerList: LiveData<List<Offer>>
        get() = offers

    init {
        fetchOffer()
    }

    fun login(login: Login) {
        val phoneNumberResult = validatePhoneNumber.execute(login.phoneNumber)
        val passwordResult = validatePassword.execute(login.password)

        val hasError = listOf(phoneNumberResult, passwordResult).any {
            !it.successful
        }

        formStates.value = LoginFormState(
            passwordResult.errorMessage ?: "",
            phoneNumberResult.errorMessage ?: ""
        )

        viewModelScope.launch {
            loginUseCase.login().collect {

            }
        }
    }


    fun fetchOffer() {
        viewModelScope.launch {
            offerUseCase.fetchOfferSliderList().collect {
                offers.value = it
            }
        }
    }

}
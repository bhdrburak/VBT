package com.bhdrburak.fupscase.login_feature.domain.use_case.validation

import android.telephony.PhoneNumberUtils

class ValidatePhoneNumber {

    fun execute(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "GSM Numarası boş olamaz."
            )
        }
        if (phoneNumber.length < 12) {
            return ValidationResult(
                successful = false,
                errorMessage = "GSM Numarası Uyumlu Değil."
            )
        }
        if (!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            return ValidationResult(
                successful = false,
                errorMessage = "GSM Numarası Uyumlu Değil."
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}
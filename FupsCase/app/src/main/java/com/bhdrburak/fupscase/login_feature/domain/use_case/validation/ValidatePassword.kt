package com.bhdrburak.fupscase.login_feature.domain.use_case.validation

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Şifre alanı boş olamaz."
            )
        }
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Şifreniz minimum 8 karakterli olmalıdır."
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Şifreniz harf ve rakam içermelidir."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}
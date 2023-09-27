package com.bhdrburak.fupscase.login_feature.domain.use_case

import com.bhdrburak.fupscase.login_feature.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend fun login(): Flow<Boolean> {
        return repository.login()
    }

}
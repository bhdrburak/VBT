package com.bhdrburak.fupscase.login_feature.data.repository

import com.bhdrburak.fupscase.login_feature.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {

    override suspend fun login(): Flow<Boolean> {
        return flow {
            emit(true)
        }
    }

}
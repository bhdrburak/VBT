package com.bhdrburak.fupscase.login_feature.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun login(): Flow<Boolean>

}
package com.bhdrburak.fupscase.login_feature.di

import com.bhdrburak.fupscase.login_feature.data.repository.LoginRepositoryImpl
import com.bhdrburak.fupscase.login_feature.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginRepositoryModule {

    @Binds
    abstract fun providesLoginRepository(
        calculatorRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

}
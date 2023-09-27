package com.bhdrburak.vbtmoviedb.di

import androidx.annotation.NonNull
import com.bhdrburak.vbtmoviedb.common.Constant.BASE_URL
import com.bhdrburak.vbtmoviedb.data.api.RequestInterceptor
import com.bhdrburak.vbtmoviedb.data.api.RetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun injectRetrofitAPI(@NonNull okHttpClient: OkHttpClient): RetrofitAPI {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }



}
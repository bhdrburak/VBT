package com.bhdrburak.vbtmoviedb.data.api

import com.bhdrburak.vbtmoviedb.common.Constant.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val originalUrl = originalRequest.url()

        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()


        val requestBuilder = originalRequest.newBuilder().url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)

    }


}
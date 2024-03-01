package com.example.weatherappcompose.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url
            .newBuilder()
            .addQueryParameter(APPID, "{APPID}")
            .build()

        val requestBuilder = request.newBuilder()
            .url(url)

        return chain.proceed(requestBuilder.build())

    }

    companion object {
        private const val APPID = "appid"
    }
}
package com.example.uitest.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("X-Api-Key", "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky")
            .build()
        return chain.proceed(request)
    }
}
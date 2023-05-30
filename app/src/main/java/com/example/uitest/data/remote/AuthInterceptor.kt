package com.example.uitest.data.remote

import okhttp3.Interceptor

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.request().newBuilder()
        .header("X-Api-Key", "eaui/ZHQXSINNhzEtXfoAQ==Q55LXXAaO8fotgky")
        .build()
        .let { chain.proceed(it) }
}
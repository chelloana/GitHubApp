package com.dicoding.githubappuser.api

import com.dicoding.githubappuser.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder()
            .addHeader("Authorization", "Token" + BuildConfig.GITHUB_TOKEN)
            .build())
    }
}
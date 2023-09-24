package com.dicoding.githubappuser.api

import com.dicoding.githubappuser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

//    private val loggingInterceptor = if (BuildConfig.DEBUG) {
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    } else {
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//    }
    private val client = OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
        .addInterceptor(HeaderInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiInstance = retrofit.create(Api::class.java)
}
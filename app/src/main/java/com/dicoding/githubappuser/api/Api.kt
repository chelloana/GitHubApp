package com.dicoding.githubappuser.api

import com.dicoding.githubappuser.BuildConfig
import com.dicoding.githubappuser.data.model.DetailUserResponse
import com.dicoding.githubappuser.data.model.User
import com.dicoding.githubappuser.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
//    @Headers("Authorization: token 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")
    fun getSearchUser(@Query("q") query: String): Call<UserResponse>

    @GET("users/{username}")
//    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String?
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
//    @Headers("Authorization: token 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")
   suspend fun getFollowers(
        @Path("username") username: String
    ): ArrayList<User>

    @GET("users/{username}/following")
//    @Headers("Authorization: token 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")
    suspend  fun getFollowing(
        @Path("username") username: String
    ): ArrayList<User>

}
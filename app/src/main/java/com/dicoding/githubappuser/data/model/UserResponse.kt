package com.dicoding.githubappuser.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("items")
    val items: List<User>
//    @field:SerializedName("Users")
//    val items : List<User>
)
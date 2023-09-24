package com.dicoding.githubappuser.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubappuser.api.Api
import com.dicoding.githubappuser.api.RetrofitClient
import com.dicoding.githubappuser.data.model.User
import com.dicoding.githubappuser.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val apiService: Api) : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        apiService
            .getSearchUser(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            Log.i("TAG", "onResponse: ${response.body()}")
                            listUsers.postValue(response.body()?.items as ArrayList<User>?)
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }
}
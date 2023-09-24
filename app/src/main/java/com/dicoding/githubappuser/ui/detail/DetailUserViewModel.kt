package com.dicoding.githubappuser.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubappuser.api.Api
import com.dicoding.githubappuser.api.RetrofitClient
import com.dicoding.githubappuser.data.model.DetailUserResponse
import retrofit2.Response

class DetailUserViewModel(username: String, private val apiService: Api): ViewModel() {
    var user = MutableLiveData<DetailUserResponse>()

//    var loading = MutableLiveData<Boolean>(false)

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean> = _isLoading

    init {
        setUserDetail(username)
    }

    private fun setUserDetail(username: String?){
        _isLoading.postValue(true)

        apiService
            .getUserDetail(username)
            .enqueue(object : retrofit2.Callback<DetailUserResponse>{
                override fun onResponse(
                    call: retrofit2.Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    _isLoading.postValue(false)

                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: retrofit2.Call<DetailUserResponse>, t: Throwable) {
                    _isLoading.postValue(false)

                    Log.d("Failure", t.message!!)
                }
            })
    }

//    fun getUserDetail(): LiveData<DetailUserResponse> {
//        return user
//    }
}
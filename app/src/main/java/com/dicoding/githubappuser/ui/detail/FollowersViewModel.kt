package com.dicoding.githubappuser.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.githubappuser.api.Api
import com.dicoding.githubappuser.api.RetrofitClient
import com.dicoding.githubappuser.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel(username: String, private val apiService: Api) : ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<User>>(arrayListOf())

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        viewModelScope.launch {
            setListFollowers(username)
        }
    }

    private fun setListFollowers(username: String) {
        coroutineScope.launch {
            try {
                val result = apiService
                    .getFollowers(username)
                listFollowers.postValue(result)
            } catch (e: Exception) {
                Log.e("TAG", "onFailure: ${e.message.toString()} ")
            }
        }
//            apiService
//                .getFollowers(username)
//                .enqueue(object : Callback<ArrayList<User>> {
//                    override fun onResponse(
//                        call: Call<ArrayList<User>>,
//                        response: Response<ArrayList<User>>
//                    ) {
//                        if (response.isSuccessful) {
//                            listFollowers.postValue(response.body())
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
//                        Log.d("Failure", t.message!!)
//                    }
//
//                })

    }

//    fun getListFollowers(): LiveData<ArrayList<User>>{
//        return listFollowers
//    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
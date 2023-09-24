package com.dicoding.githubappuser.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubappuser.api.Api
import com.dicoding.githubappuser.ui.detail.FollowersViewModel
import com.dicoding.githubappuser.ui.detail.FollowingViewModel

@Suppress("UNCHECKED_CAST")
class FollowsViewModelFactory(
    private val username: String,
    private val apiService: Api
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(FollowersViewModel::class.java) -> {
                FollowersViewModel(
                    username,
                    apiService
                ) as T
            }

            modelClass.isAssignableFrom(FollowingViewModel::class.java) -> {
                FollowingViewModel(
                    username,
                    apiService
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
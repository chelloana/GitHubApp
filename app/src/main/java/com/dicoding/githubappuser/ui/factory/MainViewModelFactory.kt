package com.dicoding.githubappuser.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubappuser.api.Api
import com.dicoding.githubappuser.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val apiService: Api
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(
                    apiService
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
package com.zero.githubmyrepo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyRepoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyRepoViewModel::class.java)) {
            return MyRepoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
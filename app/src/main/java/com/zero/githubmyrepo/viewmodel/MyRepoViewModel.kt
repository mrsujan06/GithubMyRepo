package com.zero.githubmyrepo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.githubmyrepo.model.ReposResponse
import com.zero.githubmyrepo.network.RepoApi
import kotlinx.coroutines.*

class MyRepoViewModel : ViewModel() {

    private var _repoObservable: MutableLiveData<List<ReposResponse>> = MutableLiveData()
    val repoObservable: LiveData<List<ReposResponse>>
        get() = _repoObservable

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getData() {
        coroutineScope.launch {
            val getReposDeffered = RepoApi
                .retrofitService
                .getRepos()
            try {
                val repoList = getReposDeffered
                    .await()
                _repoObservable.value = repoList
            } catch (e: Exception) {
                Log.i("MyRepoViewModel", e.message)
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
package com.zero.githubmyrepo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zero.githubmyrepo.model.ReposResponse
import com.zero.githubmyrepo.network.RepoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyRepoViewModel : ViewModel() {
    private val TAG = "MESSAGE"
    private val repoObservable: MutableLiveData<List<ReposResponse>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getData() {
        compositeDisposable.add(
            RepoApi.retrofitService.getRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repos ->
                    repoObservable.value = repos
                }, {
                    Log.i(TAG, it.message)
                })
        )
    }

    fun getRepos() = repoObservable

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
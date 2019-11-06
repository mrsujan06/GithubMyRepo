package com.zero.githubmyrepo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zero.githubmyrepo.viewmodel.MyRepoViewModel
import com.zero.githubmyrepo.viewmodel.MyRepoViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MyRepoActivity : AppCompatActivity() {

    //informing the compiler that this will variable will be assigned later
    private lateinit var repoViewModel: MyRepoViewModel
    private lateinit var repoViewModelFactory: MyRepoViewModelFactory
    private lateinit var repoAdapter: MyRepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repoViewModelFactory = MyRepoViewModelFactory()
        repoViewModel = ViewModelProviders.of(this , repoViewModelFactory).get(MyRepoViewModel::class.java)
        repoViewModel.getData()

        initRecyclerView()
        repoViewModel.repoObservable.observe(this, Observer { repos ->
            repoAdapter.setData(repos)
        })
    }

    private fun initRecyclerView() {
        repo_recyclerview.layoutManager = LinearLayoutManager(this)
        repoAdapter = MyRepoAdapter()
        repo_recyclerview.adapter = repoAdapter

    }
}

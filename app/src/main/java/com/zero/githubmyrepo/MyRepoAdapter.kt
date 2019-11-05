package com.zero.githubmyrepo

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zero.githubmyrepo.model.ReposResponse
import kotlinx.android.synthetic.main.repos_recyclerview.view.*

class MyRepoAdapter : RecyclerView.Adapter<MyRepoAdapter.MyRepoViewHolder>() {
    private val repos = mutableListOf<ReposResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRepoViewHolder {
        val inflatedView = parent.inflate(R.layout.repos_recyclerview, false)
        return MyRepoViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: MyRepoViewHolder, position: Int) {
        holder.bindHolder(repos[position])
    }

    fun setData(reposList: List<ReposResponse>) {
        repos.clear()
        repos.addAll(reposList)
        notifyDataSetChanged()

    }

    class MyRepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView? = itemView.repo_rv_id
        private val name: TextView? = itemView.repo_name_rv

        fun bindHolder(reposeResponse: ReposResponse) {
            id?.text = reposeResponse.id
            name?.text = reposeResponse.name
        }

    }


}

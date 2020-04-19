package com.alimaisam.githubsearch.Adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.alimaisam.githubsearch.Model.Repo
import com.alimaisam.githubsearch.R

class RepoList(private val context: Activity, internal var repos: List<Repo>) : ArrayAdapter<Repo>(context, R.layout.list_view_item, repos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.list_view_item, null, true)

        val textViewName = listViewItem.findViewById(R.id.textViewName) as TextView
        val textViewUrl = listViewItem.findViewById(R.id.textViewUrl) as TextView
        val textViewDesc = listViewItem.findViewById(R.id.textViewDesc) as TextView

        val repo = repos[position]
        textViewName.text = repo.name
        textViewUrl.text = repo.url
        textViewDesc.text = repo.desc

        return listViewItem
    }
}
package com.muzafferus.githubjobs.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.muzafferus.githubjobs.R
import com.muzafferus.githubjobs.data.JobData
import com.muzafferus.githubjobs.view_model.GithubApiStatus

@BindingAdapter("jobType")
fun TextView.setTypeAndLocation(item: JobData) {
    item.let {
        text = "%s/%s".format(item.type, item.location)
    }
}

@BindingAdapter("githubApiStatus")
fun bindStatus(statusImageView: ImageView, status: GithubApiStatus?) {
    when (status) {
        GithubApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        GithubApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        GithubApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
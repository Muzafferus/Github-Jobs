package com.muzafferus.githubjobs.ui.fragment.job_list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.muzafferus.githubjobs.data.JobData

class JobListAdapter : ListAdapter<JobData, JobListViewHolder>(JobDataDiffCallback()) {

    private lateinit var onClickListener: (JobData) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobListViewHolder {
        return JobListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: JobListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.jobElementLayout.setOnClickListener {
            onClickListener = { getItem(position) }
        }
    }

    fun setOnClickListener(onClickListener: (JobData) -> Unit) {
        this.onClickListener = onClickListener
    }
}
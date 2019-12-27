package com.muzafferus.githubjobs.ui.fragment.job_list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.muzafferus.githubjobs.data.JobData
import com.muzafferus.githubjobs.listener.OnClickElement

class JobListAdapter : ListAdapter<JobData, JobListViewHolder>(JobDataDiffCallback()) {

    private lateinit var onClickListener: OnClickElement<JobData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobListViewHolder {
        return JobListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: JobListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.jobElementLayout.setOnClickListener {
            onClickListener.onClickElement(getItem(position))
        }
    }

    fun setOnClickListener(onClickListener: OnClickElement<JobData>) {
        this.onClickListener = onClickListener
    }
}
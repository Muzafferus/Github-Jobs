package com.muzafferus.githubjobs.ui.fragment.job_list

import androidx.recyclerview.widget.DiffUtil
import com.muzafferus.githubjobs.data.JobData

class JobDataDiffCallback : DiffUtil.ItemCallback<JobData>() {
    override fun areItemsTheSame(oldItem: JobData, newItem: JobData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: JobData, newItem: JobData): Boolean =
        oldItem == newItem
}
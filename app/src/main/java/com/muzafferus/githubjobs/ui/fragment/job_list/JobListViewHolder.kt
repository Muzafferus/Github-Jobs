package com.muzafferus.githubjobs.ui.fragment.job_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.muzafferus.githubjobs.data.JobData
import com.muzafferus.githubjobs.databinding.ListItemJobBinding

class JobListViewHolder private constructor(val binding: ListItemJobBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val jobElementLayout: LinearLayout = binding.jobElementLayout

    fun bind(item: JobData) {
        binding.jobData = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): JobListViewHolder {
            return JobListViewHolder(
                ListItemJobBinding.inflate(
                    LayoutInflater
                        .from(parent.context), parent, false
                )
            )
        }
    }
}
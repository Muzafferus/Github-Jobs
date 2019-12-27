package com.muzafferus.githubjobs.factory


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muzafferus.githubjobs.data.JobDatabaseDao
import com.muzafferus.githubjobs.view_model.JobListViewModel

class JobListViewModelFactory(
    private val dataSource: JobDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobListViewModel::class.java)) {
            return JobListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
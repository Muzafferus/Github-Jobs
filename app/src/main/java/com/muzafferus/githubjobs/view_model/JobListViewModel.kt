package com.muzafferus.githubjobs.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muzafferus.githubjobs.data.JobData
import com.muzafferus.githubjobs.data.JobDatabaseDao
import com.muzafferus.githubjobs.network.GithubApi
import kotlinx.coroutines.*

enum class GithubApiStatus { LOADING, ERROR, DONE }

class JobListViewModel(
    private val database: JobDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private val _status = MutableLiveData<GithubApiStatus>()
    val status: LiveData<GithubApiStatus>
        get() = _status

    private val _jobList = MutableLiveData<List<JobData>>()
    val jobDataList: LiveData<List<JobData>>
        get() = _jobList

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getApiRequest()
    }

    private fun getApiRequest() {
        coroutineScope.launch {

            _jobList.value = getJobListFromDatabase()
            val getJobDiffered = GithubApi.retrofitService.getJobListAsync()
            try {
                if (jobDataList.value?.size ?: 0 == 0) {
                    _status.value = GithubApiStatus.LOADING
                } else {
                    _status.value = GithubApiStatus.DONE
                }

                val listJob = getJobDiffered.await()
                withContext(Dispatchers.IO) {
                    database.clear()
                }
                for (i in listJob.indices) {
                    withContext(Dispatchers.IO) {
                        database.insert(listJob[i])
                    }
                    if (i == (listJob.size - 1)) {
                        _status.value = GithubApiStatus.DONE
                        _jobList.value = getJobListFromDatabase()

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                if (jobDataList.value?.size ?: 0 == 0) {
                    _status.value = GithubApiStatus.ERROR
                    _jobList.value = ArrayList()
                }
            }
        }

    }

    private suspend fun getJobListFromDatabase(): List<JobData> {
        return withContext(Dispatchers.IO) {
            val jobs = database.getAllJobs()
            jobs
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

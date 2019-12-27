package com.muzafferus.githubjobs.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JobDatabaseDao {
    @Insert
    fun insert(jobData: JobData)

    @Query("SELECT * from job_table WHERE id = :key")
    fun get(key: Long): JobData?

    @Query("SELECT * FROM job_table ORDER BY id")
    fun getAllJobs(): List<JobData>

    @Query("DELETE FROM job_table")
    fun clear()
}
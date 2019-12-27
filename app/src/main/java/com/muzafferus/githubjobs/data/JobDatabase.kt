package com.muzafferus.githubjobs.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [JobData::class], version = 1, exportSchema = false)
abstract class JobDatabase : RoomDatabase() {

    abstract val jobDatabaseDao: JobDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: JobDatabase? = null

        fun getInstance(context: Context): JobDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JobDatabase::class.java,
                        "job_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }
}
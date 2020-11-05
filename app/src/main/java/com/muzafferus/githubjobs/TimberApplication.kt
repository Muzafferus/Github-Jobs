package com.muzafferus.githubjobs

import android.app.Application
import timber.log.Timber

class TimberApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ':' + element.lineNumber
                }
            })
        }
    }
}
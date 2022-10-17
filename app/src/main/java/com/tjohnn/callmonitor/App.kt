package com.tjohnn.callmonitor

import android.app.Application
import com.tjohnn.callmonitor.logging.TimberTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(TimberTree())
    }
}

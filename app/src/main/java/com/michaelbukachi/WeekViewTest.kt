package com.michaelbukachi

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class WeekViewTest : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
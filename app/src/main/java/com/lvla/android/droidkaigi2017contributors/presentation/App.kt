package com.lvla.android.droidkaigi2017contributors.presentation

import android.app.Application
import com.lvla.android.droidkaigi2017contributors.di.ApplicationComponent
import com.lvla.android.droidkaigi2017contributors.di.ApplicationModule
import com.lvla.android.droidkaigi2017contributors.di.DaggerApplicationComponent
import timber.log.Timber

class App : Application() {
  val component: ApplicationComponent by lazy {
    DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }

  override fun onCreate() {
    Timber.plant(Timber.DebugTree())
  }
}
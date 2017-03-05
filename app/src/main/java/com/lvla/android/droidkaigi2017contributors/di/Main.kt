package com.lvla.android.droidkaigi2017contributors.di

import com.lvla.android.droidkaigi2017contributors.presentation.MainActivity
import com.lvla.android.droidkaigi2017contributors.presentation.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainModule(private val contract: MainPresenter.Contract) {

  @Provides
  fun provideContract() = contract
}

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent{
  fun inject(a: MainActivity)
}
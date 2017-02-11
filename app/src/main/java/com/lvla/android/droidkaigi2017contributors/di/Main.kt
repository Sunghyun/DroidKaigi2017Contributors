package com.lvla.android.droidkaigi2017contributors.di

import com.lvla.android.droidkaigi2017contributors.presentation.MainActivity
import com.lvla.android.droidkaigi2017contributors.presentation.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class Main(private val contract: MainPresenter.Contract) {

  @Provides
  fun provideContract() = contract
}

@Subcomponent(modules = arrayOf(Main::class))
interface MainComponent{
  fun inject(a: MainActivity)
}
package com.lvla.android.droidkaigi2017contributors.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
class ApplicationModule {

  @Provides
  fun providesOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor { log -> Timber.tag("OkHttp").v(log) })
        .build()
  }

  @Provides
  fun provideRetrofit(oktHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(oktHttpClient)
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

}
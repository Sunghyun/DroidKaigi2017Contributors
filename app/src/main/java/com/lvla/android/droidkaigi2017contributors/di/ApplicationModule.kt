package com.lvla.android.droidkaigi2017contributors.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.lvla.android.droidkaigi2017contributors.infra.api.GitHubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
class ApplicationModule {

  private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

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
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides
  fun provideGitHubService(retrofit: Retrofit): GitHubService {
    return retrofit.create(GitHubService::class.java)
  }
}
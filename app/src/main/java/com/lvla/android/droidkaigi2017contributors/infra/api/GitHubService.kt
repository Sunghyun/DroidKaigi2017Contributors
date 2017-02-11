package com.lvla.android.droidkaigi2017contributors.infra.api

import com.lvla.android.droidkaigi2017contributors.domain.entity.Contributor
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubService {
  @GET("/repos/{owner}/{repo}/contributors")
  fun fetchContributors(@Path("owner") owner: String,
                        @Path("repo") repo: String,
                        @Query("per_page") perPage: Int): Single<List<Contributor>>
}
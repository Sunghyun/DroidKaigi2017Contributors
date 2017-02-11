package com.lvla.android.droidkaigi2017contributors.infra.repositoryu

import com.lvla.android.droidkaigi2017contributors.domain.entity.Contributor
import com.lvla.android.droidkaigi2017contributors.infra.api.GitHubClient
import io.reactivex.Single
import javax.inject.Inject

class ContributorRepository @Inject constructor(private val client: GitHubClient) {
  fun getContributors(): Single<List<Contributor>> {
    return client.fetchContributors()
  }
}
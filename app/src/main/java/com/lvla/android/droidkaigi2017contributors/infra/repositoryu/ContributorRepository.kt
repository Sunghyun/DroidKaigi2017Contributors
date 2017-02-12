package com.lvla.android.droidkaigi2017contributors.infra.repositoryu

import com.lvla.android.droidkaigi2017contributors.domain.entity.Contributor
import com.lvla.android.droidkaigi2017contributors.infra.api.GitHubClient
import com.lvla.android.droidkaigi2017contributors.infra.dao.ContributorDao
import io.reactivex.Single
import javax.inject.Inject

class ContributorRepository @Inject constructor(private val client: GitHubClient, private val dao: ContributorDao) {

  fun getContributors(): Single<List<Contributor>> {
    return client.fetchContributors()
        .doOnEvent { list, throwable -> list?.let { dao.insert(it) } }
        .onErrorResumeNext { dao.findAll() }
  }
}
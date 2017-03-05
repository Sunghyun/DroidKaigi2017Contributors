package com.lvla.android.droidkaigi2017contributors.infra.repositoryu

import com.lvla.android.droidkaigi2017contributors.domain.entity.Contributor
import com.lvla.android.droidkaigi2017contributors.infra.api.GitHubClient
import com.lvla.android.droidkaigi2017contributors.infra.dao.ContributorDao
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContributorRepository @Inject constructor(private val client: GitHubClient, private val dao: ContributorDao) {

  fun getContributors(): Observable<List<Contributor>> {
    return client.fetchContributors()
        .doOnNext { users -> dao.insert(users) }
        .onErrorReturn { dao.findAll() }
  }
}
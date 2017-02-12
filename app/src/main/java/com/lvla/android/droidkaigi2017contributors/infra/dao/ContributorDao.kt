package com.lvla.android.droidkaigi2017contributors.infra.dao

import com.github.gfx.android.orma.annotation.OnConflict
import com.lvla.android.droidkaigi2017contributors.di.ApplicationModule
import com.lvla.android.droidkaigi2017contributors.domain.entity.Contributor
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContributorDao @Inject constructor(ormaHolder: ApplicationModule.OrmaHolder) {
  private val orma = ormaHolder.orma

  fun insert(contributors: List<Contributor>) {
    orma.transactionSync { orma.prepareInsertIntoContributor(OnConflict.REPLACE).executeAll(contributors) }
  }

  fun findAll(): Single<List<Contributor>>
      = orma.selectFromContributor().executeAsObservable().toList()
}
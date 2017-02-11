package com.lvla.android.droidkaigi2017contributors.presentation

import com.lvla.android.droidkaigi2017contributors.domain.entity.Contributor
import com.lvla.android.droidkaigi2017contributors.extension.addTo
import com.lvla.android.droidkaigi2017contributors.extension.applySchedulers
import com.lvla.android.droidkaigi2017contributors.infra.repositoryu.ContributorRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(private val contract: Contract, private val repository: ContributorRepository) {
  private val disposables = CompositeDisposable()

  fun getContributors() {
    repository.getContributors()
        .applySchedulers()
        .subscribe(
            { contract.showContributors(it) },
            { Timber.e(it, "#getContributors") }
        )
        .addTo(disposables)
  }

  fun dispose() {
    disposables.dispose()
  }

  interface Contract {
    fun showContributors(contributors: List<Contributor>)
  }
}
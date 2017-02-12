package com.lvla.android.droidkaigi2017contributors.infra.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubClient @Inject constructor(private val service: GitHubService) {
  fun fetchContributors() = service.fetchContributors("DroidKaigi", "conference-app-2017", 100)
}
package com.lvla.android.droidkaigi2017contributors.domain.entity

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("login") val name: String,
    val avatarUrl: String?,
    val htmlUrl: String?,
    val contributions: Int
)
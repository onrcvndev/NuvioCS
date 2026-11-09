package com.nuvio.tv.updater.model

import com.nuvio.tv.updater.VersionUtils

/** The downloaded release, kept with its APK so a background install can tell it is still newer. */
data class DownloadedUpdate(
    val tag: String,
    val path: String,
    val publishedAt: String?,
    val prerelease: Boolean
) {
    @Suppress("UNUSED_PARAMETER")
    fun isNewerThan(versionName: String, versionCode: Long): Boolean =
        VersionUtils.isRemoteNewer(tag, versionName)
}

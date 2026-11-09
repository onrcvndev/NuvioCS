package com.nuvio.tv.updater

import com.nuvio.tv.updater.model.DownloadedUpdate
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DownloadedUpdateTest {
    private val versionCode = 1L

    @Test
    fun `background cache keeps a downloaded release newer than the installed one`() {
        val downloaded = DownloadedUpdate("v1.1.0", "/updates/app.apk", "2026-10-23T09:00:00Z", false)
        assertTrue(downloaded.isNewerThan("1.0.0", versionCode))
        assertTrue(downloaded.isNewerThan("1.1.0-beta.2", versionCode))
    }

    @Test
    fun `background cache keeps a newer pre-release`() {
        val downloaded = DownloadedUpdate("v1.2.0-beta.1", "/updates/app.apk", "2026-10-23T09:00:00Z", true)
        assertTrue(downloaded.isNewerThan("1.1.0", versionCode))
    }

    @Test
    fun `background cache drops the installed or an older release`() {
        val downloaded = DownloadedUpdate("v1.0.0", "/updates/app.apk", "2026-10-23T07:00:00Z", false)
        assertFalse(downloaded.isNewerThan("1.0.0", versionCode))
        assertFalse(downloaded.isNewerThan("1.0.1", versionCode))
    }
}

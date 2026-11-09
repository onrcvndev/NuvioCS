package com.nuvio.tv.updater

import com.nuvio.tv.data.remote.dto.GitHubReleaseDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ReleaseSelectorTest {

    @Test
    fun `single channel lists final and pre-releases newest first`() {
        val releases = listOf(
            release("1.1.0-beta.10", prerelease = true),
            release("1.1.0"),
            release("1.1.0-beta.9", prerelease = true)
        )

        val selected = ReleaseSelector.eligibleReleases(releases, UpdateChannel.BETA)

        assertEquals(
            listOf("1.1.0", "1.1.0-beta.10", "1.1.0-beta.9"),
            selected.map { it.tagName }
        )
    }


    @Test
    fun `drafts and invalid tags are excluded`() {
        val releases = listOf(
            release("1.2.0", draft = true),
            release("nightly"),
            release("1.1.0")
        )

        val selected = ReleaseSelector.eligibleReleases(releases, UpdateChannel.BETA)

        assertEquals(listOf("1.1.0"), selected.map { it.tagName })
    }

    @Test
    fun `autosync fork only follows canonical revision tags`() {
        val releases = listOf(
            release("1.0.1-autosync"),
            release("1.0.0-autosync.2"),
            release("1.0.0-autosync.1"),
            release("1.0.0")
        )

        val selected = ReleaseSelector.eligibleReleases(
            releases = releases,
            channel = UpdateChannel.BETA,
            canonicalAutoSyncOnly = true
        )

        assertEquals(
            listOf("1.0.0-autosync.2", "1.0.0-autosync.1"),
            selected.map { it.tagName }
        )
    }


    private fun release(
        tag: String,
        name: String = tag,
        draft: Boolean = false,
        prerelease: Boolean = false
    ) = GitHubReleaseDto(
        tagName = tag,
        name = name,
        draft = draft,
        prerelease = prerelease
    )
}

package com.nuvio.tv.updater

/**
 * Nuvio CS has a single update channel that follows every published release, pre-releases
 * included. Values stored by older builds ("stable", "tester") resolve to it as well.
 */
enum class UpdateChannel(val storedValue: String) {
    BETA("beta");

    companion object {
        @Suppress("UNUSED_PARAMETER")
        fun fromStoredValue(value: String?): UpdateChannel = BETA

        @Suppress("UNUSED_PARAMETER")
        fun defaultForVersion(versionName: String): UpdateChannel = BETA
    }
}

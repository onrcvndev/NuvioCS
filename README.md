<div align="center">

  <h1>Nuvio CS</h1>

  <p>
    A fork of <a href="https://github.com/NuvioMedia/NuvioTV">NuvioTV</a> for Android TV, with
    CloudStream (<code>.cs3</code>) plugin support.
  </p>

  [Download](https://github.com/onrcvndev/NuvioCS/releases/latest) · [All releases](https://github.com/onrcvndev/NuvioCS/releases) · [Issues](https://github.com/onrcvndev/NuvioCS/issues)

</div>

Nuvio CS keeps everything NuvioTV offers, including signing in with your Nuvio account to sync
your library and progress, and adds the features below. It installs as its own app
(`com.onrcvndev.nuvio`) next to the official Nuvio and is versioned independently, starting at 1.0.0.

## CloudStream plugins

Nuvio CS runs CloudStream `.cs3` extensions as stream providers.

1. Open **Settings > Plugins**. If you don't see it, set **Settings > Experience > Experience
   mode** to **Advanced**.
2. Under **Add repository**, enter a CloudStream repository URL (a `repo.json`) and choose **Add**.
3. Every extension in the repository appears under **Providers**. Turn each one on or off, use
   **Test** to check that it finds streams, and **Settings** to open the extension's own settings
   (for example, updating a site's address when it moves).

Streams from enabled extensions show up next to your add-on streams when you play a title.
Hub extensions that bundle several sites in one `.cs3` file are supported, and extensions marked
Beta in their repository can be enabled; only ones marked Down are turned off.

## More settings

Open **Settings > Nuvio CS** for the options added on top of NuvioTV:

- **Auto Sync Subtitles.** Align an add-on subtitle with subtitles embedded in the video, or with
  the audio when there are none.
- **Bubble notifications.** Show sync progress and results in a small bubble instead of toasts.
- **Automatically try another source.** If playback fails to start, move on to the next source.
- **Sound and picture formats.** Passthrough for TrueHD, DTS-HD and Atmos, surround matched to your
  audio system, and Dolby Vision handling. Built-in player only.
- **Seek previews.** Use your own Seekr key, or generate preview thumbnails on the device.
- **Custom subtitle fonts.** Upload a `.ttf` or `.otf` font from your phone.
- **Match streams to connection.** Move streams that are likely too heavy for your connection to
  the end of the list.
- **Pill menu** and **Netflix-style Home** layouts.

The app is fully translated into Turkish.

## Installation

Download the APK for your device from [Releases](https://github.com/onrcvndev/NuvioCS/releases)
and sideload it onto your Android TV. Most TVs and streaming boxes need the `arm64-v8a` APK; the
`universal` APK runs everywhere but is larger. Once installed, the app offers new releases itself.

## Build from source

Use Android Studio, JDK 17 and the Android SDK.

```bash
git clone https://github.com/onrcvndev/NuvioCS.git
cd NuvioCS
```

Create `local.properties` in the project root:

```properties
sdk.dir=/path/to/Android/sdk
TMDB_API_KEY=your_tmdb_api_key
```

`TMDB_API_KEY` is required for CloudStream extensions, which search sites by a title's name.
Then build the debug APKs:

```bash
./gradlew :app:assembleFullDebug
```

The app's name, application id, version and update repository live in
[`fork.properties`](./fork.properties).

## Releasing

1. Raise `versionName` and `versionCode` in `fork.properties` and push.
2. Run the **Nuvio CS Release** workflow from the Actions tab, as a draft to review or published
   directly.

The workflow needs these repository secrets: `NUVIO_CS_LOCAL_PROPERTIES_BASE64` (a base64-encoded
`local.properties` with `TMDB_API_KEY` and any other service keys), `NUVIO_CS_KEYSTORE_BASE64`,
`NUVIO_CS_STORE_PASSWORD`, `NUVIO_CS_KEY_ALIAS` and `NUVIO_CS_KEY_PASSWORD`. Keep the release
keystore safe: updates only install over APKs signed with the same key.

## License

Licensed under the [GNU General Public License v3.0](./LICENSE), the same as NuvioTV.

## Legal

Nuvio CS browses metadata and plays media from user-installed extensions or user-provided sources.
Use it only for content you own or are authorized to access. It does not host, store or distribute
media and is not affiliated with third-party extensions or content providers.

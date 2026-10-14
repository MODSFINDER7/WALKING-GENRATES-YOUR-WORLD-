# Walking Generates Your World (prototype)

NeoForge 26.2 mod source project. The prototype attempts to create a starter platform in a void-like area and fills nearby completely empty chunks with a simple deterministic terrain made of vanilla blocks.

## Important limitations

- This is **not** Minecraft's native Overworld terrain generator. Terrain is a simplified custom block fill.
- It is a prototype and has not been successfully compiled or tested in-game in this environment.
- The empty-chunk detection is heuristic; do not use it on an important existing world without making a backup.
- The project targets Minecraft 26.2 / Java 25, as required by the official NeoForge 26.2 MDK.

## Build a JAR from a phone using GitHub Actions

1. Create a new GitHub repository.
2. Extract this ZIP and upload the **contents** of the `walkingworld` folder to the repository root (not the ZIP itself and not an extra nested folder).
3. Commit the files, including `.github/workflows/build.yml`.
4. Open the repository's **Actions** tab. The `Build WalkingWorld JAR` workflow should start after the commit. You can also select **Run workflow**.
5. Open the completed workflow run. If it succeeds, download the `walkingworld-jar` artifact from the Artifacts section and extract it to get the `.jar`.
6. If it fails, download `walkingworld-build-logs` if available and inspect the build error.

The workflow installs Java 25 and Gradle 9.2.1 on GitHub's runner; no Java or Gradle installation is needed on the phone. GitHub Actions must be enabled for the repository.

## Install

Place the built JAR in the `mods` folder for the matching NeoForge 26.2 installation. Back up worlds before testing.

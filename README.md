# Walking Generates Your World

A NeoForge 26.2 mod for Java 25.

## Current prototype

- Generates a small terrain patch when a player first appears or moves into a new block position.
- Only fills air blocks, so existing solid terrain is not intentionally replaced.
- Uses deterministic, lightweight vanilla block columns: grass, dirt, and stone.

## Important limitations

This is an early playable prototype, not a full recreation of Carvs' video. It does not yet reproduce native Overworld biome, cave, structure, ore, or complete seed-based terrain generation. In an ordinary generated Overworld, most terrain is already occupied, so the mechanic is intended to be tested in a void/empty world. Back up worlds before testing.

## Build

The repository workflow builds with Java 25 and Gradle 9.2.1. Push a commit or run the workflow manually, then download the `walkingworld-jar` artifact from the Actions run.

## Install

Put the generated JAR in the `mods` folder of a matching NeoForge 26.2 installation.

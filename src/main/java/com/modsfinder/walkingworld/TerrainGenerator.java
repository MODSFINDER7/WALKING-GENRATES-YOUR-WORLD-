package com.modsfinder.walkingworld;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class TerrainGenerator {
    private static final int PATCH_RADIUS = 2;
    private static final int TERRAIN_DEPTH = 4;

    private TerrainGenerator() {
    }

    public static void generateAroundPlayer(ServerLevel level, BlockPos playerPosition) {
        generatePatch(level, playerPosition, playerPosition.getY() - 1);
    }

    public static void generateAroundProjectile(ServerLevel level, BlockPos impactPosition) {
        generatePatch(level, impactPosition, impactPosition.getY());
    }

    private static void generatePatch(ServerLevel level, BlockPos center, int baseSurfaceY) {
        for (int x = center.getX() - PATCH_RADIUS; x <= center.getX() + PATCH_RADIUS; x++) {
            for (int z = center.getZ() - PATCH_RADIUS; z <= center.getZ() + PATCH_RADIUS; z++) {
                int surfaceY = baseSurfaceY + heightOffset(x, z, center);

                if (surfaceY <= level.getMinBuildHeight() + TERRAIN_DEPTH
                        || surfaceY >= level.getMaxBuildHeight()) {
                    continue;
                }

                placeIfEmpty(level, new BlockPos(x, surfaceY, z), Blocks.GRASS_BLOCK.defaultBlockState());
                placeIfEmpty(level, new BlockPos(x, surfaceY - 1, z), Blocks.DIRT.defaultBlockState());
                placeIfEmpty(level, new BlockPos(x, surfaceY - 2, z), Blocks.DIRT.defaultBlockState());
                placeIfEmpty(level, new BlockPos(x, surfaceY - 3, z), Blocks.STONE.defaultBlockState());
            }
        }
    }

    private static int heightOffset(int x, int z, BlockPos center) {
        if (x == center.getX() && z == center.getZ()) {
            return 0;
        }

        long value = (long) x * 341873128712L + (long) z * 132897987541L;
        value ^= value >>> 29;
        return (int) Math.floorMod(value, 3L) - 1;
    }

    private static void placeIfEmpty(ServerLevel level, BlockPos position, BlockState state) {
        if (position.getY() < level.getMinBuildHeight()
                || position.getY() >= level.getMaxBuildHeight()
                || !level.isEmptyBlock(position)) {
            return;
        }

        level.setBlock(position, state, 3);
    }
}

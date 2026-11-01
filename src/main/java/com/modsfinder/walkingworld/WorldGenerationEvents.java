package com.modsfinder.walkingworld;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public final class WorldGenerationEvents {
    private final Map<UUID, LastPosition> lastPositions = new HashMap<>();

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        BlockPos currentPosition = player.blockPosition();
        LastPosition previous = lastPositions.put(
                player.getUUID(),
                new LastPosition(serverLevel.dimension(), currentPosition)
        );

        if (previous == null
                || !previous.dimension().equals(serverLevel.dimension())
                || !previous.position().equals(currentPosition)) {
            TerrainGenerator.generateAroundPlayer(serverLevel, currentPosition);
        }
    }

    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();

        if (!(projectile.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        Vec3 hitLocation = event.getRayTraceResult().getLocation();
        BlockPos impactPosition = BlockPos.containing(
                hitLocation.x,
                hitLocation.y,
                hitLocation.z
        );

        TerrainGenerator.generateAroundProjectile(serverLevel, impactPosition);
    }

    private record LastPosition(
            net.minecraft.resources.ResourceKey<Level> dimension,
            BlockPos position
    ) {
    }
}

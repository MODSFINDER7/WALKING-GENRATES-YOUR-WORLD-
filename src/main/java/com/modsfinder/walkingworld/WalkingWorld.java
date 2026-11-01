package com.modsfinder.walkingworld;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(WalkingWorld.MOD_ID)
public final class WalkingWorld {
    public static final String MOD_ID = "walkingworld";

    public WalkingWorld() {
        NeoForge.EVENT_BUS.register(new WorldGenerationEvents());
    }
}

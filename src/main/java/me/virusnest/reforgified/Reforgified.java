package me.virusnest.reforgified;

import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reforgified implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "reforgified";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ComponentType<?> MY_COMPONENT_TYPE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(MOD_ID, "forge_type"),
            ComponentType.<ForgePoolEntry>builder().codec(ForgePoolEntry.CODEC).build()
    );
    public static RegistryKey<Registry<ForgePoolEntry>> FORGE_POOLS = RegistryKey.ofRegistry(Identifier.of(MOD_ID, "forge_pools"));
    public static SimpleRegistry<ForgePoolEntry> FORGE_POOL = FabricRegistryBuilder.createSimple(FORGE_POOLS)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Hello Fabric world!");



    }
}
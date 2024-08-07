package me.virusnest.reforgified;

import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Instrument;
import net.minecraft.loot.LootPool;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.collection.Weight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.util.math.random.Random;

public class Reforgified implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    public static final String MOD_ID = "reforgified";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static RegistryKey<Registry<ForgePoolEntry>> FORGE_POOLS = RegistryKey.ofRegistry(Identifier.of(MOD_ID, "forge_pools"));
    public static ForgePoolEntry COMMON = register(new ForgePoolEntry(Weight.of(1), null, "common"), "common");
    public static SimpleRegistry<ForgePoolEntry> FORGE_POOL = FabricRegistryBuilder.createDefaulted(FORGE_POOLS,Identifier.of(MOD_ID,"common"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
    public static ComponentType<?> FORGE_TYPE_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(MOD_ID, "forge_type"),
            ComponentType.<RegistryEntry<ForgePoolEntry>>builder().codec(FORGE_POOL.getEntryCodec()).build()
    );
    @Override
    public void onInitialize() {
        Pool<ForgePoolEntry> pool = Pool.of(FORGE_POOL.stream().toList());
        Random random = Random.create();
        pool.getOrEmpty(random);
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Hello Fabric world!");

    }
    public static ForgePoolEntry register(ForgePoolEntry item, String id) {
        // Create the identifier for the item.
        Identifier poolID = Identifier.of(MOD_ID, id);

        // Register the item.
        ForgePoolEntry registeredPool = Registry.register(FORGE_POOL, poolID, item);

        // Return the registered item!
        return registeredPool;
    }
}
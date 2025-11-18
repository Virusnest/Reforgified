package me.virusnest.reforgified;

import net.minecraft.registry.Registry;
import net.minecraft.util.collection.Pool;


public class ForgePool {
    Pool<ForgePoolEntry> pool;
    public ForgePool(Registry<ForgePoolEntry> registry) {
        this.pool = Pool.of(registry.stream().toList());
    }
}

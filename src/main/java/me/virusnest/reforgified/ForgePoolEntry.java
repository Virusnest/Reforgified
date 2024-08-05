package me.virusnest.reforgified;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;

import java.util.List;

public class ForgePoolEntry {
    public static Codec<ForgePoolEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("weight").forGetter(ForgePoolEntry::getWeight),
            Codec.FLOAT.fieldOf("multiplier").forGetter(ForgePoolEntry::getMultiplier),
            Registries.ATTRIBUTE.getCodec().listOf().fieldOf("attributes").forGetter(ForgePoolEntry::getAttributeList),
            Codec.STRING.fieldOf("name").forGetter(ForgePoolEntry::getName)
            ).apply(instance, ForgePoolEntry::new));
    public ForgePoolEntry(int weight, float multiplier, List<EntityAttribute> attributeList, String name) {
        this.weight = weight;
        this.multiplier = multiplier;
        this.attributeList = attributeList;
        this.name = name;
    }
    private final String name;
    private final int weight;
    private final List<EntityAttribute> attributeList;
    private final float multiplier;
    public int getWeight() {
        return weight;
    }

    public float getMultiplier() {
        return multiplier;
    }
    public List<EntityAttribute> getAttributeList() {
        return attributeList;
    }
    public String getName() {
        return name;
    }


}
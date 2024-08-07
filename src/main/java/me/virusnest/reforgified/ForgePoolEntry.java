package me.virusnest.reforgified;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.Weight;
import net.minecraft.util.collection.Weighted;

import java.util.List;

public class ForgePoolEntry implements Weighted {
    public static Codec<ForgePoolEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Weight.CODEC.fieldOf("weight").forGetter(ForgePoolEntry::getWeight),
            Registries.ATTRIBUTE.getCodec().listOf().fieldOf("attributes").forGetter(ForgePoolEntry::getAttributeList),
            Codec.STRING.fieldOf("name").forGetter(ForgePoolEntry::getName)
            ).apply(instance, ForgePoolEntry::new));
    public static final Codec<RegistryEntry<ForgePoolEntry>> ENTRY_CODEC = RegistryElementCodec.of(Reforgified.FORGE_POOLS, CODEC);
    public ForgePoolEntry(Weight weight, List<EntityAttribute> attributeList, String name) {
        this.weight = weight;
        this.attributeList = attributeList;
        this.name = name;
    }

    private final String name;
    private final Weight weight;
    private final List<EntityAttribute> attributeList;

    public List<EntityAttribute> getAttributeList() {
        return attributeList;
    }
    public String getName() {
        return name;
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    public record ForgeTypeComponent(RegistryKey<ForgePoolEntry> forgePool) {


        public static final Codec<ForgeTypeComponent> CODEC = RecordCodecBuilder.create(builder -> {
            return builder.group(
                   RegistryKey.createCodec(Reforgified.FORGE_POOLS).fieldOf("id").forGetter(ForgeTypeComponent::forgePool)
            ).apply(builder, ForgeTypeComponent::new);
        });



    }

}

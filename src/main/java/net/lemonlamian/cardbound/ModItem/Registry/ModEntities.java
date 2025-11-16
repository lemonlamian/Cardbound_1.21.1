package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.lemonlamian.cardbound.ModItem.CardClasses.CardProjectile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Cardbound.MODID);

    public static ResourceKey<EntityType<?>> CARD_PROJECTILE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("card_projectile"));

    public static final Supplier<EntityType<CardProjectile>> CARD_PROJECTILE = ENTITY_TYPES.register("card_projectile", () -> EntityType.Builder.<CardProjectile>of(CardProjectile::new, MobCategory.MISC).sized(0.6f, 0.6f).build("card_projectile"));
}

package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.lemonlamian.cardbound.ModItem.CardEntities.NetherLegionSoldier;
import net.lemonlamian.cardbound.ModItem.CardEntities.TheGreatestKnight;
import net.lemonlamian.cardbound.ModItem.CardMiscClasses.CardProjectile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Cardbound.MODID);

    public static final Supplier<EntityType<CardProjectile>> CARD_PROJECTILE = ENTITY_TYPES.register
            ("card_projectile", () -> EntityType.Builder.<CardProjectile>of(CardProjectile::new, MobCategory.MISC).sized(0.6f, 0.6f).build("card_projectile"));

    public static final Supplier<EntityType<NetherLegionSoldier>> NETHER_LEGION_SOLDIER = ENTITY_TYPES.register
            ("nether_legion_soldier", () -> EntityType.Builder.of(NetherLegionSoldier::new, MobCategory.MONSTER).sized(0.7f, 2.4f).fireImmune().immuneTo(Blocks.WITHER_ROSE).eyeHeight(2.1f).ridingOffset(-0.875F).clientTrackingRange(8).build("nether_legion_soldier"));

    public static final Supplier<EntityType<TheGreatestKnight>> THE_GREATEST_KNIGHT = ENTITY_TYPES.register
            ("the_greatest_knight", () -> EntityType.Builder.of(TheGreatestKnight::new, MobCategory.MONSTER).sized(0.7f, 2.4f).fireImmune().immuneTo(Blocks.WITHER_ROSE).eyeHeight(2.1f).ridingOffset(-0.875F).clientTrackingRange(8).build("the_greatest_knight"));
}

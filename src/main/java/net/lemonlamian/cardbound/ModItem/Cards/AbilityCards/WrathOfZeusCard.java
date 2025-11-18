package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardMiscClasses.CardProjectile;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.lemonlamian.cardbound.ModItem.Registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WrathOfZeusCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.wrath_of_zeus_card.line1", "tooltip.cardbound.wrath_of_zeus_card.line2", "tooltip.cardbound.wrath_of_zeus_card.line3"};
    public WrathOfZeusCard(Item.Properties properties) {
        super(properties, 2000, CardCategory.ATTACK, CardRarity.LEGENDARY, tooltipLineArray);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level.isClientSide()) return true;

        if (user instanceof Player player && player instanceof ServerPlayer serverPlayer) {
            EntityType<CardProjectile> type = ModEntities.CARD_PROJECTILE.get();
            CardProjectile proj = new CardProjectile(type, player, level, stack);

            proj.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.6f, 0.0f);

            level.addFreshEntity(proj);

            return true;
        }

        return false;
    }

    @Override
    public boolean onActivateOnEntityHit(Level level, LivingEntity shooter, Entity hitEntity, ItemStack stack) {

        if (shooter != null && hitEntity != null && level instanceof ServerLevel serverLevel) {
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
            lightning.moveTo(hitEntity.position());
            lightning.setDamage(40.0f);
            lightning.setVisualOnly(false);
            serverLevel.addFreshEntity(lightning);

            serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, hitEntity.getX(), hitEntity.getY(), hitEntity.getZ(), 1, 1, 1, 1, 0.15);
            serverLevel.sendParticles(ParticleTypes.INSTANT_EFFECT, hitEntity.getX(), hitEntity.getY(), hitEntity.getZ(), 20, 1, 1, 1, 1);

            return true;
        }

        return false;
    }

    @Override
    public boolean onActivateOnBlockHit(Level level, LivingEntity shooter, BlockPos hitBlockPos, ItemStack stack) {

        if (shooter != null && hitBlockPos != null && level instanceof ServerLevel serverLevel) {
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
            lightning.moveTo(hitBlockPos, 0.0f, 0.0f);
            lightning.setDamage(40.0f);
            lightning.setVisualOnly(false);
            serverLevel.addFreshEntity(lightning);

            serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, hitBlockPos.getX(), hitBlockPos.getY() + 0.5, hitBlockPos.getZ(), 1, 1, 1, 1, 0.15);
            serverLevel.sendParticles(ParticleTypes.INSTANT_EFFECT, hitBlockPos.getX(), hitBlockPos.getY() + 0.5, hitBlockPos.getZ(), 20, 1, 1, 1, 1);


            return true;
        }

        return false;
    }
}

package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardMiscClasses.CardProjectile;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.lemonlamian.cardbound.ModItem.Registry.ModEntities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PositionSwapCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.position_swap_card.line1", "tooltip.cardbound.position_swap_card.line2", "tooltip.cardbound.position_swap_card.line3"};
    public PositionSwapCard(Item.Properties properties) {
        super(properties.stacksTo(1), 200, CardCategory.UTILITY, CardRarity.UNCOMMON, tooltipLineArray);
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

        if (shooter != null && hitEntity != null && !level.isClientSide()) {

            Vec3 shooterPos = shooter.position();
            Vec3 victimPos = hitEntity.position();

            shooter.teleportTo(victimPos.x, victimPos.y+1, victimPos.z);
            hitEntity.teleportTo(shooterPos.x, shooterPos.y+1, shooterPos.z);

            shooter.fallDistance = 0.0f;

            return true;
        }

        return false;
    }
}

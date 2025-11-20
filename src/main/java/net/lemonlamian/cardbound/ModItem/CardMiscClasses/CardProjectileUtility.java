package net.lemonlamian.cardbound.ModItem.CardMiscClasses;

import net.lemonlamian.cardbound.ModItem.Registry.ModEntities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CardProjectileUtility {
    public static void shootCardProjectile(Level level, ServerPlayer player, ItemStack stack, float inaccuracy) {
        EntityType<CardProjectile> type = ModEntities.CARD_PROJECTILE.get();
        CardProjectile proj = new CardProjectile(type, player, level, stack);

        proj.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.6f, inaccuracy);

        level.addFreshEntity(proj);
    }
}

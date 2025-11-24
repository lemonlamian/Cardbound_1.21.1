package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardEntities.NetherLegionSoldier;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.lemonlamian.cardbound.ModItem.Registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NetherLegionCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.nether_legion_card.line1", "tooltip.cardbound.nether_legion_card.line2", "tooltip.cardbound.nether_legion_card.line3"};
    public NetherLegionCard(Item.Properties properties) {
        super(properties.stacksTo(1), 1200, CardCategory.ATTACK, CardRarity.RARE, tooltipLineArray);
    }


    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level.isClientSide()) return true;

        if (user instanceof Player player && player instanceof ServerPlayer serverPlayer && level instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 5; i++) {
                NetherLegionSoldier soldier = ModEntities.NETHER_LEGION_SOLDIER.get().create(serverLevel);
                soldier.moveTo(serverPlayer.position());
                level.addFreshEntity(soldier);
            }

            return true;
        }

        return false;
    }

}
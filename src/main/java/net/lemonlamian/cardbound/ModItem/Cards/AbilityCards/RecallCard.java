package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;


public class RecallCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.recall_card.line1", "tooltip.cardbound.recall_card.line2", "tooltip.cardbound.recall_card.line3"};
    public RecallCard(Item.Properties properties) {
        super(properties.stacksTo(1), 6000, CardCategory.UTILITY, CardRarity.RARE, tooltipLineArray);
    }

    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (user instanceof ServerPlayer serverPlayer && !level.isClientSide()) {
            MinecraftServer server = serverPlayer.getServer();
            ServerLevel respawnLevel = server.overworld();

            DimensionTransition spawnPointTransition = serverPlayer.findRespawnPositionAndUseSpawnBlock(true, DimensionTransition.DO_NOTHING);
            DimensionTransition transition = new DimensionTransition(respawnLevel, spawnPointTransition.pos(), Vec3.ZERO, 0f, 0f, false, DimensionTransition.DO_NOTHING);

            serverPlayer.changeDimension(transition);
            return true;
        }

        return false;
    }

}

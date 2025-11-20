package net.lemonlamian.cardbound.ModItem.Cards.AbilityCards;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.lemonlamian.cardbound.ModItem.CardMiscClasses.CardProjectileUtility;
import net.lemonlamian.cardbound.ModItem.Enums.CardCategory;
import net.lemonlamian.cardbound.ModItem.Enums.CardRarity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TunnelBoreCard extends AbilityCardItem {
    private static final String[] tooltipLineArray = {"tooltip.cardbound.tunnel_bore_card.line1", "tooltip.cardbound.tunnel_bore_card.line2", "tooltip.cardbound.tunnel_bore_card.line3"};
    public TunnelBoreCard(Item.Properties properties) {
        super(properties, 600, CardCategory.UTILITY, CardRarity.EPIC, tooltipLineArray);
    }


    @Override
    protected boolean onActivate(Level level, LivingEntity user, ItemStack stack) {
        if (level.isClientSide()) return true;

        if (user instanceof Player player && player instanceof ServerPlayer serverPlayer) {
            CardProjectileUtility.shootCardProjectile(level, serverPlayer, stack, 0);

            return true;
        }

        return false;
    }


    @Override
    public boolean onActivateOnBlockHit(Level level, LivingEntity shooter, BlockPos hitBlockPos, Vec3 hitPos, Vec3 dir, ItemStack stack) {
        int radius = 5;
        int length = 20;

        for (int i = 0; i < length; i++) {
            Vec3 center = hitPos.add(dir.scale(i));

            int cx = (int) center.x;
            int cy = (int) center.y;
            int cz = (int) center.z;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {

                        if (x*x + z*z <= radius * radius) {
                            BlockPos pos = new BlockPos(cx + x, cy + y, cz + z);

                            BlockState blockState = level.getBlockState(pos);
                            if (!blockState.is(Blocks.BEDROCK)) {
                                level.destroyBlock(pos, false);
                            }
                        }

                    }
                }
            }
        }




        return false;
    }
}

package net.lemonlamian.cardbound.ModItem.CardMiscClasses;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class UtiltyMethods {
    public static Holder<Enchantment> getEnchant(Level level, ResourceKey<Enchantment> key) {
        return level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(key);
    }


    public static List<? extends LivingEntity> getEntityInRadius(ServerLevel level, Vec3 center, double radius, @Nullable Class<? extends LivingEntity> entityClass) {
        AABB box = new AABB(center.x - radius, center.y - radius, center.z - radius, center.x + radius, center.y + radius, center.z + radius);

        if (entityClass == null) {
            List<? extends LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, box, entity -> entity.distanceToSqr(center.x, center.y, center.z) <= radius * radius);
            return entities;
        } else {
            List<? extends LivingEntity> entities = level.getEntitiesOfClass(entityClass, box, entity -> entity.distanceToSqr(center.x, center.y, center.z) <= radius * radius);
            return entities;
        }
    }


    public static List<BlockPos> getBlocksInRadius(ServerLevel level, Vec3 center, double radius, Block filterBlock) {
        List<BlockPos> blockPosList = List.of();

        int r = Mth.floor(radius);
        int cx = Mth.floor(center.x);
        int cy = Mth.floor(center.y);
        int cz = Mth.floor(center.z);
        
        for (int dx = -r; dx <= r; dx++) {
            for (int dy = -r; dy <= r; dy++) {
                for (int dz = -r; dz <= r; dz++) {

                    BlockPos pos = new BlockPos(cx + dx, cy + dy, cz + dz);
                    double distSq = (center.x - (cx + dx)) * (center.x - (cx + dx)) + (center.y - (cy + dy)) * (center.y - (cy + dy)) + (center.z - (cz + dz)) * (center.z - (cz + dz));

                    if (distSq <= radius * radius) {
                        BlockState state = level.getBlockState(pos);

                        if (state.is(filterBlock)) {
                            blockPosList.add(pos);
                        }
                    }


                }
            }
        }

        return blockPosList;
    }
}
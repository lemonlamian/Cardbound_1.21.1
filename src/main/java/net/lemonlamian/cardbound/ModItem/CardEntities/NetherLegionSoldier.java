package net.lemonlamian.cardbound.ModItem.CardEntities;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class NetherLegionSoldier extends WitherSkeleton {
    public NetherLegionSoldier(EntityType<? extends WitherSkeleton> entityType, Level level) {
        super(entityType, level);
        this.targetSelector.removeAllGoals(goal -> goal instanceof NearestAttackableTargetGoal<?> || goal instanceof HurtByTargetGoal);
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true));
        this.goalSelector.removeAllGoals(goal -> goal instanceof FleeSunGoal || goal instanceof RestrictSunGoal);

        SetCustomEquipments(this);
    }


    @Override
    public boolean isAlliedTo(Entity other) {
        if (other instanceof Player || other instanceof AbstractVillager || other instanceof AbstractGolem || other instanceof NeutralMob || other instanceof NetherLegionSoldier) {
            return true;
        }

        return super.isAlliedTo(other);
    }

    private void SetCustomEquipments(LivingEntity entity) {
        if (entity.level().isClientSide()) return;
        entity.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.NETHERITE_HELMET));
        entity.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
        entity.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
        entity.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
        entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.GOLDEN_SWORD));

        if (entity instanceof NetherLegionSoldier soldier) {
            soldier.setDropChance(EquipmentSlot.HEAD, 0);
            soldier.setDropChance(EquipmentSlot.HEAD, 0);
            soldier.setDropChance(EquipmentSlot.HEAD, 0);
            soldier.setDropChance(EquipmentSlot.HEAD, 0);
            soldier.setDropChance(EquipmentSlot.MAINHAND, 0);
        }
    }
}

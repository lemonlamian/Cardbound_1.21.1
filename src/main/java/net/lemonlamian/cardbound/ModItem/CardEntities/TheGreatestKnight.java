package net.lemonlamian.cardbound.ModItem.CardEntities;

import net.lemonlamian.cardbound.ModItem.CardMiscClasses.UtiltyMethods;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class TheGreatestKnight extends WitherSkeleton {
    private int startTick;
    private int elapsedTick;
    private int deathTick = 600;
    public TheGreatestKnight(EntityType<? extends WitherSkeleton> entityType, Level level) {
        super(entityType, level);
        this.targetSelector.removeAllGoals(goal -> goal instanceof NearestAttackableTargetGoal<?> || goal instanceof HurtByTargetGoal);
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true));
        this.goalSelector.removeAllGoals(goal -> goal instanceof FleeSunGoal || goal instanceof RestrictSunGoal);

        SetCustomEquipments(this);
    }

    @Override
    public boolean isAlliedTo(Entity other) {
        if (other instanceof Player || other instanceof AbstractVillager || other instanceof AbstractGolem || other instanceof NeutralMob || other instanceof TheGreatestKnight) {
            return true;
        }

        return super.isAlliedTo(other);
    }

    @Override
    public void tick() {
        super.tick();
        if (startTick == 0) {startTick = this.tickCount;}

        elapsedTick = this.tickCount - startTick;

        if (elapsedTick == deathTick) {this.discard();}
    }

    private void SetCustomEquipments(LivingEntity entity) {
        if (!(entity.level() instanceof ServerLevel level)) return;

        ItemStack helmet = new ItemStack(Items.NETHERITE_HELMET);
        helmet.enchant(UtiltyMethods.getEnchant(level, Enchantments.PROTECTION), 10);

        ItemStack chestplate = new ItemStack(Items.NETHERITE_CHESTPLATE);
        chestplate.enchant(UtiltyMethods.getEnchant(level, Enchantments.PROTECTION), 10);
        chestplate.enchant(UtiltyMethods.getEnchant(level, Enchantments.THORNS), 10);

        ItemStack leggings = new ItemStack(Items.NETHERITE_LEGGINGS);
        leggings.enchant(UtiltyMethods.getEnchant(level, Enchantments.PROTECTION), 10);

        ItemStack boots = new ItemStack(Items.NETHERITE_BOOTS);
        boots.enchant(UtiltyMethods.getEnchant(level, Enchantments.PROTECTION), 10);

        ItemStack sword = new ItemStack(Items.NETHERITE_SWORD);
        sword.enchant(UtiltyMethods.getEnchant(level, Enchantments.SHARPNESS), 40);
        sword.enchant(UtiltyMethods.getEnchant(level, Enchantments.FIRE_ASPECT), 40);


        entity.setItemSlot(EquipmentSlot.HEAD, helmet);
        entity.setItemSlot(EquipmentSlot.CHEST, chestplate);
        entity.setItemSlot(EquipmentSlot.LEGS, leggings);
        entity.setItemSlot(EquipmentSlot.FEET, boots);
        entity.setItemInHand(InteractionHand.MAIN_HAND, sword);

        if (entity instanceof TheGreatestKnight knight) {
            knight.setDropChance(EquipmentSlot.HEAD, 0);
            knight.setDropChance(EquipmentSlot.HEAD, 0);
            knight.setDropChance(EquipmentSlot.HEAD, 0);
            knight.setDropChance(EquipmentSlot.HEAD, 0);
            knight.setDropChance(EquipmentSlot.MAINHAND, 0);
        }
    }
}

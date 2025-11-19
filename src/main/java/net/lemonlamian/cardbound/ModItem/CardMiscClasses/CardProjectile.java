package net.lemonlamian.cardbound.ModItem.CardMiscClasses;

import net.lemonlamian.cardbound.ModItem.CardClasses.AbilityCardItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class CardProjectile extends ThrowableItemProjectile {
    private static final EntityDataAccessor<ItemStack> DATA_STACK = SynchedEntityData.defineId(CardProjectile.class, EntityDataSerializers.ITEM_STACK);

    private ItemStack cardStack = ItemStack.EMPTY;

    public CardProjectile(EntityType<? extends CardProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public CardProjectile(EntityType<? extends CardProjectile> entityType, LivingEntity shooter, Level level, ItemStack stack) {
        super(entityType, shooter, level);
        this.cardStack = stack.copy();
        this.setOwner(shooter);
        this.setNoGravity(true);
        this.entityData.set(DATA_STACK, this.cardStack);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_STACK, new ItemStack(Items.STONE));
    }

    @Override
    protected Item getDefaultItem() {
        return this.cardStack != null ? this.cardStack.getItem() : Items.AIR;
    }

    @Override
    public ItemStack getItem() {
        return this.entityData.get(DATA_STACK);
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level().isClientSide()) return;

        Entity hit = result.getEntity();
        LivingEntity shooter = (getOwner() instanceof LivingEntity livingShooter) ? livingShooter : null;

        if (shooter != null && this.cardStack != null && this.cardStack.getItem() instanceof AbilityCardItem cardItem) {
            cardItem.onActivateOnEntityHit(level(), shooter, hit, this.cardStack);
        }

        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (!this.level().isClientSide()) {
            LivingEntity shooter = (getOwner() instanceof LivingEntity livingShooter) ? livingShooter : null;
            BlockPos hitBlockPos = result.getBlockPos();
            Vec3 hitPos = result.getLocation();
            Vec3 dir = this.getDeltaMovement().normalize();

            if (shooter != null && this.cardStack != null && this.cardStack.getItem() instanceof AbilityCardItem cardItem) {
                cardItem.onActivateOnBlockHit(level(), shooter, hitBlockPos, hitPos, dir, this.cardStack);
            }

            this.discard();
        }
    }

}

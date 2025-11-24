package net.lemonlamian.cardbound.ModItem.Registry;

import com.mojang.serialization.Codec;
import net.lemonlamian.cardbound.Cardbound;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Cardbound.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> IS_ACTIVATED = COMPONENTS.register("is_activated", () ->
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Long>> START_TICK = COMPONENTS.register("start_tick", () ->
            DataComponentType.<Long>builder().persistent(Codec.LONG).build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Long>> ELAPSED_TICK = COMPONENTS.register("elapsed_tick", () ->
            DataComponentType.<Long>builder().persistent(Codec.LONG).build());
}

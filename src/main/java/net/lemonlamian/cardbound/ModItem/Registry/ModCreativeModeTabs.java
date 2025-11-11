package net.lemonlamian.cardbound.ModItem.Registry;

import net.lemonlamian.cardbound.Cardbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cardbound.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CARDBOUND_TAB = CREATIVE_MODE_TABS.register("cardbound_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.cardbound")) //The language key for the title of your CreativeModeTab
            .icon(() -> ModItems.EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {

                output.accept(ModItems.EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(ModItems.AWW_MAN_CARD.get());
                output.accept(ModItems.I_YEARNED_FOR_THE_MINES_CARD.get());
                output.accept(ModItems.ENRAGE_CARD.get());

            }).build());
}

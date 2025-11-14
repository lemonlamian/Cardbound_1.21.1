package net.lemonlamian.cardbound.DataGen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture("aww_man_card", mcLoc("item/generated"), "layer0", modLoc("item/rare_attack"));
        singleTexture("i_yearned_for_the_mines_card", mcLoc("item/generated"), "layer0", modLoc("item/epic_passive"));
    }
}

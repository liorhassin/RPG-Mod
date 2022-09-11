package net.LiorNadav.rpgmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab RPG_MOD_WEAPONS = new CreativeModeTab("rpgmod_weapons_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BEGINNER_BROADSWORD.get());
        }
    };
    public static final CreativeModeTab RPG_MOD_ITEMS = new CreativeModeTab("rpgmod_items_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ADAMANTITE_INGOT.get());
        }
    };
}

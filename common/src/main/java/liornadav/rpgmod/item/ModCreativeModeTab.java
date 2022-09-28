package liornadav.rpgmod.item;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeTabRegistry RPG_MOD_WEAPONS = new CreativeTabRegistry("rpgmod_weapons_tab") {
        @Override
        public ItemGroup makeIcon() {
            return new ItemStack(ModItems.BEGINNER_BROADSWORD.get());
        }
    };
    public static final CreativeTabRegistry RPG_MOD_ITEMS = new CreativeTabRegistry("rpgmod_items_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ADAMANTITE_INGOT.get());
        }
    };
}

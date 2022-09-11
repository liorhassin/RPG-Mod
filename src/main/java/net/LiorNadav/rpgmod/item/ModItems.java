package net.LiorNadav.rpgmod.item;

import net.LiorNadav.rpgmod.RPGMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RPGMod.MOD_ID);


    //-------------- Ingots ---------------
    public static final RegistryObject<Item> AZURITE_INGOT = ITEMS.register("azurite_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ADAMANTITE_INGOT = ITEMS.register("adamantite_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> JADEITE_INGOT = ITEMS.register("jadeite_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    //----------- Weapons -------------
    public static final RegistryObject<Item> STARTER_DAGGER = ITEMS.register("starter_dagger",
            () -> new SwordItem(Tiers.STONE, 3, -2f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BEGINNER_BROADSWORD = ITEMS.register("beginner_broadsword",
            () -> new SwordItem(Tiers.STONE, 3, -2f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BEGINNER_BATTLE_AXE = ITEMS.register("beginner_battle_axe",
            () -> new SwordItem(Tiers.STONE, 5, -3f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
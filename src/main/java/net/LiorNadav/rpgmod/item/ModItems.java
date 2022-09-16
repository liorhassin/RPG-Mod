package net.LiorNadav.rpgmod.item;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.item.custom.weapons.BeginnerBattleAxeItem;
import net.LiorNadav.rpgmod.item.custom.weapons.BeginnerBroadSwordItem;
import net.LiorNadav.rpgmod.item.custom.WarriorStarterKey;
import net.LiorNadav.rpgmod.item.custom.weapons.archer.TorchBow;
import net.LiorNadav.rpgmod.item.custom.weapons.archer.ammunition.TorchArrowItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RPGMod.MOD_ID);
    //-------------- Items ---------------



    //-------------- Ingots ---------------
    public static final RegistryObject<Item> AZURITE_INGOT = ITEMS.register("azurite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistryObject<Item> ADAMANTITE_INGOT = ITEMS.register("adamantite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistryObject<Item> JADEITE_INGOT = ITEMS.register("jadeite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));

    //-------------- Dusts ---------------
    public static final RegistryObject<Item> AZURITE_DUST = ITEMS.register("azurite_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistryObject<Item> ADAMANTITE_DUST = ITEMS.register("adamantite_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistryObject<Item> JADEITE_DUST = ITEMS.register("jadeite_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistryObject<Item> MIX_DUST = ITEMS.register("mix_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));


    //----------- Weapons -------------
    public static final RegistryObject<Item> STARTER_KNIFE = ITEMS.register("starter_knife",
            () -> new SwordItem(Tiers.STONE, 3, -2f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(100)));

    public static final RegistryObject<Item> BEGINNER_BROADSWORD = ITEMS.register("beginner_broadsword",
            () -> new BeginnerBroadSwordItem(Tiers.STONE, 3, -2f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistryObject<Item> BEGINNER_BATTLE_AXE = ITEMS.register("beginner_battle_axe",
            () -> new BeginnerBattleAxeItem(Tiers.STONE, 5, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistryObject<Item> STARTER_SLINGSHOT = ITEMS.register("starter_slingshot",
            () -> new BowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(100)));

    public static final RegistryObject<Item> BEGINNER_BOW = ITEMS.register("beginner_bow",
            () -> new BowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistryObject<Item> BEGINNER_CROSSBOW = ITEMS.register("beginner_crossbow",
            () -> new CrossbowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistryObject<Item> TORCH_BOW = ITEMS.register("torch_bow",
            () -> new TorchBow(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    //-------------------- Ammunition -------------------

    public static final RegistryObject<Item> TORCH_ARROW = ITEMS.register("torch_arrow",
            ()-> new TorchArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 1.5F));
    //----------- Keys -------------
    public static final RegistryObject<Item> WARRIOR_STARTER_KEY = ITEMS.register("warrior_starter_key", WarriorStarterKey::new);

    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
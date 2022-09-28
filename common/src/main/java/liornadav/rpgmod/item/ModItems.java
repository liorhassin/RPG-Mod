package liornadav.rpgmod.item;

import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import liornadav.rpgmod.RPGMod;
import liornadav.rpgmod.item.custom.ArcherStarterKey;
import liornadav.rpgmod.item.custom.MageStarterKey;
import liornadav.rpgmod.item.custom.Tablet;
import liornadav.rpgmod.item.custom.WarriorStarterKey;
import liornadav.rpgmod.item.custom.weapons.archer.*;
import liornadav.rpgmod.item.custom.weapons.archer.ammunition.*;
import liornadav.rpgmod.item.custom.weapons.mage.*;
import liornadav.rpgmod.item.custom.weapons.warrior.*;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.item.custom.ArcherStarterKey;
import net.LiorNadav.rpgmod.item.custom.Tablet;
import net.LiorNadav.rpgmod.item.custom.MageStarterKey;
import net.LiorNadav.rpgmod.item.custom.weapons.archer.*;
import net.LiorNadav.rpgmod.item.custom.weapons.archer.ammunition.*;
import net.LiorNadav.rpgmod.item.custom.weapons.mage.*;
import net.LiorNadav.rpgmod.item.custom.weapons.warrior.*;
import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.fluid.ModFluids;
import net.LiorNadav.rpgmod.item.custom.WarriorStarterKey;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(RPGMod.MOD_ID, Registry.ITEM_KEY);
    //-------------- Items ---------------//



    //-------------- Ingots ---------------//
    public static final RegistrySupplier<Item> AZURITE_INGOT = ITEMS.register("azurite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> ADAMANTITE_INGOT = ITEMS.register("adamantite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> JADEITE_INGOT = ITEMS.register("jadeite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> YELLOW_GEM = ITEMS.register("topaz",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));

    //-------------- Dusts ---------------//
    public static final RegistrySupplier<Item> AZURITE_DUST = ITEMS.register("azurite_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> ADAMANTITE_DUST = ITEMS.register("adamantite_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> JADEITE_DUST = ITEMS.register("jadeite_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> MIX_DUST = ITEMS.register("mix_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));

    //----------------------------- Hearts --------------------------------//

    public static final RegistrySupplier<Item> RED_OGRE_HEART = ITEMS.register("red_ogre_heart",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> PURIFIED_RED_OGRE_HEART = ITEMS.register("purified_red_ogre_heart",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));

    public static final RegistrySupplier<Item> GREEN_OGRE_HEART = ITEMS.register("green_ogre_heart",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> PURIFIED_GREEN_OGRE_HEART = ITEMS.register("purified_green_ogre_heart",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));

    public static final RegistrySupplier<Item> BLUE_OGRE_HEART = ITEMS.register("blue_ogre_heart",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> PURIFIED_BLUE_OGRE_HEART = ITEMS.register("purified_blue_ogre_heart",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));

    public static final RegistrySupplier<Item> RED_OGRE_SPAWN_EGG = ITEMS.register("red_ogre_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntityTypes.RED_OGRE, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> GREEN_OGRE_SPAWN_EGG = ITEMS.register("green_ogre_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntityTypes.GREEN_OGRE, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> BLUE_OGRE_SPAWN_EGG = ITEMS.register("blue_ogre_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntityTypes.BLUE_OGRE, 0x22b341, 0x19732e,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> ZOMBIE_RPG_SPAWN_EGG = ITEMS.register("zombie_rpg_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntityTypes.ZOMBIE_RPG, 0x22b341, 0x19732e,

                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS)));
    public static final RegistrySupplier<Item> TABLET = ITEMS.register("tablet",
            () -> new Tablet(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS).stacksTo(1)));

    //----------------------------- Weapons -------------------------------//
    public static final RegistrySupplier<Item> STARTER_KNIFE = ITEMS.register("starter_knife",
            () -> new SwordItem(Tiers.STONE, 3, -2f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(100)));

    public static final RegistrySupplier<Item> STARTER_SLINGSHOT = ITEMS.register("starter_slingshot",
            () -> new BowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(100)));

    public static final RegistrySupplier<Item> STARTER_WAND = ITEMS.register("starter_wand",
            () -> new StarterWandItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistrySupplier<Item> BEGINNER_BROADSWORD = ITEMS.register("beginner_broadsword",
            () -> new BeginnerBroadSwordItem(Tiers.STONE, 5, -2f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistrySupplier<Item> ADVANCED_BROADSWORD = ITEMS.register("advanced_broadsword",
            () -> new AdvancedBroadSwordItem(Tiers.STONE, 7, -2f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(400)));

    public static final RegistrySupplier<Item> SUPERIOR_BROADSWORD = ITEMS.register("superior_broadsword",
            () -> new SuperiorBroadSwordItem(Tiers.STONE, 9, -2f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(800)));

    public static final RegistrySupplier<Item> BEGINNER_BATTLE_AXE = ITEMS.register("beginner_battle_axe",
            () -> new BeginnerBattleAxeItem(Tiers.STONE, 6, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistrySupplier<Item> ADVANCED_BATTLE_AXE = ITEMS.register("advanced_battle_axe",
            () -> new AdvancedBattleAxeItem(Tiers.STONE, 8, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(400)));

    public static final RegistrySupplier<Item> SUPERIOR_BATTLE_AXE = ITEMS.register("superior_battle_axe",
            () -> new SuperiorBattleAxeItem(Tiers.STONE, 11, -3f,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(800)));

    public static final RegistrySupplier<Item> BEGINNER_STAFF = ITEMS.register("beginner_staff",
            () -> new BeginnerStaffItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistrySupplier<Item> ADVANCED_STAFF = ITEMS.register("advanced_staff",
            () -> new AdvancedStaffItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(400)));

    public static final RegistrySupplier<Item> SUPERIOR_STAFF = ITEMS.register("superior_staff",
            () -> new SuperiorStaffItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(800)));

    public static final RegistrySupplier<Item> BEGINNER_SPELLBOOK = ITEMS.register("beginner_spellbook",
            () -> new BeginnerSpellbookItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistrySupplier<Item> ADVANCED_SPELLBOOK = ITEMS.register("advanced_spellbook",
            () -> new AdvancedSpellbookItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(400)));

    public static final RegistrySupplier<Item> SUPERIOR_SPELLBOOK = ITEMS.register("superior_spellbook",
            () -> new SuperiorSpellbookItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(800)));

    public static final RegistrySupplier<Item> BEGINNER_BOW = ITEMS.register("beginner_bow",
            () -> new BeginnerBowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistrySupplier<Item> ADVANCED_BOW = ITEMS.register("advanced_bow",
            () -> new AdvancedBowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(400)));

    public static final RegistrySupplier<Item> SUPERIOR_BOW = ITEMS.register("superior_bow",
            () -> new SuperiorBowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(800)));

    public static final RegistrySupplier<Item> BEGINNER_CROSSBOW = ITEMS.register("beginner_crossbow",
            () -> new BeginnerCrossbowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(200)));

    public static final RegistrySupplier<Item> ADVANCED_CROSSBOW = ITEMS.register("advanced_crossbow",
            () -> new AdvancedCrossbowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(400)));

    public static final RegistrySupplier<Item> SUPERIOR_CROSSBOW = ITEMS.register("superior_crossbow",
            () -> new SuperiorCrossbowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_WEAPONS).durability(800)));

    //-------------------- Ammunition -------------------//

    public static final RegistrySupplier<Item> TORCH_ARROW = ITEMS.register("torch_arrow",
            ()-> new TorchArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 2f));

    public static final RegistrySupplier<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow",
            ()-> new ExplosiveArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 2f));

    public static final RegistrySupplier<Item> FROST_ARROW = ITEMS.register("frost_arrow",
            ()-> new FrostArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 5f));

    public static final RegistrySupplier<Item> IRON_ARROW = ITEMS.register("iron_arrow",
            ()-> new IronArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 5f));

    public static final RegistrySupplier<Item> GOLD_ARROW = ITEMS.register("gold_arrow",
            ()-> new GoldArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 7f));

    public static final RegistrySupplier<Item> DIAMOND_ARROW = ITEMS.register("diamond_arrow",
            ()-> new DiamondArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 10f));

    public static final RegistrySupplier<Item> EMERALD_ARROW = ITEMS.register("emerald_arrow",
            ()-> new EmeraldArrowItem(new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS), 13f));

    //----------- Keys -------------
    public static final RegistrySupplier<Item> WARRIOR_STARTER_KEY = ITEMS.register("warrior_starter_key", WarriorStarterKey::new);

    public static final RegistrySupplier<Item> ARCHER_STARTER_KEY = ITEMS.register("archer_starter_key", ArcherStarterKey::new);

    public static final RegistrySupplier<Item> MAGE_STARTER_KEY = ITEMS.register("mage_starter_key", MageStarterKey::new);

    public static final RegistrySupplier<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER,
                    new Item.Properties().tab(ModCreativeModeTab.RPG_MOD_ITEMS).craftRemainder(Items.BUCKET).stacksTo(1)));

}
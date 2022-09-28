package liornadav.rpgmod.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import liornadav.rpgmod.RPGMod;
import liornadav.rpgmod.block.custom.PurifierBlock;
import liornadav.rpgmod.block.custom.Spawner;
import liornadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.block.custom.KJPortalBlock;
import net.LiorNadav.rpgmod.block.custom.PurifierBlock;
import net.LiorNadav.rpgmod.block.custom.Spawner;
import net.LiorNadav.rpgmod.fluid.ModFluids;
import net.LiorNadav.rpgmod.item.ModCreativeModeTab;
import net.LiorNadav.rpgmod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(RPGMod.MOD_ID, Registry.BLOCK_KEY);
    // ---------------------- Ores & Gems --------------------------
    public static final RegistrySupplier<Block> AZURITE_ORE = registerBlock("azurite_ore",
            ()-> new Block(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistrySupplier<Block> ADAMANTITE_ORE = registerBlock("adamantite_ore",
            ()-> new Block(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistrySupplier<Block> JADEITE_ORE = registerBlock("jadeite_ore",
            ()-> new Block(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistrySupplier<Block> TOPAZ_ORE = registerBlock("topaz_ore",
            ()-> new Block(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistrySupplier<Block> TOPAZ_BLOCK = registerBlock("topaz_block",
            ()-> new Block(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool()), ModCreativeModeTab.RPG_MOD_ITEMS);

    // ------------------- Special functionality blocks ----------------------
    public static final RegistrySupplier<Block> PORTAL_BLOCK = registerBlock("portal_block",
            ()-> new Block(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistrySupplier<Block> SPAWNER = registerBlock("spawner",
            ()-> new Spawner(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistrySupplier<Block> KAUPEN_PORTAL = registerBlockWithoutBlockItem("kaupen_portal",
            KJPortalBlock::new);

    public static final RegistrySupplier<Block> PURIFIER = registerBlock("purifier",
            ()-> new PurifierBlock(Block.Settings.of(Material.METAL)
                    .strength(4f).requiresTool().noCollision()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistrySupplier<Block> VILLAGER_BLOCK = registerBlock("villager_block",
            ()-> new Block(Block.Settings.of(Material.STONE)
                    .strength(4f).requiresTool().noCollision()), ModCreativeModeTab.RPG_MOD_ITEMS);

    // -------------------- Other mod blocks ----------------------------

    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, Block.Settings.copy((Blocks.WATER))));





    // -------------------------- Registry methods ------------------------
    private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistrySupplier<Item> registerBlockItem(String name, RegistrySupplier<T> block,
                                                                              CreativeModeTab tab){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistrySupplier<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
}

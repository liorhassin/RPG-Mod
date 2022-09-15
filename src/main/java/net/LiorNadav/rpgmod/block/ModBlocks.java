package net.LiorNadav.rpgmod.block;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.block.custom.KJPortalBlock;
import net.LiorNadav.rpgmod.block.custom.PurifierBlock;
import net.LiorNadav.rpgmod.fluid.ModFluids;
import net.LiorNadav.rpgmod.item.ModCreativeModeTab;
import net.LiorNadav.rpgmod.item.ModItems;
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
            DeferredRegister.create(ForgeRegistries.BLOCKS, RPGMod.MOD_ID);

    public static final RegistryObject<Block> AZURITE_ORE = registerBlock("azurite_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistryObject<Block> ADAMANTITE_ORE = registerBlock("adamantite_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistryObject<Block> JADEITE_ORE = registerBlock("jadeite_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistryObject<Block> PORTAL_BLOCK = registerBlock("portal_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistryObject<Block> KAUPEN_PORTAL = registerBlockWithoutBlockItem("kaupen_portal",
            KJPortalBlock::new);

    public static final RegistryObject<Block> PURIFIER = registerBlock("purifier",
            ()-> new PurifierBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(4f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.RPG_MOD_ITEMS);

    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, BlockBehaviour.Properties.copy((Blocks.WATER))));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
}

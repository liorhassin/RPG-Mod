package net.LiorNadav.rpgmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.entity.custom.RedOgreEntity;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.networking.packet.*;
import net.LiorNadav.rpgmod.villager.ModVillagers;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBow;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBowProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshot;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshotProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerMana;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerManaProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.wand.PlayerWand;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.wand.PlayerWandProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxe;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxeProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadsword;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnife;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


public class ModEvents {

    @Mod.EventBusSubscriber(modid = RPGMod.MOD_ID)
    public static class ForgeEvents {
        //------------------------------------------Trades----------------------------------------------
        @SubscribeEvent
        public static void addCustomTrades(VillagerTradesEvent event) {
            if (event.getType() == ModVillagers.DARK_MAGE.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.AZURITE_DUST.get(), 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(ModItems.AZURITE_INGOT.get(), 1),
                        stack, 10, 8, 0.02F));
            }

            if (event.getType() == ModVillagers.DARK_MAGE.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.ADAMANTITE_DUST.get(), 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(ModItems.ADAMANTITE_INGOT.get(), 1),
                        stack, 10, 8, 0.02F));
            }

            if (event.getType() == ModVillagers.DARK_MAGE.get()) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(ModItems.JADEITE_DUST.get(), 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(ModItems.JADEITE_INGOT.get(), 1),
                        stack, 10, 8, 0.02F));
            }
        }


        //------------------------------------------Knife Events----------------------------------------------
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {

            //------------------Player weapon system------------------//
            if (!event.getObject().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_knife_levels"), new PlayerKnifeProvider());
            }
            if (!event.getObject().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_slingshot_levels"), new PlayerSlingshotProvider());
            }
            if (!event.getObject().getCapability(PlayerWandProvider.PLAYER_WAND).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_wand_levels"), new PlayerWandProvider());
            }

            if (!event.getObject().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_broadsword_levels"), new PlayerBroadswordProvider());
            }
            if (!event.getObject().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_battle_axe_levels"), new PlayerBattleAxeProvider());
            }
            if (!event.getObject().getCapability(PlayerBowProvider.PLAYER_BOW).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_bow_levels"), new PlayerBowProvider());
            }

            //------------------Player general capabilities------------------//
            if (!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_mana"), new PlayerManaProvider());
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(PlayerKnife.class);
            event.register(PlayerBroadsword.class);
            event.register(PlayerBattleAxe.class);
            event.register(PlayerSlingshot.class);
            event.register(PlayerBow.class);
            event.register(PlayerWand.class);
            event.register(PlayerMana.class);
        }

        //---------------------------------------General Events----------------------------------------------
        @SubscribeEvent
        public static void onEnemyHit(LivingHurtEvent event) {
            Entity player = event.getSource().getEntity();
            if (player instanceof Player) {
                String mainItemName = ((Player) player).getMainHandItem().getItem().toString();
                switch (mainItemName) {

                    //---------------------------Starter Tier-------------------------------//
                    case "starter_knife":
                        player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                            if(knifeExperience.getKnifeLevel() != 10) {
                                knifeExperience.addExperience((int) event.getAmount());
                                ModMessages.sendToServer(new KnifeLevelC2SPacket());
                            }
                        });
                        break;
                    case "starter_slingshot":
                        player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                            if (slingshotExperience.getSlingshotLevel() != 10) {
                                slingshotExperience.addExperience((int) event.getAmount());
                                ModMessages.sendToServer(new SlingshotLevelC2SPacket());
                            }
                        });
                        break;
                    case "starter_wand":
                        player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandExperience -> {
                            if (wandExperience.getWandLevel() != 10) {
                                wandExperience.addExperience((int) event.getAmount());
                                ModMessages.sendToServer(new WandLevelC2SPacket());
                            }
                        });
                        break;
                    //---------------------------Beginner Tier-------------------------------//
                    case "beginner_broadsword":
                        player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                            if (knifeExperience.getKnifeLevel() == 10) {
                                player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                                    if (broadswordExperience.getSwordLevel() >= 0 && broadswordExperience.getTier() == 0) {
                                        broadswordExperience.addExperience((int) event.getAmount());
                                        ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                                    }
                                });
                            }
                        });
                        break;
                    case "beginner_battle_axe":
                        player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                            if (knifeExperience.getKnifeLevel() == 10) {
                                player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                                    if (battleAxeExperience.getAxeLevel() >= 0 && battleAxeExperience.getTier() == 0) {
                                        battleAxeExperience.addExperience((int) event.getAmount());
                                        ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                                    }
                                });
                            }
                        });
                        break;
                    case "beginner_bow":
                        player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                            if (slingshotExperience.getSlingshotLevel() == 10) {
                                player.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowExperience -> {
                                    if (bowExperience.getBowLevel() >= 0 && bowExperience.getTier() == 0) {
                                        bowExperience.addExperience((int) event.getAmount());
                                        ModMessages.sendToServer(new BowLevelC2SPacket());
                                    }
                                });
                            }
                        });
                        break;

                    //---------------------------Advanced Tier-------------------------------//
                    case "advanced_broadsword":
                        player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                            if (broadswordExperience.getSwordLevel() >= 30 && broadswordExperience.getTier() == 1) {
                                broadswordExperience.addExperience((int) event.getAmount());
                                ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                            }
                        });
                        break;

                    case "advanced_battle_axe":
                        player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                            if (battleAxeExperience.getAxeLevel() >= 30 && battleAxeExperience.getTier() == 1) {
                                battleAxeExperience.addExperience((int) event.getAmount());
                                ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                            }
                        });
                        break;
                    //---------------------------Superior Tier-------------------------------//
                    case "superior_broadsword":
                        player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                            if (broadswordExperience.getSwordLevel() >= 60 && broadswordExperience.getTier() == 2) {
                                broadswordExperience.addExperience((int) event.getAmount());
                                ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                            }
                        });
                        break;
                    case "superior_battle_axe":
                        player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                            if (battleAxeExperience.getAxeLevel() >= 60 && battleAxeExperience.getTier() == 2) {
                                battleAxeExperience.addExperience((int) event.getAmount());
                                ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                            }
                        });
                        break;
                    //---------------------------Dev Tool-------------------------------//
                    case "apple":
                        player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                            knifeExperience.addExperience(10000);
                            ModMessages.sendToServer(new KnifeLevelC2SPacket());
                        });
                        player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                            slingshotExperience.addExperience(10000);
                            ModMessages.sendToServer(new SlingshotLevelC2SPacket());
                        });
                        player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                            broadswordExperience.addExperience(10000);
                            ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                        });
                        player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                            battleAxeExperience.addExperience(10000);
                            ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                        });
                        break;
                    default:
                        break;
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event){
            if (event.side == LogicalSide.SERVER) {
                event.player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    if (mana.getMana() < 100 && event.player.getRandom().nextFloat() < 0.05f){
                        mana.addMana(1);
                    }
                });
            }
        }
    }

    @Mod.EventBusSubscriber(modid = RPGMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.RED_OGRE.get(), RedOgreEntity.setAttributes());
        }
    }
}
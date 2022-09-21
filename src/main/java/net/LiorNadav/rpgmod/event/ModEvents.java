package net.LiorNadav.rpgmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.entity.custom.BlueOgreEntity;
import net.LiorNadav.rpgmod.entity.custom.GreenOgreEntity;
import net.LiorNadav.rpgmod.entity.custom.RedOgreEntity;
import net.LiorNadav.rpgmod.entity.custom.ZombieRPGEntity;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.networking.packet.*;
import net.LiorNadav.rpgmod.villager.ModVillagers;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBow;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBowProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.crossbow.PlayerCrossbow;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.crossbow.PlayerCrossbowProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshot;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshotProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerMana;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerManaProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.spellbook.PlayerSpellbook;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.spellbook.PlayerSpellbookProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.staff.PlayerStaff;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.staff.PlayerStaffProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.wand.PlayerWand;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.wand.PlayerWandProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxe;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxeProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadsword;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnife;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
                ItemStack firstTrade = new ItemStack(ModItems.AZURITE_DUST.get(), 1);
                ItemStack secondTrade = new ItemStack(ModItems.ADAMANTITE_DUST.get(), 1);
                ItemStack thirdTrade = new ItemStack(ModItems.JADEITE_DUST.get(), 1);

                trades.get(2).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(ModItems.AZURITE_INGOT.get(), 1),
                        firstTrade, 10, 8, 0.02F));

                trades.get(1).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(ModItems.ADAMANTITE_INGOT.get(), 1),
                        secondTrade, 10, 8, 0.02F));

                trades.get(1).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(ModItems.JADEITE_INGOT.get(), 1),
                        thirdTrade, 10, 8, 0.02F));
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
            if (!event.getObject().getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_crossbow_levels"), new PlayerCrossbowProvider());
            }
            if (!event.getObject().getCapability(PlayerStaffProvider.PLAYER_STAFF).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_staff_levels"), new PlayerStaffProvider());
            }
            if (!event.getObject().getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_spellbook_levels"), new PlayerSpellbookProvider());
            }

            //------------------Player general capabilities------------------//
            if (!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()) {
                event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_mana"), new PlayerManaProvider());
            }
        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (event.isWasDeath()) {
                event.getOriginal().reviveCaps();
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerStaffProvider.PLAYER_STAFF).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerStaffProvider.PLAYER_STAFF).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
                event.getOriginal().getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(oldStore -> {
                    event.getEntity().getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(newStore -> {
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
            event.register(PlayerCrossbow.class);

            event.register(PlayerWand.class);
            event.register(PlayerSpellbook.class);
            event.register(PlayerStaff.class);

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
                                if(knifeExperience.getKnifeExperience() + (int)event.getAmount() >= knifeExperience.getExperienceArray(knifeExperience.getKnifeLevel())){
                                    ModMessages.sendToServer(new KnifeLevelC2SPacket());
                                }
                                knifeExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "starter_slingshot":
                        player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                            if (slingshotExperience.getSlingshotLevel() != 10) {
                                if(slingshotExperience.getSlingshotExperience() + (int)event.getAmount() >= slingshotExperience.getExperienceArray(slingshotExperience.getSlingshotLevel())){
                                    ModMessages.sendToServer(new SlingshotLevelC2SPacket());
                                }
                                slingshotExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "starter_wand":
                        player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandExperience -> {
                            if (wandExperience.getWandLevel() != 10) {
                                if(wandExperience.getWandExperience() + (int)event.getAmount() >= wandExperience.getExperienceArray(wandExperience.getWandLevel())){
                                    ModMessages.sendToServer(new WandLevelC2SPacket());
                                }
                                wandExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    //---------------------------Beginner Tier-------------------------------//
                    case "beginner_broadsword":
                        player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                            if (knifeExperience.getKnifeLevel() == 10) {
                                player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                                    if (broadswordExperience.getSwordLevel() >= 0 && broadswordExperience.getTier() == 0) {
                                        if(broadswordExperience.getSwordExperience() + (int)event.getAmount() >= broadswordExperience.getExperienceArray(broadswordExperience.getSwordLevel())){
                                            ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                                        }
                                        broadswordExperience.addExperience((int) event.getAmount());
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
                                        if(battleAxeExperience.getAxeExperience() + (int)event.getAmount() >= battleAxeExperience.getExperienceArray(battleAxeExperience.getAxeLevel())){
                                            ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                                        }
                                        battleAxeExperience.addExperience((int) event.getAmount());
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
                                        if(bowExperience.getBowExperience() + (int)event.getAmount() >= bowExperience.getExperienceArray(bowExperience.getBowLevel())){
                                            ModMessages.sendToServer(new BowLevelC2SPacket());
                                        }
                                        bowExperience.addExperience((int) event.getAmount());
                                    }
                                });
                            }
                        });
                        break;
                    case "beginner_crossbow":
                        player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                            if (slingshotExperience.getSlingshotLevel() == 10) {
                                player.getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(crossbowExperience -> {
                                    if (crossbowExperience.getCrossbowLevel() >= 0 && crossbowExperience.getTier() == 0) {
                                        if(crossbowExperience.getCrossbowExperience() + (int)event.getAmount() >= crossbowExperience.getExperienceArray(crossbowExperience.getCrossbowLevel())){
                                            ModMessages.sendToServer(new CrossbowLevelC2SPacket());
                                        }
                                        crossbowExperience.addExperience((int) event.getAmount());
                                    }
                                });
                            }
                        });
                        break;

                    case "beginner_staff":
                        player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandExperience -> {
                            if (wandExperience.getWandLevel() == 10) {
                                player.getCapability(PlayerStaffProvider.PLAYER_STAFF).ifPresent(staffExperience -> {
                                    if (staffExperience.getStaffLevel() >= 0 && staffExperience.getTier() == 0) {
                                        if(staffExperience.getStaffExperience() + (int)event.getAmount() >= staffExperience.getExperienceArray(staffExperience.getStaffLevel())){
                                            ModMessages.sendToServer(new StaffLevelC2SPacket());
                                        }
                                        staffExperience.addExperience((int) event.getAmount());
                                    }
                                });
                            }
                        });
                        break;
                    case "beginner_spellbook":
                        player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandExperience -> {
                            if (wandExperience.getWandLevel() == 10) {
                                player.getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(spellbookExperience -> {
                                    if (spellbookExperience.getSpellbookLevel() >= 0 && spellbookExperience.getTier() == 0) {
                                        if(spellbookExperience.getSpellbookExperience() + (int)event.getAmount() >= spellbookExperience.getExperienceArray(spellbookExperience.getSpellbookLevel())){
                                            ModMessages.sendToServer(new SpellbookLevelC2SPacket());
                                        }
                                        spellbookExperience.addExperience((int) event.getAmount());
                                    }
                                });
                            }
                        });
                        break;

                    //---------------------------Advanced Tier-------------------------------//
                    case "advanced_broadsword":
                        player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                            if (broadswordExperience.getSwordLevel() >= 30 && broadswordExperience.getTier() == 1) {
                                if(broadswordExperience.getSwordExperience() + (int)event.getAmount() >= broadswordExperience.getExperienceArray(broadswordExperience.getSwordLevel())){
                                    ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                                }
                                broadswordExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "advanced_battle_axe":
                        player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                            if (battleAxeExperience.getAxeLevel() >= 30 && battleAxeExperience.getTier() == 1) {
                                if(battleAxeExperience.getAxeExperience() + (int)event.getAmount() >= battleAxeExperience.getExperienceArray(battleAxeExperience.getAxeLevel())){
                                    ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                                }
                                battleAxeExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;

                    case "advanced_bow":
                        player.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowExperience -> {
                            if (bowExperience.getBowLevel() >= 30 && bowExperience.getTier() == 1) {
                                if(bowExperience.getBowExperience() + (int)event.getAmount() >= bowExperience.getExperienceArray(bowExperience.getBowLevel())){
                                    ModMessages.sendToServer(new BowLevelC2SPacket());
                                }
                                bowExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "advanced_crossbow":
                        player.getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(crossbowExperience -> {
                            if (crossbowExperience.getCrossbowLevel() >= 30 && crossbowExperience.getTier() == 1) {
                                if(crossbowExperience.getCrossbowExperience() + (int)event.getAmount() >= crossbowExperience.getExperienceArray(crossbowExperience.getCrossbowLevel())){
                                    ModMessages.sendToServer(new BowLevelC2SPacket());
                                }
                                crossbowExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;

                    case "advanced_staff":
                        player.getCapability(PlayerStaffProvider.PLAYER_STAFF).ifPresent(staffExperience -> {
                            if (staffExperience.getStaffLevel() >= 30 && staffExperience.getTier() == 1) {
                                if(staffExperience.getStaffExperience() + (int)event.getAmount() >= staffExperience.getExperienceArray(staffExperience.getStaffLevel())){
                                    ModMessages.sendToServer(new StaffLevelC2SPacket());
                                }
                                staffExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "advanced_spellbook":
                        player.getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(spellbookExperience -> {
                            if (spellbookExperience.getSpellbookLevel() >= 30 && spellbookExperience.getTier() == 1) {
                                if(spellbookExperience.getSpellbookExperience() + (int)event.getAmount() >= spellbookExperience.getExperienceArray(spellbookExperience.getSpellbookLevel())){
                                    ModMessages.sendToServer(new SpellbookLevelC2SPacket());
                                }
                                spellbookExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    //---------------------------Superior Tier-------------------------------//
                    case "superior_broadsword":
                        player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                            if (broadswordExperience.getSwordLevel() >= 60 && broadswordExperience.getTier() == 2) {
                                if(broadswordExperience.getSwordExperience() + (int)event.getAmount() >= broadswordExperience.getExperienceArray(broadswordExperience.getSwordLevel())){
                                    ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                                }
                                broadswordExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "superior_battle_axe":
                        player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                            if (battleAxeExperience.getAxeLevel() >= 60 && battleAxeExperience.getTier() == 2) {
                                if(battleAxeExperience.getAxeExperience() + (int)event.getAmount() >= battleAxeExperience.getExperienceArray(battleAxeExperience.getAxeLevel())){
                                    ModMessages.sendToServer(new BattleAxeLevelC2SPacket());
                                }
                                battleAxeExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;

                    case "superior_bow":
                        player.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowExperience -> {
                            if (bowExperience.getBowLevel() >= 60 && bowExperience.getTier() == 2) {
                                if(bowExperience.getBowExperience() + (int)event.getAmount() >= bowExperience.getExperienceArray(bowExperience.getBowLevel())){
                                    ModMessages.sendToServer(new BowLevelC2SPacket());
                                }
                                bowExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "superior_crossbow":
                        player.getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(crossbowExperience -> {
                            if (crossbowExperience.getCrossbowLevel() >= 60 && crossbowExperience.getTier() == 2) {
                                if(crossbowExperience.getCrossbowExperience() + (int)event.getAmount() >= crossbowExperience.getExperienceArray(crossbowExperience.getCrossbowLevel())){
                                    ModMessages.sendToServer(new BowLevelC2SPacket());
                                }
                                crossbowExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;

                    case "superior_staff":
                        player.getCapability(PlayerStaffProvider.PLAYER_STAFF).ifPresent(staffExperience -> {
                            if (staffExperience.getStaffLevel() >= 60 && staffExperience.getTier() == 2) {
                                if(staffExperience.getStaffExperience() + (int)event.getAmount() >= staffExperience.getExperienceArray(staffExperience.getStaffLevel())){
                                    ModMessages.sendToServer(new StaffLevelC2SPacket());
                                }
                                staffExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    case "superior_spellbook":
                        player.getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(spellbookExperience -> {
                            if (spellbookExperience.getSpellbookLevel() >= 60 && spellbookExperience.getTier() == 2) {
                                if(spellbookExperience.getSpellbookExperience() + (int)event.getAmount() >= spellbookExperience.getExperienceArray(spellbookExperience.getSpellbookLevel())){
                                    ModMessages.sendToServer(new SpellbookLevelC2SPacket());
                                }
                                spellbookExperience.addExperience((int) event.getAmount());
                            }
                        });
                        break;
                    //---------------------------Dev Tool-------------------------------//
                    case "apple":
                        player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                            knifeExperience.addExperience(10000);
                        });
                        player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                            slingshotExperience.addExperience(10000);
                        });
                        player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandExperience -> {
                            wandExperience.addExperience(10000);
                            //
                        });

                        player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                            broadswordExperience.addExperience(10000);
                        });
                        player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeExperience -> {
                            battleAxeExperience.addExperience(10000);
                        });
                        player.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowExperience -> {
                            bowExperience.addExperience(10000);
                            //
                        });
                        player.getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(crossbowExperience -> {
                            crossbowExperience.addExperience(10000);
                            //
                        });
                        player.getCapability(PlayerStaffProvider.PLAYER_STAFF).ifPresent(staffExperience -> {
                            staffExperience.addExperience(10000);
                            //
                        });
                        player.getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(spellbookExperience -> {
                            spellbookExperience.addExperience(10000);
                            //
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
                        ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), (ServerPlayer) event.player);
                    }
                });
            }
        }

        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event){
            if(!event.getLevel().isClientSide()){
                if(event.getEntity() instanceof ServerPlayer player){
                    player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                        ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
                    });
                }
            }
        }
    }

    @Mod.EventBusSubscriber(modid = RPGMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents{
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event){
            event.put(ModEntityTypes.RED_OGRE.get(), RedOgreEntity.setAttributes());
            event.put(ModEntityTypes.GREEN_OGRE.get(), GreenOgreEntity.setAttributes());
            event.put(ModEntityTypes.BLUE_OGRE.get(), BlueOgreEntity.setAttributes());
            event.put(ModEntityTypes.ZOMBIE_RPG.get(), ZombieRPGEntity.setAttributes());
        }
    }
}
package net.LiorNadav.rpgmod.block.custom;

import net.LiorNadav.rpgmod.block.ModBlocks;
import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.entity.custom.BlueOgreEntity;
import net.LiorNadav.rpgmod.entity.custom.GreenOgreEntity;
import net.LiorNadav.rpgmod.entity.custom.RedOgreEntity;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBowProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.crossbow.PlayerCrossbowProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.spellbook.PlayerSpellbookProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.staff.PlayerStaffProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxeProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class Spawner extends Block {
    public Spawner(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        boolean[] canSpawn = new boolean[1];
        if(!pLevel.isClientSide()){
            if(pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().getItem() == ModItems.WARRIOR_STARTER_KEY.get()){
                if(pPlayer != null) {
                    pPlayer.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordLevel -> {
                        if (broadswordLevel.getSwordLevel() >= 30){
                            canSpawn[0] = true;
                        }
                    });
                    pPlayer.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(battleAxeLevel -> {
                        if (battleAxeLevel.getAxeLevel() >= 30){
                            canSpawn[0] = true;
                        }
                    });
                }
                pPlayer.getInventory().removeItem(pPlayer.getMainHandItem());
                if(canSpawn[0]){
                    EntityType<RedOgreEntity> red_ogre = ModEntityTypes.RED_OGRE.get();
                    red_ogre.spawn((ServerLevel)pLevel, null, pPlayer, pPos, MobSpawnType.MOB_SUMMONED, true, true);
                }
                else{
                    pPlayer.sendSystemMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            }
            if(pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().getItem() == ModItems.ARCHER_STARTER_KEY.get()){
                if(pPlayer != null) {
                    pPlayer.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowLevel -> {
                        if (bowLevel.getBowLevel() >= 30){
                            canSpawn[0] = true;
                        }
                    });
                    pPlayer.getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(crossbowLevel -> {
                        if (crossbowLevel.getCrossbowLevel() >= 30){
                            canSpawn[0] = true;
                        }
                    });
                }
                pPlayer.getInventory().removeItem(pPlayer.getMainHandItem());
                if(canSpawn[0]){
                    EntityType<GreenOgreEntity> green_ogre = ModEntityTypes.GREEN_OGRE.get();
                    green_ogre.spawn((ServerLevel)pLevel, null, pPlayer, pPos, MobSpawnType.MOB_SUMMONED, true, true);
                }
                else{
                    pPlayer.sendSystemMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            }
            if(pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().getItem() == ModItems.MAGE_STARTER_KEY.get()){
                if(pPlayer != null) {
                    pPlayer.getCapability(PlayerStaffProvider.PLAYER_STAFF).ifPresent(staffLevel -> {
                        if (staffLevel.getStaffExperience() >= 30){
                            canSpawn[0] = true;
                        }
                    });
                    pPlayer.getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(spellBookLevel -> {
                        if (spellBookLevel.getSpellbookLevel() >= 30){
                            canSpawn[0] = true;
                        }
                    });
                }
                pPlayer.getInventory().removeItem(pPlayer.getMainHandItem());
                if(canSpawn[0]){
                    EntityType<BlueOgreEntity> blue_ogre = ModEntityTypes.BLUE_OGRE.get();
                    blue_ogre.spawn((ServerLevel)pLevel, null, pPlayer, pPos, MobSpawnType.MOB_SUMMONED, true, true);
                }
                else{
                    pPlayer.sendSystemMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

}

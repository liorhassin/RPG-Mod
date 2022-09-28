package liornadav.rpgmod.block.custom;

import liornadav.rpgmod.item.ModItems;
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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.awt.*;

public class Spawner extends Block {
    public Spawner(Settings pProperties) {
        super(pProperties);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockHitResult hit) {
        boolean[] canSpawn = new boolean[1];
        if(!world.isClient()){
            if(pHand == Hand.MAIN_HAND && pPlayer.getMainHandStack().getItem() == ModItems.WARRIOR_STARTER_KEY.get()){
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
                pPlayer.getInventory().removeOne(pPlayer.getMainHandStack());
                if(canSpawn[0]){
                    EntityType<RedOgreEntity> red_ogre = ModEntityTypes.RED_OGRE.get();
                    red_ogre.spawn((ServerWorld)world, null, pPlayer, pPos, SpawnReason.MOB_SUMMONED, true, true);
                }
                else{
                    pPlayer.sendMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            }
            if(pHand == Hand.MAIN_HAND && pPlayer.getMainHandStack().getItem() == ModItems.ARCHER_STARTER_KEY.get()){
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
                pPlayer.getInventory().removeOne(pPlayer.getMainHandStack());
                if(canSpawn[0]){
                    EntityType<GreenOgreEntity> green_ogre = ModEntityTypes.GREEN_OGRE.get();
                    green_ogre.spawn((ServerWorld)world, null, pPlayer, pPos, SpawnReason.MOB_SUMMONED, true, true);
                }
                else{
                    pPlayer.sendMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            }
            if(pHand == Hand.MAIN_HAND && pPlayer.getMainHandStack().getItem() == ModItems.MAGE_STARTER_KEY.get()){
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
                pPlayer.getInventory().removeOne(pPlayer.getMainHandStack());
                if(canSpawn[0]){
                    EntityType<BlueOgreEntity> blue_ogre = ModEntityTypes.BLUE_OGRE.get();
                    blue_ogre.spawn((ServerWorld)world, null, pPlayer, pPos, SpawnReason.MOB_SUMMONED, true, true);
                }
                else{
                    pPlayer.sendMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            }
        }
        return super.onUse(state, world, pPos, pPlayer, pHand, hit);
    }
}

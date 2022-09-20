package net.LiorNadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBowProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshotProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.wand.PlayerWandProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxeProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//C2S meaning Client To Server.
public class AllWeaponsLevelC2SPacket {

    public AllWeaponsLevelC2SPacket(){

    }
    //For now stays empty.
    public AllWeaponsLevelC2SPacket(FriendlyByteBuf buf){

    }
    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        StringBuilder str = new StringBuilder();
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            str.append("Warrior Class\n");
            player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                str.append("Knife experience: "
                        + knifeExperience.getKnifeExperience()
                        + ", Knife level: " + knifeExperience.getKnifeLevel() + "\n");
            });
            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                str.append("Sword experience: "
                        + broadswordExperience.getSwordExperience()
                        + ", Sword level: " + broadswordExperience.getSwordLevel() + "\n");
            });
            player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(AxeExperience -> {
                str.append("Battle axe experience: "
                        + AxeExperience.getAxeExperience()
                        + ", Battle axe level: " + AxeExperience.getAxeLevel() + "\n");
            });
            str.append("\nArcher Class\n");
            player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotExperience -> {
                str.append("Slingshot experience: "
                        + slingshotExperience.getSlingshotExperience()
                        + ", Slingshot level: " + slingshotExperience.getSlingshotLevel() + "\n");
            });
            player.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowExperience -> {
                str.append("Bow experience: "
                        + bowExperience.getBowExperience()
                        + ", Bow level: " + bowExperience.getBowLevel() + "\n");
            });
            str.append("\nMage Class\n");
            player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandExperience -> {
                str.append("Wand experience: "
                        + wandExperience.getWandExperience()
                        + ", Wand level: " + wandExperience.getWandLevel() + "\n");
            });
            player.sendSystemMessage(Component.literal(str.toString()).withStyle(ChatFormatting.DARK_GREEN));
        });
        return true;
    }

}

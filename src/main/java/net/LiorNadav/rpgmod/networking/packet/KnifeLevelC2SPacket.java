package net.LiorNadav.rpgmod.networking.packet;


import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnife;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//This is an example packet just to use as reference.
//C2S meaning Client To Server.
public class KnifeLevelC2SPacket {

    public KnifeLevelC2SPacket(){

    }

    //For now stays empty.
    public KnifeLevelC2SPacket(FriendlyByteBuf buf){

    }

    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            //Everything here happens on the server, even tho the client is being sent by client.
            //The message itself comes from the server.

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            //player.sendSystemMessage(Component.literal("Knife level: ").withStyle(ChatFormatting.DARK_GREEN));

            player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                player.sendSystemMessage(Component.literal("Knife Experience: "
                        + knifeExperience.getKnifeExperience()
                        + ", Knife level: " + knifeExperience.getKnifeLevel()).withStyle(ChatFormatting.DARK_GREEN));
            });
        });
        return true;
    }

}

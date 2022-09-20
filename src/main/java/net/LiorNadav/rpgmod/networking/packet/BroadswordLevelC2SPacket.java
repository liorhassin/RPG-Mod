package net.LiorNadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//C2S meaning Client To Server.
public class BroadswordLevelC2SPacket {

    public BroadswordLevelC2SPacket(){

    }
    //For now stays empty.
    public BroadswordLevelC2SPacket(FriendlyByteBuf buf){

    }
    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                player.sendSystemMessage(Component.literal("Broadsword leveled up, Current level: " + broadswordExperience.getSwordLevel()).withStyle(ChatFormatting.DARK_GREEN));
            });
        });
        return true;
    }

}

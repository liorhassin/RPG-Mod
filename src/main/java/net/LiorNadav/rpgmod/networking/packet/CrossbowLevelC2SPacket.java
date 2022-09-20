package net.LiorNadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.weapon_leveling_system.archer.crossbow.PlayerCrossbowProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//C2S meaning Client To Server.
public class CrossbowLevelC2SPacket {

    public CrossbowLevelC2SPacket(){

    }
    //For now stays empty.
    public CrossbowLevelC2SPacket(FriendlyByteBuf buf){

    }
    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(PlayerCrossbowProvider.PLAYER_CROSSBOW).ifPresent(crossbowExperience -> {
                player.sendSystemMessage(Component.literal("Crossbow leveled up, Current level: " + crossbowExperience.getCrossbowLevel()).withStyle(ChatFormatting.DARK_GREEN));
            });
        });
        return true;
    }

}

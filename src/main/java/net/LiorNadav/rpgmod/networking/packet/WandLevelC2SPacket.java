package net.LiorNadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.weapon_leveling_system.mage.wand.PlayerWandProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//C2S meaning Client To Server.
public class WandLevelC2SPacket {

    public WandLevelC2SPacket(){

    }
    //For now stays empty.
    public WandLevelC2SPacket(FriendlyByteBuf buf){

    }
    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandExperience -> {
                player.sendSystemMessage(Component.literal("Wand leveled up, Current level: " + wandExperience.getWandLevel()).withStyle(ChatFormatting.DARK_GREEN));
            });
        });
        return true;
    }

}

package liornadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow.PlayerBowProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//C2S meaning Client To Server.
public class BowLevelC2SPacket {

    public BowLevelC2SPacket(){

    }
    //For now stays empty.
    public BowLevelC2SPacket(FriendlyByteBuf buf){

    }
    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(PlayerBowProvider.PLAYER_BOW).ifPresent(bowExperience -> {
                player.sendSystemMessage(Component.literal("Bow leveled up, Current level: " + bowExperience.getBowLevel()).withStyle(ChatFormatting.DARK_GREEN));
            });
        });
        return true;
    }

}

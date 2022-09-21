package net.LiorNadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerManaProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
public class ManaC2SPacket {

    public ManaC2SPacket(){

    }

    //For now stays empty.
    public ManaC2SPacket(FriendlyByteBuf buf){

    }

    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                ModMessages.sendToPlayer(new ManaDataSyncS2CPacket(mana.getMana()), player);
            });
        });
        return true;
    }

}

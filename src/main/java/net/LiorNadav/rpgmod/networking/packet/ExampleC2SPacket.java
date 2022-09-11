package net.LiorNadav.rpgmod.networking.packet;


import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//This is an example packet just to use as reference.
//C2S meaning Client To Server.
public class ExampleC2SPacket {
    public ExampleC2SPacket(){

    }

    //For now stays empty.
    public ExampleC2SPacket(FriendlyByteBuf buf){

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

            EntityType.COW.spawn(level, null, null, player.blockPosition(),
                    MobSpawnType.COMMAND, true, false);

        });
        return true;
    }

}

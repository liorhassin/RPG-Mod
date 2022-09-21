package net.LiorNadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.client.ClientManaData;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxeProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
public class ManaDataSyncS2CPacket {
    private final int mana;
    public ManaDataSyncS2CPacket(int mana){
        this.mana = mana;
    }

    //For now stays empty.
    public ManaDataSyncS2CPacket(FriendlyByteBuf buf){
        this.mana = buf.readInt();
    }

    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){
        buf.writeInt(mana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
           ClientManaData.set(mana);
        });
        return true;
    }

}

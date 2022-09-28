package liornadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe.PlayerBattleAxeProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//This is an example packet just to use as reference.
//C2S meaning Client To Server.
public class BattleAxeLevelC2SPacket {

    public BattleAxeLevelC2SPacket(){

    }

    //For now stays empty.
    public BattleAxeLevelC2SPacket(FriendlyByteBuf buf){

    }

    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(PlayerBattleAxeProvider.PLAYER_BATTLE_AXE).ifPresent(AxeExperience -> {
                player.sendSystemMessage(Component.literal("Battle axe leveled up, Current level: " + AxeExperience.getAxeLevel()).withStyle(ChatFormatting.DARK_GREEN));
            });
        });
        return true;
    }

}

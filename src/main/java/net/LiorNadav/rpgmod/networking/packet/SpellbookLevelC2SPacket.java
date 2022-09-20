package net.LiorNadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.weapon_leveling_system.mage.spellbook.PlayerSpellbookProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//C2S meaning Client To Server.
public class SpellbookLevelC2SPacket {

    public SpellbookLevelC2SPacket(){

    }
    //For now stays empty.
    public SpellbookLevelC2SPacket(FriendlyByteBuf buf){

    }
    //For now stays empty.
    public void toByte(FriendlyByteBuf buf){

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.getCapability(PlayerSpellbookProvider.PLAYER_SPELLBOOK).ifPresent(spellbookExperience -> {
                player.sendSystemMessage(Component.literal("Spellbook leveled up, Current level: " + spellbookExperience.getSpellbookLevel()).withStyle(ChatFormatting.DARK_GREEN));
            });
        });
        return true;
    }

}

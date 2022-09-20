package net.LiorNadav.rpgmod.item.custom;

import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.networking.packet.AllWeaponsLevelC2SPacket;
import net.LiorNadav.rpgmod.networking.packet.BattleAxeLevelC2SPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class Tablet extends Item {
    public Tablet(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()){
            ModMessages.sendToServer(new AllWeaponsLevelC2SPacket());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}

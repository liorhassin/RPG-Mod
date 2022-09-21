package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SuperiorStaffItem extends Item {
    public SuperiorStaffItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(hand == InteractionHand.MAIN_HAND) {
            MageSkills.fireball(player.getMainHandItem(), level, player, 6, false, true, 6, 10);
        }
        return super.use(level, player, hand);
    }
}

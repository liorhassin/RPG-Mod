package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AdvancedStaffItem extends Item {
    public AdvancedStaffItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(hand == InteractionHand.MAIN_HAND) {
            MageSkills.fireball(player.getMainHandItem(), level, player, 4, false, false, 5, 16);
        }
        return super.use(level, player, hand);
    }
}

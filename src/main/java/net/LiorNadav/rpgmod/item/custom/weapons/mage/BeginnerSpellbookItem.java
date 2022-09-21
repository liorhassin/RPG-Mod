package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BeginnerSpellbookItem extends Item {
    public BeginnerSpellbookItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(hand == InteractionHand.MAIN_HAND) {
            MageSkills.fireball(player.getMainHandItem(), level, player, 2, false, false, 3, 18);
        }
        return super.use(level, player, hand);
    }
}

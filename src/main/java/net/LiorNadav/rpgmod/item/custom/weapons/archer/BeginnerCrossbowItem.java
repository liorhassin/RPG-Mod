package net.LiorNadav.rpgmod.item.custom.weapons.archer;

import net.LiorNadav.rpgmod.entities.projectiles.TorchArrowEntity;
import net.LiorNadav.rpgmod.item.ModItems;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class BeginnerCrossbowItem extends ModCrossbow{

    public BeginnerCrossbowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ammoStack) -> {
            if (ammoStack.getItem() == Items.FIREWORK_ROCKET){
                return ammoStack.is(Items.FIREWORK_ROCKET);
            }
            else if(ammoStack.getItem() == ModItems.TORCH_ARROW.get()){
                return ammoStack.is(ModItems.TORCH_ARROW.get());
            }
            return ammoStack.is(ItemTags.ARROWS);
        };
    }

    @Override
    protected AbstractArrow createArrow(Level level, ItemStack ammoStack, Player player) {
        if(ammoStack.getItem() == ModItems.TORCH_ARROW.get()) {
            return new TorchArrowEntity(level, player);
        }
        return new Arrow(level,player);
    }
}

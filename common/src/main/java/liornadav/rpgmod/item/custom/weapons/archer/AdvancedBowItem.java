package liornadav.rpgmod.item.custom.weapons.archer;

import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.world.entity.projectile.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class AdvancedBowItem extends ModBow{
    public AdvancedBowItem(Properties properties) {
        super(properties);
    }

    protected double getArrowDamage(ItemStack bowStack, AbstractArrow arrowEntity) {
        int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);
            return (double)powerLevel + 1.5D;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ammoStack) -> {
            if(ammoStack.getItem() == ModItems.IRON_ARROW.get()){
                return ammoStack.getItem() == ModItems.IRON_ARROW.get();
            }
            else if(ammoStack.getItem() == ModItems.GOLD_ARROW.get()){
                return ammoStack.getItem() == ModItems.GOLD_ARROW.get();
            }
            else if(ammoStack.getItem() == ModItems.TORCH_ARROW.get()){
                return ammoStack.getItem() == ModItems.TORCH_ARROW.get();
            }
            else if(ammoStack.getItem() == ModItems.EXPLOSIVE_ARROW.get()){
                return ammoStack.getItem() == ModItems.EXPLOSIVE_ARROW.get();
            }
            else if(ammoStack.getItem() == ModItems.FROST_ARROW.get()){
                return ammoStack.getItem() == ModItems.FROST_ARROW.get();
            }
            return ammoStack.getItem() == Items.ARROW;
        };
    }

    @Override
    protected AbstractArrow createArrow(Level level, ItemStack ammoStack, Player player) {
        if(ammoStack.getItem() == ModItems.IRON_ARROW.get()) {
            return new IronArrowEntity(ModEntityTypes.IRON_ARROW.get(), player, level);
        }
        else if(ammoStack.getItem() == ModItems.GOLD_ARROW.get()) {
            return new GoldArrowEntity(ModEntityTypes.GOLD_ARROW.get(), player, level);
        }
        else if(ammoStack.getItem() == ModItems.TORCH_ARROW.get()) {
            return new TorchArrowEntity(ModEntityTypes.TORCH_ARROW.get(), player, level);
        }
        else if(ammoStack.getItem() == ModItems.EXPLOSIVE_ARROW.get()) {
            return new ExplosiveArrowEntity(ModEntityTypes.EXPLOSIVE_ARROW.get(), player, level);
        }
        else if(ammoStack.getItem() == ModItems.FROST_ARROW.get()) {
            return new FrostArrowEntity(ModEntityTypes.FROST_ARROW.get(), player, level);
        }
        return new Arrow(level,player);
    }
}

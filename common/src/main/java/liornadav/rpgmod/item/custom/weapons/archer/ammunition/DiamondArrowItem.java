package liornadav.rpgmod.item.custom.weapons.archer.ammunition;

import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.world.entity.projectile.DiamondArrowEntity;
import net.LiorNadav.rpgmod.world.entity.projectile.TorchArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class DiamondArrowItem extends ArrowItem {
    public final float damage;
    public DiamondArrowItem(Settings settings, float damage) {
        super(settings);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity entity) {
        DiamondArrowEntity newArrow = new DiamondArrowEntity(ModEntityTypes.DIAMOND_ARROW.get(), entity, level);
        newArrow.setBaseDamage(this.damage);
        return newArrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = stack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS);
        return enchant <= 0 ? false : this.getClass() == DiamondArrowItem.class;
    }

}

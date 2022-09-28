package liornadav.rpgmod.item.custom.weapons.archer.ammunition;

import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.world.entity.projectile.EmeraldArrowEntity;
import net.LiorNadav.rpgmod.world.entity.projectile.ExplosiveArrowEntity;
import net.LiorNadav.rpgmod.world.entity.projectile.TorchArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ExplosiveArrowItem extends ArrowItem {
    public final float damage;
    public ExplosiveArrowItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity entity) {
        ExplosiveArrowEntity newArrow = new ExplosiveArrowEntity(ModEntityTypes.EXPLOSIVE_ARROW.get(), entity, level);
        newArrow.setBaseDamage(this.damage);
        return newArrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = stack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS);
        return enchant <= 0 ? false : this.getClass() == ExplosiveArrowItem.class;
    }

}

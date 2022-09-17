package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class ModWand extends BowItem {
    public ModWand(Properties properties) {
        super(properties);
    }

    public void releaseUsing(ItemStack bowStack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {


            int timeDrawn = this.getUseDuration(bowStack) - timeLeft;
            if (timeDrawn < 0) return;


                float velocity = getPowerForTime(timeDrawn);
                if (!((double)velocity < 0.95D)) {
                    if (!level.isClientSide) {
                        //DragonFireball arrowEntity = new DragonFireball(level, player, player.getX(), player.getY(), player.getZ());
                        LargeFireball arrowEntity = new LargeFireball(level, player, player.getX(), player.getY(), player.getZ(), 0);
                        arrowEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity * 3.0F, 1.0F);

                        bowStack.hurtAndBreak(1, player, (player1) -> {
                            player1.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        /*if (player.getAbilities().instabuild && hasInfinity && !isTippedArrow) {
                            arrowEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }*/
                        level.addFreshEntity(arrowEntity);
                    }

                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);


                    //player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player) {

            entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100), entity);



                //player.awardStat(Stats.ITEM_USED.get(this));
            }
        return false;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ammoStack) -> {
            return ammoStack.is(ItemTags.ARROWS);
        };
    }

}

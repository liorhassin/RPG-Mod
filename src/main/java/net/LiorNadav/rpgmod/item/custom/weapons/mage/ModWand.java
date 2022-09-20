package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerManaProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;
public class ModWand extends BowItem {
    public ModWand(Properties properties) {
        super(properties);
    }

    public void fireball(ItemStack bowStack, Level level, LivingEntity player, int timeLeft){
            int timeDrawn = this.getUseDuration(bowStack) - timeLeft;
            if (timeDrawn < 0) return;

            float velocity = getPowerForTime(timeDrawn);
            if (!((double)velocity < 0.95D)) {
                if (!level.isClientSide) {
                    player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                        if (mana.getMana() > 20){
                            //DragonFireball arrowEntity = new DragonFireball(level, player, player.getX(), player.getY(), player.getZ());
                            LargeFireball fireballEntity = new LargeFireball(level, player, 0, -0.1f, 0, 0);
                            fireballEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.7F, velocity * 3.0f, 1.0F);

                            bowStack.hurtAndBreak(1, player, (player1) -> {
                                player1.broadcastBreakEvent(player.getUsedItemHand());
                            });
                            level.addFreshEntity(fireballEntity);
                            mana.subMana(10);
                            player.sendSystemMessage(Component.literal("Current mana: " + mana.getMana()));
                        }
                    });
                }
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
            }
    }

    public void releaseUsing(ItemStack bowStack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player player) {
            fireball(bowStack,level,entityLiving,timeLeft);
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
    public int getDefaultProjectileRange() {
        return 25;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ammoStack) -> {
            return ammoStack.is(ItemTags.ARROWS);
        };
    }

}

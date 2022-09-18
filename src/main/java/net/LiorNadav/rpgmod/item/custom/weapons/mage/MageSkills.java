package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerManaProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.world.item.BowItem.getPowerForTime;

public class MageSkills extends Item {
    public MageSkills(Properties pProperties) {
        super(pProperties);
    }

    public void fireball(ItemStack bowStack, Level level, LivingEntity player) {
        float velocity = 0.5f;
        if (!level.isClientSide) {
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                if (mana.getMana() >= 15) {
                    //DragonFireball arrowEntity = new DragonFireball(level, player, player.getX(), player.getY(), player.getZ());
                    LargeFireball fireballEntity = new LargeFireball(level, player, 0, -0.1f, 0, 0);
                    fireballEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.7F, velocity * 3.0f, 1.0F);

                    bowStack.hurtAndBreak(1, player, (player1) -> {
                        player1.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    level.addFreshEntity(fireballEntity);
                    mana.subMana(15);
                    player.sendSystemMessage(Component.literal("Current mana: " + mana.getMana()));
                }
            });
        }
        level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }
}

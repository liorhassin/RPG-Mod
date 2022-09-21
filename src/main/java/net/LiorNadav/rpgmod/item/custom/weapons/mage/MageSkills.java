package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.LiorNadav.rpgmod.weapon_leveling_system.mage.PlayerManaProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;


public class MageSkills {

    public static void fireball(ItemStack bowStack, Level level, LivingEntity player, int explosivePower,
                                boolean explosiveGrief, boolean explosiveFire, float tierSpeed, int manaCost) {
        float velocity = 0.5f;
        if (!level.isClientSide) {
            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                if (mana.getMana() >= 15) {
                    //DragonFireball arrowEntity = new DragonFireball(level, player, player.getX(), player.getY(), player.getZ());
                    ModFireballSkill fireballEntity = new ModFireballSkill(level, player, 0, 0, 0, 0, explosivePower, explosiveGrief, explosiveFire);
                    fireballEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, velocity * tierSpeed, 3.0f);
                    bowStack.hurtAndBreak(1, player, (player1) -> {
                        player1.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    level.addFreshEntity(fireballEntity);
                    mana.subMana(manaCost);
                    player.sendSystemMessage(Component.literal("Current mana: " + mana.getMana()));
                }
            });
        }
        level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0f, 1.0f / (level.getRandom().nextFloat() * 0.4f + 1.2f) + velocity * 0.5f);
    }

    //W.I.P
    public static void LightningStrike(){

    }
}

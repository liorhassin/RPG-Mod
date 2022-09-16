package net.LiorNadav.rpgmod.item.custom.weapons.warrior;

import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class BeginnerBroadSwordItem extends SwordItem {
    public BeginnerBroadSwordItem(Tier tier, int i, float v, Properties properties) {
        super(tier, i, v, properties);
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof Player){
            Player player = ((Player)pAttacker);
            player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeLevel -> {
                if (knifeLevel.getKnifeLevel() < 10){
                    pAttacker.setSecondsOnFire(10);
                    ((Player)pAttacker).getInventory().removeItem(pStack);
                    pAttacker.sendSystemMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            });
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}

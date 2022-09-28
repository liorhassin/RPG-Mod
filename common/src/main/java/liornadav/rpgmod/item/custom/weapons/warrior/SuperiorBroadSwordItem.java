package liornadav.rpgmod.item.custom.weapons.warrior;

import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SuperiorBroadSwordItem extends SwordItem {
    public SuperiorBroadSwordItem(Tier tier, int i, float v, Properties properties) {
        super(tier, i, v, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof Player){
            Player player = ((Player)pAttacker);
            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordLevel -> {
                if (broadswordLevel.getSwordLevel() < 60){
                    pAttacker.setSecondsOnFire(10);
                    ((Player)pAttacker).getInventory().removeItem(pStack);
                    pAttacker.sendSystemMessage(Component.literal("You are not worthy for this kind of power!"));
                }
            });
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}

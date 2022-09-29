package liornadav.rpgmod.world.entity.projectile;

import liornadav.rpgmod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
//import net.minecraftforge.network.NetworkHooks;

public class FrostArrowEntity extends PersistentProjectileEntity {
    public FrostArrowEntity(EntityType<FrostArrowEntity> pEntityType, World pWorld) {
        super(pEntityType, pWorld);
    }

    public FrostArrowEntity(EntityType<FrostArrowEntity> pEntityType, double pX, double pY, double pZ, World pWorld) {
        super(pEntityType, pX, pY, pZ, pWorld);
    }

    public FrostArrowEntity(EntityType<FrostArrowEntity> pEntityType, LivingEntity pShooter, World pWorld) {
        super(pEntityType, pShooter, pWorld);
    }


    /*
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
*/
    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.FROST_ARROW.get());
    }

    @Override
    protected void onEntityHit(EntityHitResult result) {
        super.onEntityHit(result);
        LivingEntity living = (LivingEntity)result.getEntity();
        living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 4));
    }

}
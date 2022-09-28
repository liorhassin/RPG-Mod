package liornadav.rpgmod.world.entity.projectile;

import net.LiorNadav.rpgmod.item.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class FrostArrowEntity extends AbstractArrow {
    public FrostArrowEntity(EntityType<FrostArrowEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FrostArrowEntity(EntityType<FrostArrowEntity> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public FrostArrowEntity(EntityType<FrostArrowEntity> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.FROST_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        LivingEntity living = (LivingEntity)result.getEntity();
        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
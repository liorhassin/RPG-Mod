package net.LiorNadav.rpgmod.item.custom.weapons.mage;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ModFireballSkill extends LargeFireball {
    private int customExplosivePower;
    private boolean explosiveGrief;
    private boolean explosiveFire;

    public ModFireballSkill(EntityType<? extends LargeFireball> pEntityType, Level pLevel, int customExplosivePower,
                            boolean explosiveGrief, boolean explosiveFire) {
        super(pEntityType, pLevel);
        this.customExplosivePower = customExplosivePower;
        this.explosiveGrief = explosiveGrief;
        this.explosiveFire = explosiveFire;
    }

    public ModFireballSkill(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ,
                            int pExplosionPower, int customExplosivePower, boolean explosiveGrief, boolean explosiveFire) {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ, pExplosionPower);
        this.explosiveGrief = explosiveGrief;
        this.customExplosivePower = customExplosivePower;
        this.explosiveFire = explosiveFire;
    }
    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level.isClientSide) {
            Explosion.BlockInteraction flag;
            if (explosiveGrief){
                flag = Explosion.BlockInteraction.DESTROY;
            }
            else{
                flag = Explosion.BlockInteraction.NONE;
            }
            this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), (float)this.customExplosivePower, explosiveFire, flag);
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (tickCount == 40){
            this.discard();
        }
    }
}

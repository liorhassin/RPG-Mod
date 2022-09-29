package liornadav.rpgmod.world.entity.projectile;

import liornadav.rpgmod.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
//import net.minecraftforge.network.NetworkHooks;

public class ExplosiveArrowEntity extends PersistentProjectileEntity {
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> pEntityType, World pWorld) {
        super(pEntityType, pWorld);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> pEntityType, double pX, double pY, double pZ, World pWorld) {
        super(pEntityType, pX, pY, pZ, pWorld);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> pEntityType, LivingEntity pShooter, World pWorld) {
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
        return new ItemStack(ModItems.EXPLOSIVE_ARROW.get());
    }

    @Override
    protected void onEntityHit(EntityHitResult result) {
        super.onEntityHit(result);
        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 2.5f, false, Explosion.DestructionType.NONE);
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);
        this.setDamage(2);
        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 2.5f, false, Explosion.DestructionType.NONE);
    }
}
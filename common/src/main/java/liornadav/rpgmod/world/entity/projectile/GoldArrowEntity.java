package liornadav.rpgmod.world.entity.projectile;


import liornadav.rpgmod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//import net.minecraftforge.network.NetworkHooks;

public class GoldArrowEntity extends PersistentProjectileEntity {
    public GoldArrowEntity(EntityType<GoldArrowEntity> pEntityType, World pWorld) {
        super(pEntityType, pWorld);
    }

    public GoldArrowEntity(EntityType<GoldArrowEntity> pEntityType, double pX, double pY, double pZ, World pWorld) {
        super(pEntityType, pX, pY, pZ, pWorld);
    }

    public GoldArrowEntity(EntityType<GoldArrowEntity> pEntityType, LivingEntity pShooter, World pWorld) {
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
        return new ItemStack(ModItems.GOLD_ARROW.get());
    }
}
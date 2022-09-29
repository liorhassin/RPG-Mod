package liornadav.rpgmod.world.entity.projectile;

import liornadav.rpgmod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//import net.minecraftforge.network.NetworkHooks;

public class EmeraldArrowEntity extends PersistentProjectileEntity {
    public EmeraldArrowEntity(EntityType<EmeraldArrowEntity> pEntityType, World pWorld) {
        super(pEntityType, pWorld);
    }

    public EmeraldArrowEntity(EntityType<EmeraldArrowEntity> pEntityType, double pX, double pY, double pZ, World pWorld) {
        super(pEntityType, pX, pY, pZ, pWorld);
    }

    public EmeraldArrowEntity(EntityType<EmeraldArrowEntity> pEntityType, LivingEntity pShooter, World pWorld) {
        super(pEntityType, pShooter, pWorld);
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.EMERALD_ARROW.get());
    }
/*
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }*/
}
package liornadav.rpgmod.world.entity.projectile;

import net.LiorNadav.rpgmod.item.ModItems;
import liornadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class TorchArrowEntity extends PersistentProjectileEntity {
    public TorchArrowEntity(EntityType<TorchArrowEntity> pEntityType, World pWorld) {
        super(pEntityType, pWorld);
    }

    public TorchArrowEntity(EntityType<TorchArrowEntity> pEntityType, double pX, double pY, double pZ, World pWorld) {
        super(pEntityType, pX, pY, pZ, pWorld);
    }

    public TorchArrowEntity(EntityType<TorchArrowEntity> pEntityType, LivingEntity pShooter, World pWorld) {
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
        return new ItemStack(ModItems.TORCH_ARROW.get());
    }

    @Override
    protected void onEntityHit(EntityHitResult result) {
        super.onEntityHit(result);
        if (result.getEntity() instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)result.getEntity();
            living.setOnFireFor(4);
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 80, 0));
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult pResult) {
        super.onBlockHit(pResult);
        setToTorch(pResult);
        this.remove(Entity.RemovalReason.DISCARDED);
    }

    private void setToTorch(BlockHitResult hit) {
        Direction face = hit.getSide();
        BlockPos pos = hit.getBlockPos().relative(face);
        boolean isAir = this.world.getBlockState(pos).isAir();
        if (face == Direction.DOWN || !isAir){
            ItemEntity torch = new ItemEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.TORCH_ARROW.get()));
            world.addFreshEntity(torch);
        }
        else{
            BlockState state = Blocks.TORCH.defaultBlockState();
            String stringDirection = face.getName();
            switch(stringDirection){
                case "south":
                    state = Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING ,Direction.SOUTH);
                    break;
                case "east":
                    state = Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING ,Direction.EAST);
                    break;
                case "north":
                    state = Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING ,Direction.NORTH);
                    break;
                case "west":
                    state = Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING ,Direction.WEST);
                    break;
                default:
                    break;
            }
            world.setBlockState(pos, state);
        }
    }
}
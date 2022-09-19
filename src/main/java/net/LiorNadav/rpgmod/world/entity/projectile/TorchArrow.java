package net.LiorNadav.rpgmod.world.entity.projectile;

import net.LiorNadav.rpgmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class TorchArrow extends AbstractArrow {
    public TorchArrow(EntityType<TorchArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TorchArrow(EntityType<TorchArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public TorchArrow(EntityType<TorchArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.TORCH_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (result.getEntity() instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)result.getEntity();
            super.onHitEntity(result);
            living.setSecondsOnFire(8);
            living.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        setToTorch(pResult);
        super.onHitBlock(pResult);
        this.remove(Entity.RemovalReason.DISCARDED);
    }

    private void setToTorch(BlockHitResult hit) {
        Direction face = hit.getDirection();
        BlockPos pos = hit.getBlockPos().relative(face);
        boolean isAir = this.level.getBlockState(pos).isAir();
        if (face == Direction.DOWN || !isAir){
            ItemEntity torch = new ItemEntity(this.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.TORCH_ARROW.get()));
            level.addFreshEntity(torch);
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
            level.setBlockAndUpdate(pos, state);
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
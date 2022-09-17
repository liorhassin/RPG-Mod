package net.LiorNadav.rpgmod.entities.projectiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class TorchArrowEntity extends Arrow {
    public TorchArrowEntity(Level level, LivingEntity entity) {
        super(level, entity);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        HitResult.Type hitType = result.getType();
        if (hitType == HitResult.Type.BLOCK){
            setToTorch((BlockHitResult)result);
            this.remove(Entity.RemovalReason.DISCARDED);
        }
    }

    private void setToTorch(BlockHitResult hit) {
        Direction face = hit.getDirection();
        BlockPos pos = hit.getBlockPos().relative(face);
        boolean isAir = this.level.getBlockState(pos).isAir();
        if (face == Direction.DOWN || !isAir){
            ItemEntity torch = new ItemEntity(this.level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.TORCH));
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
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setSecondsOnFire(10);
        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
    }
}

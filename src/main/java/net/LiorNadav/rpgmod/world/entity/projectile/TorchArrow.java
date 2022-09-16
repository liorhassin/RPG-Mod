package net.LiorNadav.rpgmod.world.entity.projectile;

import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.world.entity.ModEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class TorchArrow extends AbstractArrow {
    private final Item referenceItem;
    public TorchArrow(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = ModItems.TORCH_ARROW.get();
    }

    public TorchArrow(LivingEntity entity, Level level, Item referenceItem) {
        super(ModEntityType.TORCH_ARROW.get(), entity, level);
        this.referenceItem = referenceItem;
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        ((LivingEntity)result.getEntity()).addEffect(new MobEffectInstance((new MobEffects()).POISON, 200));
        super.onHitEntity(result);
    }
}

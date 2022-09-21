package net.LiorNadav.rpgmod.entity.custom;

import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshot;
import net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot.PlayerSlingshotProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.mage.wand.PlayerWandProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ZombieRPGEntity extends Monster implements IAnimatable {

    private static final float ATTACK_DMG = 3f;
    private AnimationFactory factory = new AnimationFactory(this);

    public ZombieRPGEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DMG)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f).build();
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Creeper.class, true));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.zombie_rpg.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.zombie_rpg.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event){
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.zombie_rpg.attack", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "zombiecontroller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "zombieattackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_STRAY_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.DOLPHIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        boolean[] canDrop = new boolean[3];
        if(pSource.getEntity() instanceof Player){
            String mainItemName = ((Player) pSource.getEntity()).getMainHandItem().getItem().toString();
            Player player = (Player) pSource.getEntity();
            switch(mainItemName) {
                case "beginner_broadsword":
                case "advanced_broadsword":
                case "superior_broadsword":
                case "beginner_battle_axe":
                case "advanced_battle_axe":
                case "superior_battle_axe":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeLevel -> {
                        if (knifeLevel.getKnifeLevel() >= 10) {
                            canDrop[0] = true;
                        }
                    });
                    if (canDrop[0]) {
                        ItemEntity itemEntity = this.spawnAtLocation(ModItems.WARRIOR_STARTER_KEY.get());
                        if (itemEntity != null) {
                            itemEntity.setExtendedLifetime();
                        }
                    }
                    break;
                case "beginner_bow":
                case "advanced_bow":
                case "superior_bow":
                case "beginner_crossbow":
                case "advanced_crossbow":
                case "superior_crossbow":
                    player.getCapability(PlayerSlingshotProvider.PLAYER_SLINGSHOT).ifPresent(slingshotLevel -> {
                        if (slingshotLevel.getSlingshotLevel() >= 10) {
                            canDrop[0] = true;
                        }
                    });
                    if (canDrop[0]) {
                        ItemEntity itemEntity = this.spawnAtLocation(ModItems.ARCHER_STARTER_KEY.get());
                        if (itemEntity != null) {
                            itemEntity.setExtendedLifetime();
                        }
                    }
                    break;
                case "beginner_staff":
                case "advanced_staff":
                case "superior_staff":
                case "beginner_spellbook":
                case "advanced_spellbook":
                case "superior_spellbook":
                    player.getCapability(PlayerWandProvider.PLAYER_WAND).ifPresent(wandLevel -> {
                        if (wandLevel.getWandLevel() >= 10) {
                            canDrop[0] = true;
                        }
                    });
                    if (canDrop[0]) {
                        ItemEntity itemEntity = this.spawnAtLocation(ModItems.MAGE_STARTER_KEY.get());
                        if (itemEntity != null) {
                            itemEntity.setExtendedLifetime();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
    }
}

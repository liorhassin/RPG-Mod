package net.LiorNadav.rpgmod.item.custom.weapons.mage;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class ModLightningStrikeSkill extends LightningBolt {
    private final int lightningRadius;
    private final boolean lightningFire;
    private final int lightningPower;

    public ModLightningStrikeSkill(EntityType<? extends LightningBolt> pEntityType, Level pLevel, int lightningRadius, int lightningPower, boolean lightningFire) {
        super(pEntityType, pLevel);
        this.lightningRadius = lightningRadius;
        this.lightningPower = lightningPower;
        this.lightningFire = lightningFire;
    }

}

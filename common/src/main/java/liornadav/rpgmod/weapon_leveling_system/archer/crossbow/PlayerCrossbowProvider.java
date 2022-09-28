package liornadav.rpgmod.weapon_leveling_system.archer.crossbow;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCrossbowProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerCrossbow> PLAYER_CROSSBOW = CapabilityManager.get(new CapabilityToken<PlayerCrossbow>() { });
    
    private PlayerCrossbow crossbow = null;
    private final LazyOptional<PlayerCrossbow> optional = LazyOptional.of(this::createCrossbow);

    private PlayerCrossbow createCrossbow() {
        if(crossbow == null){
            crossbow = new PlayerCrossbow();
        }
        return crossbow;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_CROSSBOW){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCrossbow().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCrossbow().loadNBTData(nbt);
    }
}

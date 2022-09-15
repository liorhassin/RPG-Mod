package net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot;

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

public class PlayerSlingshotProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerSlingshot> PLAYER_SLINGSHOT = CapabilityManager.get(new CapabilityToken<PlayerSlingshot>() { });
    
    private PlayerSlingshot slingshot = null;
    private final LazyOptional<PlayerSlingshot> optional = LazyOptional.of(this::createPlayerSlingshot);

    private PlayerSlingshot createPlayerSlingshot() {
        if(slingshot == null){
            slingshot = new PlayerSlingshot();
        }
        return slingshot;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_SLINGSHOT){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerSlingshot().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSlingshot().loadNBTData(nbt);
    }
}

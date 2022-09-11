package net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife;

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

public class PlayerKnifeProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerKnife> PLAYER_KNIFE = CapabilityManager.get(new CapabilityToken<PlayerKnife>() { });
    
    private PlayerKnife knife = null;
    private final LazyOptional<PlayerKnife> optional = LazyOptional.of(this::createPlayerKnife);

    private PlayerKnife createPlayerKnife() {
        if(knife == null){
            knife = new PlayerKnife();
        }
        return knife;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_KNIFE){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerKnife().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerKnife().loadNBTData(nbt);
    }
}

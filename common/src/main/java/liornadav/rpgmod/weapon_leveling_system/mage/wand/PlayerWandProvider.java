package liornadav.rpgmod.weapon_leveling_system.mage.wand;

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

public class PlayerWandProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerWand> PLAYER_WAND = CapabilityManager.get(new CapabilityToken<PlayerWand>() { });
    
    private PlayerWand wand = null;
    private final LazyOptional<PlayerWand> optional = LazyOptional.of(this::createPlayerWand);

    private PlayerWand createPlayerWand() {
        if(wand == null){
            wand = new PlayerWand();
        }
        return wand;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_WAND){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerWand().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerWand().loadNBTData(nbt);
    }
}

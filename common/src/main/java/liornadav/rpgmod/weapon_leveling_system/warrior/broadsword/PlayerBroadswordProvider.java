package liornadav.rpgmod.weapon_leveling_system.warrior.broadsword;

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

public class PlayerBroadswordProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerBroadsword> PLAYER_BROADSWORD = CapabilityManager.get(new CapabilityToken<PlayerBroadsword>() { });
    
    private PlayerBroadsword broadsword = null;
    private final LazyOptional<PlayerBroadsword> optional = LazyOptional.of(this::createPlayerBroadsword);

    private PlayerBroadsword createPlayerBroadsword() {
        if(broadsword == null){
            broadsword = new PlayerBroadsword();
        }
        return broadsword;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_BROADSWORD){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerBroadsword().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerBroadsword().loadNBTData(nbt);
    }
}

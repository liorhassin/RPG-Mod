package liornadav.rpgmod.weapon_leveling_system.mage.staff;

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

public class PlayerStaffProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerStaff> PLAYER_STAFF = CapabilityManager.get(new CapabilityToken<PlayerStaff>() { });
    
    private PlayerStaff staff = null;
    private final LazyOptional<PlayerStaff> optional = LazyOptional.of(this::createStaff);

    private PlayerStaff createStaff() {
        if(staff == null){
            staff = new PlayerStaff();
        }
        return staff;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_STAFF){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createStaff().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createStaff().loadNBTData(nbt);
    }
}

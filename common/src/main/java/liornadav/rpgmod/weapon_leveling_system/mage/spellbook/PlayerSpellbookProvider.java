package liornadav.rpgmod.weapon_leveling_system.mage.spellbook;

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

public class PlayerSpellbookProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerSpellbook> PLAYER_SPELLBOOK = CapabilityManager.get(new CapabilityToken<PlayerSpellbook>() { });
    
    private PlayerSpellbook spellbook = null;
    private final LazyOptional<PlayerSpellbook> optional = LazyOptional.of(this::createSpellbook);

    private PlayerSpellbook createSpellbook() {
        if(spellbook == null){
            spellbook = new PlayerSpellbook();
        }
        return spellbook;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_SPELLBOOK){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createSpellbook().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createSpellbook().loadNBTData(nbt);
    }
}

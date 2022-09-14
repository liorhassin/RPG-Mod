package net.LiorNadav.rpgmod.weapon_leveling_system.warrior.battle_axe;

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

public class PlayerBattleAxeProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerBattleAxe> PLAYER_BATTLE_AXE = CapabilityManager.get(new CapabilityToken<PlayerBattleAxe>() { });
    
    private PlayerBattleAxe battleAxe = null;
    private final LazyOptional<PlayerBattleAxe> optional = LazyOptional.of(this::createPlayerBattleAxe);

    private PlayerBattleAxe createPlayerBattleAxe() {
        if(battleAxe == null){
            battleAxe = new PlayerBattleAxe();
        }
        return battleAxe;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_BATTLE_AXE){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerBattleAxe().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerBattleAxe().loadNBTData(nbt);
    }
}

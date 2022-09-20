package net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife;

import net.LiorNadav.rpgmod.networking.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class PlayerKnife{
    private int knifeLevel; //
    private int knifeExperience; //
    private final int [] knifeExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int MAX_LEVEL = 10;

    public PlayerKnife(){
        knifeExperienceRequirement = new int[MAX_LEVEL];
        resetExperienceArray(knifeExperienceRequirement);
    }

    private void resetExperienceArray(int[] arr){
        int amount = 10;
        arr[0] = amount;
        for(int i = 1; i < arr.length; i++){
            arr[i] = arr[i - 1] + (int) (amount * (1 + ((double) i / 10)));
        }
    }

    public void addExperience(int add){
        if (knifeLevel < MAX_LEVEL && add > 0) {
            if (knifeExperience + add >= knifeExperienceRequirement[knifeLevel]) {
                int remainingAdd = add - (knifeExperienceRequirement[knifeLevel] - knifeExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                knifeExperience+=add;
            }
        }
    }
    public void addLevel(int level){
        if (level >= 0) {
            knifeLevel = Math.min(knifeLevel + level, MAX_LEVEL);
            knifeExperience = 0;
        }
    }
    public int getKnifeExperience() {
        return knifeExperience;
    }

    public int getKnifeLevel() { return knifeLevel; }

    public int getExperienceArray(int current){
        return knifeExperienceRequirement[current];
    }
    public void copyFrom(PlayerKnife source){
        knifeLevel = source.knifeLevel;
        knifeExperience = source.knifeExperience;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("knifeLevel", knifeLevel);
        nbt.putInt("knifeExperience", knifeExperience);
    }

    public void loadNBTData (CompoundTag nbt){
        knifeLevel = nbt.getInt("knifeLevel");
        knifeExperience = nbt.getInt("knifeExperience");
    }
}

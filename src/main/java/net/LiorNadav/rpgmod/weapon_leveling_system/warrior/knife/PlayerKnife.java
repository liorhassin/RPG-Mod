package net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife;

import net.minecraft.nbt.CompoundTag;

public class PlayerKnife {
    private int knifeLevel; //
    private int knifeExperience; //
    private final int [] knifeExperienceRequirement = new int[10];
    private final int MIN_LEVEL = 1;
    private final int MAX_LEVEL = 10;

    public float getKnifeLevel(){
        return knifeLevel;
    }

    public void addExperience(int add){
        if (knifeLevel < 10 && add > 0) {
            if (knifeExperience + add >= knifeExperienceRequirement[knifeLevel]) {
                int remainingAdd = add - knifeExperienceRequirement[knifeLevel] - knifeExperience;
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                knifeExperience+=add;
            }
        }
    }

    public void addLevel(int level){
        knifeLevel = Math.min(knifeLevel + level, MAX_LEVEL);
        knifeExperience = 0;
    }

    public void setKnifeLevel(int level){
        if (level <= MAX_LEVEL && level >= MIN_LEVEL) {
            knifeLevel = level;
            //Optional log output with level successfully set to level.
        }
        else{
            //Log output with wrong level given, levels of knife can be set between 1-10.
        }
    }

    //Method to build array of experience required for each knife level.
    public void buildExperienceRequirementArr(){
        int startingExperience = 100;
        for(int i = 0; i < MAX_LEVEL; i++){
            knifeExperienceRequirement[i] = startingExperience * (i + 1);
        }
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

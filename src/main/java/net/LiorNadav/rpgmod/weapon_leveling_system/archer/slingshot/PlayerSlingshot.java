package net.LiorNadav.rpgmod.weapon_leveling_system.archer.slingshot;

import net.minecraft.nbt.CompoundTag;

public class PlayerSlingshot {
    private int slingshotLevel; //
    private int slingshotExperience; //
    private final int [] slingshotExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int MAX_LEVEL = 10;

    public PlayerSlingshot(){
        slingshotExperienceRequirement = new int[MAX_LEVEL];
        resetExperienceArray(slingshotExperienceRequirement);
    }

    private void resetExperienceArray(int[] arr){
        int amount = 10;
        arr[0] = amount;
        for(int i = 1; i < arr.length; i++){
            arr[i] = arr[i - 1] + (int) (amount * (1 + ((double) i / 10)));
        }
    }

    public void addExperience(int add){
        if (slingshotLevel < MAX_LEVEL && add > 0) {
            if (slingshotExperience + add >= slingshotExperienceRequirement[slingshotLevel]) {
                int remainingAdd = add - (slingshotExperienceRequirement[slingshotLevel] - slingshotExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                slingshotExperience +=add;
            }
        }
    }

    public void addLevel(int level){
        if (level >= 0) {
            slingshotLevel = Math.min(slingshotLevel + level, MAX_LEVEL);
            slingshotExperience = 0;
        }
    }

    public void setSlingshotLevel(int level){
        if (level <= MAX_LEVEL && level >= MIN_LEVEL) {
            slingshotLevel = level;
            //Optional log output with level successfully set to level.
        }
        else{
            //Log output with wrong level given, levels of knife can be set between 1-10.
        }
    }

    public void setSlingshotExperience(int experience){
        if(experience >= 0){
            slingshotExperience = experience;
        }
    }

    public int getSlingshotExperience() {
        return slingshotExperience;
    }

    public int getSlingshotLevel() { return slingshotLevel; }

    public void copyFrom(PlayerSlingshot source){
        slingshotLevel = source.slingshotLevel;
        slingshotExperience = source.slingshotExperience;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("slingshotLevel", slingshotLevel);
        nbt.putInt("slingshotExperience", slingshotExperience);
    }

    public void loadNBTData (CompoundTag nbt){
        slingshotLevel = nbt.getInt("slingshotLevel");
        slingshotExperience = nbt.getInt("slingshotExperience");
    }
}

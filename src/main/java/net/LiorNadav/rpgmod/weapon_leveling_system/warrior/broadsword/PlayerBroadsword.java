package net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword;

import net.minecraft.nbt.CompoundTag;

public class PlayerBroadsword {
    private int swordLevel;
    private int swordExperience;
    private int swordAdvancementFlag; // 0,1,2
    private final int [] swordExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int [] MAX_LEVEL = {30,60,100};

    public PlayerBroadsword(){
        swordExperienceRequirement = new int[100];
        resetExperienceArray(swordExperienceRequirement);
        swordAdvancementFlag = 0;
    }

    private void resetExperienceArray(int[] arr){
        int amount = 10;
        arr[0] = amount;
        for(int i = 1; i < arr.length; i++){
            arr[i] = arr[i - 1] + (int) (amount * (1 + ((double) i / 10)));
        }
    }

    public void addExperience(int add){
        if (swordLevel < MAX_LEVEL[swordAdvancementFlag] && add > 0) {
            if (swordExperience + add >= swordExperienceRequirement[swordLevel]) {
                int remainingAdd = add - (swordExperienceRequirement[swordLevel] - swordExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                swordExperience+=add;
            }
        }
    }

    public void addLevel(int level) {
        if (level >= 0) {
            swordLevel = Math.min(swordLevel + level, MAX_LEVEL[swordAdvancementFlag]);
            if (swordLevel >= MAX_LEVEL[swordAdvancementFlag]) {
                swordAdvancementFlag = Math.min(2, swordAdvancementFlag + 1);
            }
            swordExperience = 0;
        }
    }
    public int getSwordExperience() {
        return swordExperience;
    }

    public int getSwordLevel() { return swordLevel; }

    public int getTier(){ return swordAdvancementFlag; }

    public void copyFrom(PlayerBroadsword source){
        swordLevel = source.swordLevel;
        swordExperience = source.swordExperience;
        swordAdvancementFlag = source.swordAdvancementFlag;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("swordLevel", swordLevel);
        nbt.putInt("swordExperience", swordExperience);
        nbt.putInt("swordAdvancementFlag", swordAdvancementFlag);
    }

    public void loadNBTData (CompoundTag nbt){
        swordLevel = nbt.getInt("swordLevel");
        swordExperience = nbt.getInt("swordExperience");
        swordAdvancementFlag = nbt.getInt("swordAdvancementFlag");
    }
}

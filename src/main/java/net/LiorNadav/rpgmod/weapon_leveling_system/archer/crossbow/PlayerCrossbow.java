package net.LiorNadav.rpgmod.weapon_leveling_system.archer.crossbow;

import net.LiorNadav.rpgmod.networking.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class PlayerCrossbow {
    private int crossbowLevel;
    private int crossbowExperience;
    private int crossbowAdvancementFlag; // 0,1,2
    private boolean advancementSwitch;
    private final int [] crossbowExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int [] MAX_LEVEL = {30,60,100};

    public PlayerCrossbow(){
        crossbowExperienceRequirement = new int[100];
        resetExperienceArray(crossbowExperienceRequirement);
        crossbowAdvancementFlag = 0;
        advancementSwitch = false;
    }

    private void resetExperienceArray(int[] arr){
        int amount = 10;
        arr[0] = amount;
        for(int i = 1; i < arr.length; i++){
            arr[i] = arr[i - 1] + (int) (amount * (1 + ((double) i / 10)));
        }
    }

    public void addExperience(int add){
        if(advancementSwitch){
            advancementSwitch = false;
        }
        if (crossbowLevel < MAX_LEVEL[crossbowAdvancementFlag] && add > 0) {
            if (crossbowExperience + add >= crossbowExperienceRequirement[crossbowLevel]) {
                int remainingAdd = add - (crossbowExperienceRequirement[crossbowLevel] - crossbowExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                crossbowExperience +=add;
            }
        }
    }

    public void addLevel(int level){
        if (level >= 0) {
            crossbowLevel = Math.min(crossbowLevel + level, MAX_LEVEL[crossbowAdvancementFlag]);
            if (crossbowLevel >= MAX_LEVEL[crossbowAdvancementFlag]){
                crossbowAdvancementFlag = Math.min(2, crossbowAdvancementFlag + 1);
                advancementSwitch = true;
            }
            crossbowExperience = 0;
        }
    }

    public int getCrossbowExperience() {
        return crossbowExperience;
    }

    public int getCrossbowLevel() { return crossbowLevel; }

    public int getTier() {return crossbowAdvancementFlag; }

    public int getExperienceArray(int current){
        return crossbowExperienceRequirement[current];
    }

    public void copyFrom(PlayerCrossbow source){
        crossbowLevel = source.crossbowLevel;
        crossbowExperience = source.crossbowExperience;
        crossbowAdvancementFlag = source.crossbowAdvancementFlag;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("crossbowLevel", crossbowLevel);
        nbt.putInt("crossbowExperience", crossbowExperience);
        nbt.putInt("crossbowAdvancementFlag", crossbowAdvancementFlag);
    }

    public void loadNBTData (CompoundTag nbt){
        crossbowLevel = nbt.getInt("crossbowLevel");
        crossbowExperience = nbt.getInt("crossbowExperience");
        crossbowAdvancementFlag = nbt.getInt("crossbowAdvancementFlag");
    }
}

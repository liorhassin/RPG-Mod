package net.LiorNadav.rpgmod.weapon_leveling_system.archer.bow;

import net.LiorNadav.rpgmod.networking.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class PlayerBow {
    private int bowLevel;
    private int bowExperience;
    private int bowAdvancementFlag; // 0,1,2
    private boolean advancementSwitch;
    private final int [] bowExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int [] MAX_LEVEL = {30,60,100};

    public PlayerBow(){
        bowExperienceRequirement = new int[100];
        resetExperienceArray(bowExperienceRequirement);
        bowAdvancementFlag = 0;
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
        if (bowLevel < MAX_LEVEL[bowAdvancementFlag] && add > 0) {
            if (bowExperience + add >= bowExperienceRequirement[bowLevel]) {
                int remainingAdd = add - (bowExperienceRequirement[bowLevel] - bowExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                bowExperience +=add;
            }
        }
    }

    public void addLevel(int level){
        if (level >= 0) {
            bowLevel = Math.min(bowLevel + level, MAX_LEVEL[bowAdvancementFlag]);
            if (bowLevel >= MAX_LEVEL[bowAdvancementFlag]){
                bowAdvancementFlag = Math.min(2, bowAdvancementFlag + 1);
                advancementSwitch = true;
            }
            bowExperience = 0;
        }
    }

    public int getBowExperience() {
        return bowExperience;
    }

    public int getBowLevel() { return bowLevel; }

    public int getTier() {return bowAdvancementFlag; }

    public int getExperienceArray(int current){
        return bowExperienceRequirement[current];
    }

    public void copyFrom(PlayerBow source){
        bowLevel = source.bowLevel;
        bowExperience = source.bowExperience;
        bowAdvancementFlag = source.bowAdvancementFlag;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("bowLevel", bowLevel);
        nbt.putInt("bowExperience", bowExperience);
        nbt.putInt("bowAdvancementFlag", bowAdvancementFlag);
    }

    public void loadNBTData (CompoundTag nbt){
        bowLevel = nbt.getInt("bowLevel");
        bowExperience = nbt.getInt("bowExperience");
        bowAdvancementFlag = nbt.getInt("bowAdvancementFlag");
    }
}

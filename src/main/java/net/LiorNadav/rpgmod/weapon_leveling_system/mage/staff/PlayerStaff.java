package net.LiorNadav.rpgmod.weapon_leveling_system.mage.staff;

import net.LiorNadav.rpgmod.networking.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class PlayerStaff {
    private int staffLevel;
    private int staffExperience;
    private int staffAdvancementFlag; // 0,1,2
    private boolean advancementSwitch;
    private final int [] staffExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int [] MAX_LEVEL = {30,60,100};

    public PlayerStaff(){
        staffExperienceRequirement = new int[100];
        resetExperienceArray(staffExperienceRequirement);
        staffAdvancementFlag = 0;
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
        if (staffLevel < MAX_LEVEL[staffAdvancementFlag] && add > 0) {
            if (staffExperience + add >= staffExperienceRequirement[staffLevel]) {
                int remainingAdd = add - (staffExperienceRequirement[staffLevel] - staffExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                staffExperience +=add;
            }
        }
    }

    public void addLevel(int level){
        if (level >= 0) {
            staffLevel = Math.min(staffLevel + level, MAX_LEVEL[staffAdvancementFlag]);
            if (staffLevel >= MAX_LEVEL[staffAdvancementFlag]){
                staffAdvancementFlag = Math.min(2, staffAdvancementFlag + 1);
                advancementSwitch = true;
            }
            staffExperience = 0;
        }
    }

    public int getStaffExperience() {
        return staffExperience;
    }

    public int getStaffLevel() { return staffLevel; }

    public int getTier() {return staffAdvancementFlag; }

    public int getExperienceArray(int current){
        return staffExperienceRequirement[current];
    }

    public void copyFrom(PlayerStaff source){
        staffLevel = source.staffLevel;
        staffExperience = source.staffExperience;
        staffAdvancementFlag = source.staffAdvancementFlag;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("staffLevel", staffLevel);
        nbt.putInt("staffExperience", staffExperience);
        nbt.putInt("staffAdvancementFlag", staffAdvancementFlag);
    }

    public void loadNBTData (CompoundTag nbt){
        staffLevel = nbt.getInt("staffLevel");
        staffExperience = nbt.getInt("staffExperience");
        staffAdvancementFlag = nbt.getInt("staffAdvancementFlag");
    }
}

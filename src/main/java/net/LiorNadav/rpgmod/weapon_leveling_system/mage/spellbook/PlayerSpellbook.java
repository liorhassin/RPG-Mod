package net.LiorNadav.rpgmod.weapon_leveling_system.mage.spellbook;

import net.LiorNadav.rpgmod.networking.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public class PlayerSpellbook {
    private int spellbookLevel;
    private int spellbookExperience;
    private int spellbookAdvancementFlag; // 0,1,2
    private boolean advancementSwitch;
    private final int [] spellbookExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int [] MAX_LEVEL = {30,60,100};

    public PlayerSpellbook(){
        spellbookExperienceRequirement = new int[100];
        resetExperienceArray(spellbookExperienceRequirement);
        spellbookAdvancementFlag = 0;
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
        if (spellbookLevel < MAX_LEVEL[spellbookAdvancementFlag] && add > 0) {
            if (spellbookExperience + add >= spellbookExperienceRequirement[spellbookLevel]) {
                int remainingAdd = add - (spellbookExperienceRequirement[spellbookLevel] - spellbookExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                spellbookExperience +=add;
            }
        }
    }

    public void addLevel(int level){
        if (level >= 0) {
            spellbookLevel = Math.min(spellbookLevel + level, MAX_LEVEL[spellbookAdvancementFlag]);
            if (spellbookLevel >= MAX_LEVEL[spellbookAdvancementFlag]){
                spellbookAdvancementFlag = Math.min(2, spellbookAdvancementFlag + 1);
                advancementSwitch = true;
            }
            spellbookExperience = 0;
        }
    }

    public int getSpellbookExperience() {
        return spellbookExperience;
    }

    public int getSpellbookLevel() { return spellbookLevel; }

    public int getTier() {return spellbookAdvancementFlag; }

    public int getExperienceArray(int current){
        return spellbookExperienceRequirement[current];
    }

    public void copyFrom(PlayerSpellbook source){
        spellbookLevel = source.spellbookLevel;
        spellbookExperience = source.spellbookExperience;
        spellbookAdvancementFlag = source.spellbookAdvancementFlag;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("spellbookLevel", spellbookLevel);
        nbt.putInt("spellbookExperience", spellbookExperience);
        nbt.putInt("spellbookAdvancementFlag", spellbookAdvancementFlag);
    }

    public void loadNBTData (CompoundTag nbt){
        spellbookLevel = nbt.getInt("spellbookLevel");
        spellbookExperience = nbt.getInt("spellbookExperience");
        spellbookAdvancementFlag = nbt.getInt("spellbookAdvancementFlag");
    }
}

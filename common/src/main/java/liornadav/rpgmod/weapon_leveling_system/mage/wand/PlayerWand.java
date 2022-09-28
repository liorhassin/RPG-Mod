package liornadav.rpgmod.weapon_leveling_system.mage.wand;

import net.minecraft.nbt.CompoundTag;

public class PlayerWand {
    private int wandLevel; //
    private int wandExperience; //
    private final int [] wandExperienceRequirement;
    private final int MIN_LEVEL = 0;
    private final int MAX_LEVEL = 10;

    public PlayerWand(){
        wandExperienceRequirement = new int[MAX_LEVEL];
        resetExperienceArray(wandExperienceRequirement);
    }

    private void resetExperienceArray(int[] arr){
        int amount = 10;
        arr[0] = amount;
        for(int i = 1; i < arr.length; i++){
            arr[i] = arr[i - 1] + (int) (amount * (1 + ((double) i / 10)));
        }
    }

    public void addExperience(int add){
        if (wandLevel < MAX_LEVEL && add > 0) {
            if (wandExperience + add >= wandExperienceRequirement[wandLevel]) {
                int remainingAdd = add - (wandExperienceRequirement[wandLevel] - wandExperience);
                addLevel(1);
                addExperience(remainingAdd);
            }
            else{
                wandExperience +=add;
            }
        }
    }

    public void addLevel(int level){
        if (level >= 0) {
            wandLevel = Math.min(wandLevel + level, MAX_LEVEL);
            wandExperience = 0;
        }
    }

    public void setWandLevel(int level){
        if (level <= MAX_LEVEL && level >= MIN_LEVEL) {
            wandLevel = level;
            //Optional log output with level successfully set to level.
        }
        else{
            //Log output with wrong level given, levels of knife can be set between 1-10.
        }
    }

    public void setWandExperience(int experience){
        if(experience >= 0){
            wandExperience = experience;
        }
    }

    public int getWandExperience() {
        return wandExperience;
    }

    public int getWandLevel() { return wandLevel; }

    public int getExperienceArray(int current){
        return wandExperienceRequirement[current];
    }

    public void copyFrom(PlayerWand source){
        wandLevel = source.wandLevel;
        wandExperience = source.wandExperience;
    }

    public void saveNBTData (CompoundTag nbt){
        nbt.putInt("wandLevel", wandLevel);
        nbt.putInt("wandExperience", wandExperience);
    }

    public void loadNBTData (CompoundTag nbt){
        wandLevel = nbt.getInt("wandLevel");
        wandExperience = nbt.getInt("wandExperience");
    }
}

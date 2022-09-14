package net.LiorNadav.rpgmod.event;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.networking.ModMessages;
import net.LiorNadav.rpgmod.networking.packet.BroadswordLevelC2SPacket;
import net.LiorNadav.rpgmod.networking.packet.KnifeLevelC2SPacket;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.broadsword.PlayerBroadswordProvider;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnife;
import net.LiorNadav.rpgmod.weapon_leveling_system.warrior.knife.PlayerKnifeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RPGMod.MOD_ID)
public class ModEvents {

    //------------------------------------------Knife Events----------------------------------------------
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(!event.getObject().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).isPresent()){
            event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_knife"), new PlayerKnifeProvider());
        }
        if(!event.getObject().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).isPresent()){
            event.addCapability(new ResourceLocation(RPGMod.MOD_ID, "properties_broadsword"), new PlayerBroadswordProvider());
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if (event.isWasDeath()){
            event.getOriginal().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(PlayerKnife.class);
    }
    //-----------------------------------------------------------------------------------------------------

    //---------------------------------------General Events----------------------------------------------
    @SubscribeEvent
    public static void onEnemyHit(LivingHurtEvent event){
        Entity player = event.getSource().getEntity();
        if (player instanceof Player){
            String mainItemName = ((Player) player).getMainHandItem().getItem().toString();
            switch(mainItemName){

                case "starter_knife":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        knifeExperience.addExperience((int)event.getAmount());
                        ModMessages.sendToServer(new KnifeLevelC2SPacket());
                    });
                    break;

                case "beginner_broadsword":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        if (knifeExperience.getKnifeLevel() == 10){
                            player.getCapability(PlayerBroadswordProvider.PLAYER_BROADSWORD).ifPresent(broadswordExperience -> {
                                broadswordExperience.addExperience((int)event.getAmount() * 2);
                                ModMessages.sendToServer(new BroadswordLevelC2SPacket());
                            });
                        }
                    });
                    break;
                case "apple":
                    player.getCapability(PlayerKnifeProvider.PLAYER_KNIFE).ifPresent(knifeExperience -> {
                        knifeExperience.addExperience(1000);
                        ModMessages.sendToServer(new KnifeLevelC2SPacket());
                    });
                    break;
                default:
                    break;
            }
        }
    }

}

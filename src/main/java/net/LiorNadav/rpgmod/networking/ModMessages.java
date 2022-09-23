package net.LiorNadav.rpgmod.networking;

import net.LiorNadav.rpgmod.RPGMod;
import net.LiorNadav.rpgmod.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RPGMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(KnifeLevelS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(KnifeLevelS2CPacket::new)
                .encoder(KnifeLevelS2CPacket::toByte)
                .consumerMainThread(KnifeLevelS2CPacket::handle)
                .add();

        net.messageBuilder(BroadswordLevelC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BroadswordLevelC2SPacket::new)
                .encoder(BroadswordLevelC2SPacket::toByte)
                .consumerMainThread(BroadswordLevelC2SPacket::handle)
                .add();

        net.messageBuilder(BattleAxeLevelC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BattleAxeLevelC2SPacket::new)
                .encoder(BattleAxeLevelC2SPacket::toByte)
                .consumerMainThread(BattleAxeLevelC2SPacket::handle)
                .add();

        net.messageBuilder(SlingshotLevelC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SlingshotLevelC2SPacket::new)
                .encoder(SlingshotLevelC2SPacket::toByte)
                .consumerMainThread(SlingshotLevelC2SPacket::handle)
                .add();

        net.messageBuilder(BowLevelC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BowLevelC2SPacket::new)
                .encoder(BowLevelC2SPacket::toByte)
                .consumerMainThread(BowLevelC2SPacket::handle)
                .add();

        net.messageBuilder(FluidSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FluidSyncS2CPacket::new)
                .encoder(FluidSyncS2CPacket::toByte)
                .consumerMainThread(FluidSyncS2CPacket::handle)
                .add();

        net.messageBuilder(WandLevelC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(WandLevelC2SPacket::new)
                .encoder(WandLevelC2SPacket::toByte)
                .consumerMainThread(WandLevelC2SPacket::handle)
                .add();

        net.messageBuilder(AllWeaponsLevelS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AllWeaponsLevelS2CPacket::new)
                .encoder(AllWeaponsLevelS2CPacket::toByte)
                .consumerMainThread(AllWeaponsLevelS2CPacket::handle)
                .add();

        net.messageBuilder(ManaDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ManaDataSyncS2CPacket::new)
                .encoder(ManaDataSyncS2CPacket::toByte)
                .consumerMainThread(ManaDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}

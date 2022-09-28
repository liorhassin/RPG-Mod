package liornadav.rpgmod.networking.packet;

import net.LiorNadav.rpgmod.block.entity.PurifierBlockEntity;
import net.LiorNadav.rpgmod.screen.PurifierMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidSyncS2CPacket {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public FluidSyncS2CPacket(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toByte(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            PurifierBlockEntity blockEntity = (PurifierBlockEntity) Minecraft.getInstance().level.getBlockEntity(pos);
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof PurifierBlockEntity) {
                blockEntity.setFluid(this.fluidStack);
                PurifierMenu menu = (PurifierMenu) Minecraft.getInstance().player.containerMenu;
                if(Minecraft.getInstance().player.containerMenu instanceof PurifierMenu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }
}

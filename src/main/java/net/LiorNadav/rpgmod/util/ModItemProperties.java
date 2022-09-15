package net.LiorNadav.rpgmod.util;

import net.LiorNadav.rpgmod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemProperties {
    public static void addCustomItemProperties(){
        makeSlingshot(ModItems.STARTER_SLINGSHOT.get());
        makeBow(ModItems.BEGINNER_BOW.get());
    }

    public static void makeBow(Item item){
        ItemProperties.register(Items.BOW, new ResourceLocation("pull"), (itemStack, level, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration()
                        - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });

        ItemProperties.register(Items.BOW, new ResourceLocation("pulling"), (itemStack, level, entity, i) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }

    public static void makeSlingshot(Item item){
        ItemProperties.register(Items.BOW, new ResourceLocation("pull"), (itemStack, level, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() -
                        entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });

        ItemProperties.register(Items.BOW, new ResourceLocation("pulling"), (itemStack, level, entity, i) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }
}

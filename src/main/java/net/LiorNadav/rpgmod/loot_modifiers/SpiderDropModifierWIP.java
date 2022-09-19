package net.LiorNadav.rpgmod.loot_modifiers;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.parser.Entity;

public class SpiderDropModifierWIP extends LootModifier {
    private final Entity entity;
    private final Item itemReward;
    protected SpiderDropModifierWIP(LootItemCondition[] conditionsIn, Entity entity, Item itemReward) {
        super(conditionsIn);
        this.entity = entity;
        this.itemReward = itemReward;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(this.itemReward));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {

        return null;
    }
}

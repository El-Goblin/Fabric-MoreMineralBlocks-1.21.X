package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LegendaryLongSwordItem extends LongSwordItem {
    public LegendaryLongSwordItem(Settings settings, ToolMaterial material, float attackDamage, float attackSpeed, float entityInteractionRange, float sweepingDamage) {
        super(settings, material, attackDamage, attackSpeed, entityInteractionRange, sweepingDamage);
    }

    // Con esto hago que no pierda durabilidad al atacar cosas
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        ItemEnchantmentsComponent oldEnchantments = itemStack.get(DataComponentTypes.ENCHANTMENTS);
        ItemEnchantmentsComponent oldStoredEnchantments = itemStack.get(DataComponentTypes.STORED_ENCHANTMENTS);

        ItemEnchantmentsComponent newEnchantments = itemStack.get(ModDataComponentTypes.OTHER_ENCHANTMENTS);
        ItemEnchantmentsComponent newStoredEnchantments = itemStack.get(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS);

        itemStack.set(DataComponentTypes.ENCHANTMENTS, newEnchantments);
        itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, newStoredEnchantments);

        if (newEnchantments == null) {
            itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            itemStack.set(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        }
        itemStack.set(ModDataComponentTypes.OTHER_ENCHANTMENTS, oldEnchantments);
        itemStack.set(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS, oldStoredEnchantments);

        return ActionResult.PASS;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
        return toolComponent != null;
    }
}

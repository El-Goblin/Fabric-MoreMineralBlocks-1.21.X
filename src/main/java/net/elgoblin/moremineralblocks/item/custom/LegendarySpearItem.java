package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LegendarySpearItem extends Item {
    public LegendarySpearItem(Settings settings, ToolMaterial material,
                              float swingAnimationSeconds,
                              float chargeDamageMultiplier,
                              float chargeDelaySeconds,
                              float maxDurationForDismountSeconds,
                              float minSpeedForDismount,
                              float maxDurationForChargeKnockbackInSeconds,
                              float minSpeedForChargeKnockback,
                              float maxDurationForChargeDamageInSeconds,
                              float minRelativeSpeedForChargeDamage) {
        super(settings.spear(material,
                swingAnimationSeconds,
                chargeDamageMultiplier,
                chargeDelaySeconds,
                maxDurationForDismountSeconds,
                minSpeedForDismount,
                maxDurationForChargeKnockbackInSeconds,
                minSpeedForChargeKnockback,
                maxDurationForChargeDamageInSeconds,
                minRelativeSpeedForChargeDamage));
    }

    // Con esto hago que no pierda durabilidad al atacar cosas
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
        return toolComponent != null;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos position = context.getBlockPos();
        if (!context.getWorld().isClient()) {
            if (context.getWorld().getBlockEntity(position) instanceof Inventory inventory) {
                context.getStack().set(ModDataComponentTypes.LINKED_CHEST, position);
                if (context.getPlayer() != null) {
                    context.getPlayer().sendMessage(Text.of(position.toString()), false);
                }
            }
        }
        if (context.getWorld().getBlockEntity(position) instanceof Inventory inventory) {
            return ActionResult.SUCCESS;
        }
        return super.useOnBlock(context);
    }
}

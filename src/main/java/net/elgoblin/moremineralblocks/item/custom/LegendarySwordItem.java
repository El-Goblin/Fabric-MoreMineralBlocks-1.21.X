package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LegendarySwordItem extends Item {
    public LegendarySwordItem(Settings settings, ToolMaterial material, float attackDamage, float attackSpeed) {
        super(settings.sword(material, attackDamage, attackSpeed));
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
                context.getStack().set(ModDataComponentTypes.SERVERWORLD, context.getWorld().getRegistryKey().getValue());
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

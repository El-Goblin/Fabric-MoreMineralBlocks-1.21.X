package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LegendaryPickaxeItem extends Item{
    public LegendaryPickaxeItem(Settings settings, ToolMaterial material, float attackDamage, float attackSpeed) {
        super(settings.pickaxe(material, attackDamage, attackSpeed));
    }

    // Con esto hago que no pierda durabilidad al atacar cosas
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
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

    //    @Override
//    public ActionResult use(World world, PlayerEntity user, Hand hand) {
//        ItemStack itemStack = user.getStackInHand(hand);
//
//        ItemEnchantmentsComponent oldEnchantments = itemStack.get(DataComponentTypes.ENCHANTMENTS);
//        ItemEnchantmentsComponent oldStoredEnchantments = itemStack.get(DataComponentTypes.STORED_ENCHANTMENTS);
//
//        ItemEnchantmentsComponent newEnchantments = itemStack.get(ModDataComponentTypes.OTHER_ENCHANTMENTS);
//        ItemEnchantmentsComponent newStoredEnchantments = itemStack.get(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS);
//
//        itemStack.set(DataComponentTypes.ENCHANTMENTS, newEnchantments);
//        itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, newStoredEnchantments);
//
//        if (newEnchantments == null) {
//            itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
//            itemStack.set(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
//        }
//        itemStack.set(ModDataComponentTypes.OTHER_ENCHANTMENTS, oldEnchantments);
//        itemStack.set(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS, oldStoredEnchantments);
//
//        return ActionResult.PASS;
//    }



    // Al overridear esto y sacarle la parte de
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
        return toolComponent != null;
    }
}

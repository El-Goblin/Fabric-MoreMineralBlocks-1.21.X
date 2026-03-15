package net.elgoblin.moremineralblocks.item.custom;

import com.mojang.datafixers.util.Pair;
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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class LegendaryHoeItem extends HoeItem {
    public LegendaryHoeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
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
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();

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

        Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> pair = (Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>)TILLING_ACTIONS.get(
                world.getBlockState(blockPos).getBlock()
        );
        if (pair == null) {
            return ActionResult.PASS;
        } else {
            Predicate<ItemUsageContext> predicate = pair.getFirst();
            Consumer<ItemUsageContext> consumer = pair.getSecond();
            if (predicate.test(context)) {
                PlayerEntity playerEntity = context.getPlayer();
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClient()) {
                    consumer.accept(context);
                }

                return ActionResult.SUCCESS;
            } else {
                return ActionResult.PASS;
            }
        }
    }
}

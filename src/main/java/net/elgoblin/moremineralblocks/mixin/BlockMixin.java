package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantments;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public abstract class BlockMixin {

    private static final int[][] AVAILABLE_SLOTS_CACHE = new int[54][];

    @Inject(at = @At("RETURN"), method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", cancellable = true)
    private static void carryDropsToLinkedChest(
            BlockState state,
            ServerWorld world,
            BlockPos pos,
            @Nullable BlockEntity blockEntity,
            @Nullable Entity entity,
            ItemStack tool,
            CallbackInfoReturnable<List<ItemStack>> cir) {

        if (!tool.isEmpty() && tool.isIn(ModTags.Items.LEGENDARY_TOOLS) && ModEnchantments.getLevel(tool, ModEnchantments.LINKER) > 0) {

            BlockPos storagePos = tool.get(ModDataComponentTypes.LINKED_CHEST);

            if (storagePos != null && world.isChunkLoaded(storagePos) && world.getBlockEntity(storagePos) instanceof Inventory deposit) {
                Block blockAtLinkedPosition = world.getBlockState(storagePos).getBlock();

                if (deposit instanceof ChestBlockEntity && blockAtLinkedPosition instanceof ChestBlock) {
                    deposit = ChestBlock.getInventory((ChestBlock) blockAtLinkedPosition,
                            world.getBlockState(storagePos), world, storagePos, true
                    );
                }

                List<ItemStack> toDropAsUsual = new ArrayList<>();

                if (deposit != null) {
                    for (ItemStack drop : cir.getReturnValue()) {
                        int remainder = insert(deposit, drop);

                        if (remainder > 0) {
                            toDropAsUsual.add(drop);
                        }
                    }
                    deposit.markDirty();
                    cir.setReturnValue(toDropAsUsual);
                }
            }
        }
    }

    private static int insert(Inventory deposit, ItemStack drop) {
        if (deposit == null) {
            return drop.getCount();
        }
        else {
            if (isInventoryFull(deposit)) {
                return drop.getCount();
            }
            else {
                ItemStack remainder = transfer(deposit, drop);
                if (remainder.isEmpty()) {
                    return 0;
                }

                return drop.getCount();
            }
        }
    }

    private static boolean isInventoryFull(Inventory inventory) {
        int[] is = getAvailableSlots(inventory);

        for (int i : is) {
            ItemStack itemStack = inventory.getStack(i);
            if (itemStack.getCount() < itemStack.getMaxCount()) {
                return false;
            }
        }

        return true;
    }

    private static int[] getAvailableSlots(Inventory inventory) {
        int i = inventory.size();
        if (i < AVAILABLE_SLOTS_CACHE.length) {
            int[] is = AVAILABLE_SLOTS_CACHE[i];
            if (is != null) {
                return is;
            } else {
                int[] js = indexArray(i);
                AVAILABLE_SLOTS_CACHE[i] = js;
                return js;
            }
        } else {
            return indexArray(i);
        }
    }

    private static int[] indexArray(int size) {
        int[] is = new int[size];
        int i = 0;

        while (i < is.length) {
            is[i] = i++;
        }

        return is;
    }

    private static ItemStack transfer(Inventory to, ItemStack stack) {
        int j = to.size();

        for (int i = 0; i < j && !stack.isEmpty(); i++) {
            stack = transfer2(to, stack, i);
        }
        return stack;
    }

    private static ItemStack transfer2(Inventory deposit, ItemStack stack, int slot) {
        ItemStack receivingItemStack = deposit.getStack(slot);
        if (canInsert(deposit, stack, slot)) {
            if (receivingItemStack.isEmpty()) {
                deposit.setStack(slot, stack);
                stack = ItemStack.EMPTY;
            }
            else if (canMergeItems(receivingItemStack, stack)) {
                int i = stack.getMaxCount() - receivingItemStack.getCount();
                int j = Math.min(stack.getCount(), i);
                stack.decrement(j);
                receivingItemStack.increment(j);
            }
        }

        return stack;
    }

    private static boolean canInsert(Inventory inventory, ItemStack stack, int slot) {
        return inventory.isValid(slot, stack);
    }

    private static boolean canMergeItems(ItemStack first, ItemStack second) {
        return first.getCount() <= first.getMaxCount() && ItemStack.areItemsAndComponentsEqual(first, second);
    }
}

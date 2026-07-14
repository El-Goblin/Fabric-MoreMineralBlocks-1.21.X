package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantments;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
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
            Identifier dimension = tool.get(ModDataComponentTypes.SERVERWORLD);
            MinecraftServer server = world.getServer();

            if (storagePos == null || dimension == null || server == null) {return;}

            ServerWorld targetWorld = server.getWorld(RegistryKey.of(RegistryKeys.WORLD, dimension));
            if (targetWorld == null || !targetWorld.isPosLoaded(storagePos)) {return;}

            BlockState blockAtLinkedPosition = targetWorld.getBlockState(storagePos);
            Block inventoryBlock = blockAtLinkedPosition.getBlock();
            Inventory deposit = null;

            if (inventoryBlock instanceof ChestBlock chest) {
                deposit = ChestBlock.getInventory(chest, blockAtLinkedPosition, targetWorld, storagePos, true);
            }
            if (deposit == null && targetWorld.getBlockEntity(storagePos) instanceof Inventory otherInventory) {
                deposit = otherInventory;
            }
            if (deposit == null) {return;}

            List<ItemStack> toDropAsUsual = new ArrayList<>();

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

    private static int insert(Inventory to, ItemStack stack) {
        int invSize = to.size();
        int firstEmptySpot = -1;

        for (int i = 0; i < invSize; i++) {
            ItemStack currentStack = to.getStack(i);
            if (firstEmptySpot == -1 && currentStack.isEmpty()) {
                firstEmptySpot = i;
            }
            if (canMergeItems(currentStack, stack)){
                stack = transfer(to, stack, i);
            }
        }
        if (stack.isEmpty()) {
            return 0;
        }
        if (firstEmptySpot != -1) {
            stack = transfer(to, stack, firstEmptySpot);
        }
        return stack.getCount();
    }

    private static ItemStack transfer(Inventory deposit, ItemStack stack, int slot) {
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

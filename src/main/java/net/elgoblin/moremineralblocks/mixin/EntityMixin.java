package net.elgoblin.moremineralblocks.mixin;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantments;
import net.elgoblin.moremineralblocks.util.KillerToolSaver;
import net.elgoblin.moremineralblocks.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Entity.class)
public abstract class EntityMixin implements KillerToolSaver {

    private ItemStack killerTool = ItemStack.EMPTY;
    private static final int[][] AVAILABLE_SLOTS_CACHE = new int[54][];


    @Override
    public void setKillerTool(ItemStack stack) {
        this.killerTool = stack;
    }

    @Override
    public ItemStack getKillerTool() {
        return this.killerTool;
    }

    @Inject(at = @At("RETURN"), method = "dropStack(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/entity/ItemEntity;", cancellable = true)
    private void carryDropsToLinkedChest(
            ServerWorld world,
            ItemStack stack,
            Vec3d yOffset,
            CallbackInfoReturnable<List<ItemStack>> cir) {

        if (!world.isClient()) {
            if (!((Object)this instanceof LivingEntity self)) {
                return;
            }

            ItemStack tool = this.getKillerTool();

            if (!tool.isEmpty() && tool.isIn(ModTags.Items.LEGENDARY_TOOLS) && ModEnchantments.getLevel(tool, ModEnchantments.LINKER) > 0) {
                BlockPos storagePos = tool.get(ModDataComponentTypes.LINKED_CHEST);

                if (storagePos != null && world.isChunkLoaded(storagePos) && world.getBlockEntity(storagePos) instanceof Inventory deposit) {
                    Block blockAtLinkedPosition = world.getBlockState(storagePos).getBlock();

                    if (deposit instanceof ChestBlockEntity && blockAtLinkedPosition instanceof ChestBlock) {
                        deposit = ChestBlock.getInventory((ChestBlock) blockAtLinkedPosition,
                                world.getBlockState(storagePos), world, storagePos, true
                        );
                    }

                    if (deposit != null) {
                        int remainder = insert(deposit, stack);

                        deposit.markDirty();

                        if (remainder == 0) {
                            cir.setReturnValue(null);
                        }
                    }
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
                deposit.setStack(slot, stack.copy());
                stack.setCount(0);
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

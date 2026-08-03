package net.elgoblin.moremineralblocks.util;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.enchantment.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LegendaryItemUtils {

    public static void switchEnchantmentSet(ItemStack itemStack) {
        ItemEnchantments oldEnchantmentSet = itemStack.get(DataComponents.ENCHANTMENTS);
        BlockPos oldLinkedChest = itemStack.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier oldDimension = itemStack.get(ModDataComponentTypes.SERVERWORLD);

        ItemEnchantments newEnchantmentSet = itemStack.get(ModDataComponentTypes.OTHER_ENCHANTMENTS);
        BlockPos newLinkedChest = itemStack.get(ModDataComponentTypes.OTHER_LINKED_CHEST);
        Identifier newDimension = itemStack.get(ModDataComponentTypes.OTHER_SERVERWORLD);

        itemStack.set(DataComponents.ENCHANTMENTS, newEnchantmentSet);
        itemStack.set(ModDataComponentTypes.LINKED_CHEST, newLinkedChest);
        itemStack.set(ModDataComponentTypes.SERVERWORLD, newDimension);

        itemStack.set(ModDataComponentTypes.OTHER_ENCHANTMENTS, oldEnchantmentSet);
        itemStack.set(ModDataComponentTypes.OTHER_LINKED_CHEST, oldLinkedChest);
        itemStack.set(ModDataComponentTypes.OTHER_SERVERWORLD, oldDimension);

        if (newEnchantmentSet == null) {
            itemStack.set(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        }
        if (newLinkedChest == null) {
            itemStack.remove(ModDataComponentTypes.LINKED_CHEST);
            itemStack.remove(ModDataComponentTypes.SERVERWORLD);
        }
    }

    public static void linkChest(UseOnContext context, BlockPos position) {
        if (context.getLevel().getBlockEntity(position) instanceof Container inventory) {
            ItemStack itemStack = context.getItemInHand();

            Identifier newPositionDimension = context.getLevel().dimension().identifier();

            BlockPos currentLinkedChest = itemStack.get(ModDataComponentTypes.LINKED_CHEST);
            Identifier currentDimension = itemStack.get(ModDataComponentTypes.SERVERWORLD);

            if (currentLinkedChest != null && currentLinkedChest.equals(position) && newPositionDimension.equals(currentDimension)) {
                itemStack.remove(ModDataComponentTypes.LINKED_CHEST);
                itemStack.remove(ModDataComponentTypes.SERVERWORLD);
                if (context.getPlayer() != null) {
                    context.getPlayer().sendSystemMessage(
                            Component.translatable("message.moremineralblocks.legendary_tools_unlinked_successfully")
                    );
                }
            }
            else {
                itemStack.set(ModDataComponentTypes.LINKED_CHEST, position);
                itemStack.set(ModDataComponentTypes.SERVERWORLD, context.getLevel().dimension().identifier());
                if (context.getPlayer() != null) {
                    context.getPlayer().sendSystemMessage(
                            Component.translatable("message.moremineralblocks.legendary_tools_linked_successfully"));
                }
            }
        }
    }

    public static void appendTooltip(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        Component switchEnchantments = Component.keybind("key." + MoreMineralBlocks.MOD_ID + ".switch_enchantments_toggle_safe_mode");
        builder.accept(Component.translatable("tooltip." + MoreMineralBlocks.MOD_ID + ".legendary_tool_switch_enchantments", switchEnchantments));

        if (ModEnchantments.getLevel(itemStack, ModEnchantments.LINKER) > 0) {
            builder.accept(Component.empty());

            BlockPos linkedChest = itemStack.get(ModDataComponentTypes.LINKED_CHEST);
            Identifier dimension = itemStack.get(ModDataComponentTypes.SERVERWORLD);

            if (linkedChest != null && dimension != null) {
                String translationKey = "dimension." + dimension.getNamespace() + "." + dimension.getPath();

                builder.accept(Component.translatable("tooltip." + MoreMineralBlocks.MOD_ID + ".legendary_tool_linked")
                        .withStyle(ChatFormatting.DARK_GREEN));
                builder.accept(Component.literal(String.format("X = %s | Y = %s | Z = %s",
                        linkedChest.getX(), linkedChest.getY(), linkedChest.getZ())));
                builder.accept(Component.translatableWithFallback(translationKey, dimension.toString())
                        .withStyle(ChatFormatting.LIGHT_PURPLE));
            }
            else {

                builder.accept(Component.translatable("tooltip." + MoreMineralBlocks.MOD_ID + ".legendary_tool_not_linked")
                        .withStyle(ChatFormatting.RED));
            }
            builder.accept(Component.empty());

            Component useKey = Component.keybind("key.use");
            builder.accept(Component.translatable("tooltip.moremineralblocks.legendary_tool_linking_guide", useKey));
            builder.accept(Component.empty());
        }
    }

    public static boolean isLinked(ItemStack itemStack) {
        return itemStack.has(ModDataComponentTypes.LINKED_CHEST) && itemStack.has(ModDataComponentTypes.SERVERWORLD);
    }

    public static List<ItemStack> carryDropsToLinkedContainerAndGetRemainder(ServerLevel level,
                                                                             ItemStack linkerTool,
                                                                             List<ItemStack> originalReturnValue) {

        BlockPos linkedPos = linkerTool.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier dimension = linkerTool.get(ModDataComponentTypes.SERVERWORLD);
        if (linkedPos == null || dimension == null) { return originalReturnValue; }

        MinecraftServer server = level.getServer();
        ServerLevel targetLevel = server.getLevel(ResourceKey.create(Registries.DIMENSION, dimension));
        if (targetLevel == null || !targetLevel.isLoaded(linkedPos)) { return originalReturnValue; }

        Container deposit = getContainer(targetLevel, linkedPos);
        if (deposit == null) { return originalReturnValue; }

        List<ItemStack> remainingDrops = new ArrayList<>();
        for (ItemStack drop : originalReturnValue) {
            int remainder = insert(deposit, drop);

            if (remainder > 0) {
                remainingDrops.add(drop);
            }
        }

        return remainingDrops;
    }

    public static Container getContainer(ServerLevel targetLevel, BlockPos containerPos) {
        BlockState blockState = targetLevel.getBlockState(containerPos);
        Block inventoryBlock = blockState.getBlock();
        Container deposit = null;

        if (inventoryBlock instanceof ChestBlock chest) {
            deposit = ChestBlock.getContainer(chest, blockState, targetLevel, containerPos, true);
        }
        if (deposit == null && targetLevel.getBlockEntity(containerPos) instanceof Container otherInventory) {
            deposit = otherInventory;
        }
        return deposit;
    }

    private static int insert(Container to, ItemStack stack) {
        int containerSize = to.getContainerSize();
        int firstEmptySpot = -1;

        for (int i = 0; i < containerSize; i++) {
            ItemStack stackAtPosition_i = to.getItem(i);
            if (firstEmptySpot == -1 && stackAtPosition_i.isEmpty()) {
                firstEmptySpot = i;
            }
            if (canMergeItems(stackAtPosition_i, stack)) {
                stack = transfer(to, stack, i);
            }
        }
        if (stack.isEmpty()) { return 0; }

        if (firstEmptySpot != -1) {
            stack = transfer(to, stack, firstEmptySpot);
        }
        return stack.getCount();
    }

    public static boolean canMergeItems(ItemStack first, ItemStack second) {
        return first.getCount() <= first.getMaxStackSize() && ItemStack.isSameItemSameComponents(first, second);
    }

    private static ItemStack transfer(Container container, ItemStack stack, int slot) {
        ItemStack receivingItemStack = container.getItem(slot);
        if (canInsert(container, stack, slot)) {
            if (receivingItemStack.isEmpty()) {
                container.setItem(slot, stack);
                stack = ItemStack.EMPTY;
            } else if (canMergeItems(receivingItemStack, stack)) {
                int spaceLeft = receivingItemStack.getMaxStackSize() - receivingItemStack.getCount();
                int amountToMove = Math.min(stack.getCount(), spaceLeft);
                stack.shrink(amountToMove);
                receivingItemStack.grow(amountToMove);
            }
        }

        return stack;
    }

    private static boolean canInsert(Container container, ItemStack stack, int slot) {
        return container.canPlaceItem(slot, stack);
    }
    
    public interface KillerToolSaver {
        void setKillerTool(ItemStack tool);
        
        ItemStack getKillerTool();
    }
}

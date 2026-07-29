package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.DimensionalPocketItem;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.component.ComponentType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerPayloadReceivers {

    public static void registerDimensionPocketGlobalReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(DimensionalPocketDepositContentsQueryPayload.ID,
                (payload, context) -> {
                    context.server().execute(() -> {
                        ItemStack infiniteItem = payload.itemStack();

                        DimensionalPocketItem.DimensionalPocketData dimPocketData = checkValidDimensionalPocket(infiniteItem, context.server());
                        if (dimPocketData == null) {
                            sendEmptyPayload(context);
                            return;
                        }

                        ServerWorld targetWorld = dimPocketData.targetWorld();
                        BlockPos storagePos = dimPocketData.storagePos();
                        Integer interGroupPointer = dimPocketData.interGroupPointer();
                        List<Integer> intraGroupPointers = dimPocketData.intraGroupPointers();
                        boolean safeMode = dimPocketData.safeMode();

                        if (!(dimPocketData.targetWorld().getBlockEntity(storagePos) instanceof Inventory)) {
                            sendEmptyPayload(context);
                            return;
                        }

                        Inventory deposit = getDeposit(targetWorld, storagePos);
                        if (deposit == null) {
                            sendEmptyPayload(context);
                            return;
                        }

                        StacksToShow stacksToShow = getStacksToShow(infiniteItem, deposit, interGroupPointer, intraGroupPointers.get(interGroupPointer), safeMode);
                        int nextGroupIndex = findNextNonEmptyGroup(infiniteItem, deposit, interGroupPointer, 1, safeMode);
                        int prevGroupIndex = findNextNonEmptyGroup(infiniteItem, deposit, interGroupPointer, -1, safeMode);

                        List<Integer> prevGroup = infiniteItem.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(prevGroupIndex)));
                        List<Integer> nextGroup = infiniteItem.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(nextGroupIndex)));

                        ItemStack prevGroupStack = prevGroup != null ? getStackFromInventory(deposit, prevGroup.get(intraGroupPointers.get(prevGroupIndex))) : ItemStack.EMPTY;
                        ItemStack nextGroupStack = nextGroup != null ? getStackFromInventory(deposit, nextGroup.get(intraGroupPointers.get(nextGroupIndex))) : ItemStack.EMPTY;

                        context.responseSender().sendPacket(new DimensionalPocketDepositContentsResponsePayload(
                                stacksToShow.currentStack,
                                stacksToShow.countCurrentStack,
                                stacksToShow.prevStack,
                                stacksToShow.nextStack,
                                prevGroupStack,
                                prevGroupIndex,
                                nextGroupStack,
                                nextGroupIndex
                        ));
                    });
                });

        ServerPlayNetworking.registerGlobalReceiver(DimensionalPocketMiddleClickQueryPayload.ID,
                (payload, context) -> {
                    context.server().execute(() -> {
                        ServerPlayerEntity player = context.player();
                        ItemStack infiniteItem = player.getMainHandStack();
                        if (!infiniteItem.isOf(ModItems.DIMENSIONAL_POCKET)){
                            infiniteItem = player.getOffHandStack();
                        }
                        if (!infiniteItem.isOf(ModItems.DIMENSIONAL_POCKET)) {
                            return;
                        }

                        DimensionalPocketItem.DimensionalPocketData dimPocketData = checkValidDimensionalPocket(infiniteItem, context.server());
                        if (dimPocketData == null) {
                            return;
                        }

                        ServerWorld targetWorld = dimPocketData.targetWorld();
                        BlockPos storagePos = dimPocketData.storagePos();
                        Integer interGroupPointer = dimPocketData.interGroupPointer();
                        List<Integer> intraGroupPointers = dimPocketData.intraGroupPointers();
                        boolean safeMode = dimPocketData.safeMode();

                        if (!(dimPocketData.targetWorld().getBlockEntity(storagePos) instanceof Inventory)) {
                            return;
                        }

                        Inventory deposit = getDeposit(targetWorld, storagePos);
                        if (deposit == null) {
                            return;
                        }

                        List<Integer> group = infiniteItem.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(interGroupPointer)));
                        if (group == null) {
                            return;
                        }

                        ArrayList<Integer> newIntraGroupPointers = new ArrayList<>(intraGroupPointers);

                        // Esta en el grupo en el que estoy?
                        for (int i = 0 ; i < group.size() ; i++) {
                            ItemStack currentStack = getStackFromInventory(deposit, group.get(i));
                            if (currentStack.getCount() > (safeMode ? 1 : 0) &&
                                    currentStack.getItem() == payload.clicked().asItem()) {
                                newIntraGroupPointers.set(interGroupPointer, i);
                                infiniteItem.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, newIntraGroupPointers);
                                return;
                            }
                        }

                        // Esta en algun grupo?
                        for (int offset = 0 ; offset < 16 ; offset++) {
                            int newInterGroupPointer = Math.floorMod(interGroupPointer + offset, 16);
                            group = infiniteItem.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newInterGroupPointer)));

                            if (group == null) {
                                return;
                            }

                            // Esta en el grupo en el que estoy?
                            for (int i = 0 ; i < group.size() ; i++) {
                                ItemStack currentStack = getStackFromInventory(deposit, group.get(i));
                                if (currentStack.getCount() > (safeMode ? 1 : 0) &&
                                        currentStack.getItem() == payload.clicked().asItem()) {
                                    newIntraGroupPointers.set(newInterGroupPointer, i);
                                    infiniteItem.set(ModDataComponentTypes.INTER_GROUP_POINTER, newInterGroupPointer);
                                    infiniteItem.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, newIntraGroupPointers);
                                    return;
                                }
                            }
                        }
                    });
                });

        ServerPlayNetworking.registerGlobalReceiver(DimensionalPocketToggleSlotPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                ServerPlayerEntity player = context.player();
                ScreenHandler handler = player.currentScreenHandler;

                if (handler == null) return;

                Slot targetSlot = handler.getSlot(payload.targetSlotId());
                if (targetSlot == null || targetSlot.getStack().isEmpty()) {return;}
                ItemStack itemStack = targetSlot.getStack();

                if (!itemStack.isOf(ModItems.DIMENSIONAL_POCKET)) {return;}

                DyeColor dyeColor = DyeColor.byIndex(payload.colorIndex());
                ComponentType<List<Integer>> colorComponent = ModDataComponentTypes.COLOR_INVENTORIES.get(dyeColor);

                if (colorComponent == null) {return;}

                List<Integer> existingSlots = itemStack.getOrDefault(colorComponent, List.of());
                List<Integer> updatedSlots = new ArrayList<>(existingSlots);

                int clickedSlotId = payload.clickedSlotId();

                if (updatedSlots.contains(clickedSlotId)) {
                    updatedSlots.remove(Integer.valueOf(clickedSlotId));
                } else {
                    updatedSlots.add(clickedSlotId);
                }
                itemStack.set(colorComponent, updatedSlots);
                targetSlot.markDirty();
                handler.syncState();

            });
        });

        ServerPlayNetworking.registerGlobalReceiver(DimensionalPocketSelectColorPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                ServerPlayerEntity player = context.player();
                ScreenHandler currentHandler = player.currentScreenHandler;

                if (currentHandler == null) {return;}

                int targetSlot = payload.slot();

                if (targetSlot >= 0 && targetSlot < currentHandler.slots.size()) {
                    ItemStack stack = currentHandler.getSlot(targetSlot).getStack();

                    if (stack.isOf(ModItems.DIMENSIONAL_POCKET)) {
                        stack.set(ModDataComponentTypes.SELECTED_COLOR, payload.colorIndex());
                    }
                }

            });
        });

        ServerPlayNetworking.registerGlobalReceiver(DimensionalPocketInterGroupScrollPayload.ID,
                (payload, context) -> {
                    ServerPlayerEntity player = context.player();
                    ItemStack activeHand = ItemStack.EMPTY;
                    if (player.getMainHandStack().isOf(ModItems.DIMENSIONAL_POCKET)) {
                        activeHand = player.getMainHandStack();
                    }
                    else if (player.getOffHandStack().isOf(ModItems.DIMENSIONAL_POCKET)) {
                        activeHand = player.getOffHandStack();
                    }

                    if (activeHand.isEmpty()) {
                        return;
                    }

                    DimensionalPocketItem.DimensionalPocketData dimPocketData = checkValidDimensionalPocket(activeHand, context.server());

                    if (dimPocketData == null) {
                        return;
                    }

                    ServerWorld targetWorld = dimPocketData.targetWorld();
                    BlockPos storagePos = dimPocketData.storagePos();
                    Integer interGroupPointer = dimPocketData.interGroupPointer();
                    List<Integer> intraGroupPointers = dimPocketData.intraGroupPointers();
                    boolean safeMode = dimPocketData.safeMode();

                    Inventory deposit = getDeposit(targetWorld, storagePos);
                    if (deposit == null) {
                        return;
                    }
                    int newInterGroupPointer = interGroupPointer;

                    for (int offset = 1 ; offset < 16 ; offset++) {
                        newInterGroupPointer = Math.floorMod(newInterGroupPointer + payload.scroll(), 16);
                        List<Integer> group = activeHand.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newInterGroupPointer)));

                        if (group == null || group.isEmpty()) {
                            continue;
                        }
                        int firstNonEmptySlot = isGroupEmpty(deposit, group, safeMode);
                        int oldIntraGroupPointer = intraGroupPointers.get(newInterGroupPointer);
                        int newIntraGroupPointer = firstNonEmptySlot;
                        if (group.size() > oldIntraGroupPointer) {
                            newIntraGroupPointer = (getStackFromInventory(deposit, group.get(oldIntraGroupPointer)).getCount() > (safeMode ? 1 : 0)) ? oldIntraGroupPointer : firstNonEmptySlot;
                        }


                        if (newIntraGroupPointer != -1) {
                            activeHand.set(ModDataComponentTypes.INTER_GROUP_POINTER, newInterGroupPointer);
                            List<Integer> newIntraGroupPointers = new ArrayList<>(intraGroupPointers);
                            newIntraGroupPointers.set(newInterGroupPointer, newIntraGroupPointer);
                            activeHand.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, newIntraGroupPointers);
                            return;
                        }
                    }
                });

        ServerPlayNetworking.registerGlobalReceiver(DimensionalPocketIntraGroupScrollPayload.ID,
                (payload, context) -> {
                    ServerPlayerEntity player = context.player();
                    ItemStack activeHand = ItemStack.EMPTY;
                    if (player.getMainHandStack().isOf(ModItems.DIMENSIONAL_POCKET)) {activeHand = player.getMainHandStack();}
                    else if (player.getOffHandStack().isOf(ModItems.DIMENSIONAL_POCKET)) {activeHand = player.getOffHandStack();}

                    if (activeHand.isEmpty()) {return;}

                    DimensionalPocketItem.DimensionalPocketData dimPocketData = checkValidDimensionalPocket(activeHand, context.server());

                    if (dimPocketData == null) {
                        return;
                    }

                    BlockPos storagePosition = dimPocketData.storagePos();
                    ServerWorld targetWorld = dimPocketData.targetWorld();
                    Integer interGroupPointer = dimPocketData.interGroupPointer();
                    List<Integer> intraGroupPointers = dimPocketData.intraGroupPointers();
                    boolean safeMode = dimPocketData.safeMode();


                    Inventory deposit = getDeposit(targetWorld, storagePosition);
                    if (deposit == null) {
                        return;
                    }

                    List<Integer> group = activeHand.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(interGroupPointer)));
                    if (group == null) {return;}

                    ItemStack heldStack = getStackFromInventory(deposit, group.get(intraGroupPointers.get(interGroupPointer)));

                    List<Integer> newIntraGroupPointers = new ArrayList<>(intraGroupPointers);
                    int newPointer = intraGroupPointers.get(interGroupPointer);
                    Item targetItem = payload.scroll() > 0 ? payload.prevItem().getItem() : payload.nextItem().getItem();

                    for (int offset = 1 ; offset <= group.size(); offset++) {
                        newPointer = Math.floorMod(newPointer - payload.scroll(), group.size());
                        ItemStack currentStack = getStackFromInventory(deposit, group.get(newPointer));
                        if (currentStack.getCount() > (safeMode ? 1 : 0) && currentStack.isOf(targetItem)) {
                            newIntraGroupPointers.set(interGroupPointer, newPointer);
                            activeHand.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, newIntraGroupPointers);
                            return;
                        }
                    }
                });
    }

    private static int findNextNonEmptyGroup(ItemStack infiniteItemstack, Inventory deposit, int currentGroup, int direction, boolean safeMode) {
        int newGroup = currentGroup;
        for (int i = 1; i < 16 ; i++) {
            newGroup = Math.floorMod(newGroup + direction, 16);
            List<Integer> group = infiniteItemstack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(newGroup)));

            if (group == null || group.isEmpty()) {
                continue;
            }

            for (int j = 0 ; j < group.size() ; j++) {
                ItemStack currentStack = getStackFromInventory(deposit, group.get(j));
                if (currentStack.getCount() > (safeMode ? 1 : 0)) {
                    return newGroup;
                }
            }
        }
        return currentGroup;
    }

    private static ItemStack getStackFromInventory(Inventory inventory, int index) {
        if (inventory.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int newPointer = Math.floorMod(index, inventory.size());
        return inventory.getStack(newPointer);
    }

    private static void sendEmptyPayload(ServerPlayNetworking.Context context) {
        context.responseSender().sendPacket(new DimensionalPocketDepositContentsResponsePayload(
                ItemStack.EMPTY, 0, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, 0, ItemStack.EMPTY, 0
        ));
    }

    private static int isGroupEmpty(Inventory deposit, List<Integer> group, boolean safeMode) {
        for (int i = 0 ; i < group.size() ; i++) {
            ItemStack currentStack = getStackFromInventory(deposit, group.get(i));
            if (currentStack.getCount() > (safeMode ? 1 : 0)) {
                return i; // El grupo no esta vacio y su slot i contiene algo
            }
        }
        return -1; // Ninguno de los slots del grupo contiene algo
    }

    private static DimensionalPocketItem.DimensionalPocketData checkValidDimensionalPocket(ItemStack infiniteItem, MinecraftServer server){
        if (!infiniteItem.isOf(ModItems.DIMENSIONAL_POCKET)) {
            return null;
        }
        BlockPos storagePos = infiniteItem.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier dimension = infiniteItem.get(ModDataComponentTypes.SERVERWORLD);
        List<Integer> intraGroupPointers = infiniteItem.get(ModDataComponentTypes.INTRA_GROUP_POINTERS);
        Integer interGroupPointer = infiniteItem.get(ModDataComponentTypes.INTER_GROUP_POINTER);
        Boolean safeMode = infiniteItem.getOrDefault(ModDataComponentTypes.SAFE_MODE, false);

        if (storagePos == null || dimension == null || intraGroupPointers == null || interGroupPointer == null) {;
            return null;
        }

        ServerWorld targetWorld = server.getWorld(RegistryKey.of(RegistryKeys.WORLD, dimension));
        if (targetWorld == null || !targetWorld.isPosLoaded(storagePos)) {
            return null;
        }

        return new DimensionalPocketItem.DimensionalPocketData(storagePos, dimension, targetWorld, intraGroupPointers, interGroupPointer, safeMode);
    }

    private static Inventory getDeposit(ServerWorld targetWorld, BlockPos storagePos) {
        BlockState blockState = targetWorld.getBlockState(storagePos);
        Block inventoryBlock = blockState.getBlock();
        Inventory deposit = null;

        if (inventoryBlock instanceof ChestBlock chest) {
            deposit = ChestBlock.getInventory(chest, blockState, targetWorld, storagePos, true);
        }
        if (deposit == null && targetWorld.getBlockEntity(storagePos) instanceof Inventory otherInventory) {
            deposit = otherInventory;
        }
        return deposit;
    }

    private static boolean canMergeItems(ItemStack first, ItemStack second) {
        return first.getCount() <= first.getMaxCount() && ItemStack.areItemsAndComponentsEqual(first, second);
    }

    private static StacksToShow getStacksToShow(ItemStack dimensionalPocket, Inventory deposit, int interGroupPointer, int currentIntraIndex, boolean safeMode) {
        List<Integer> group = dimensionalPocket.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(interGroupPointer)));
        if (group == null || group.isEmpty()) {
            return new StacksToShow(ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, ItemStack.EMPTY, 0);
        }
        int groupSize = group.size();
        currentIntraIndex = Math.floorMod(currentIntraIndex, groupSize);

        ItemStack mainStack = getStackFromInventory(deposit, group.get(currentIntraIndex));
        int mainStackCount = mainStack.getCount();

        ItemStack nextStack = mainStack;
        int nextStackCount = mainStackCount;

        for (int i = 1 ; i < groupSize; i++) {
            int newPointer = Math.floorMod(currentIntraIndex + i, groupSize);
            ItemStack currentStack = getStackFromInventory(deposit, group.get(newPointer));

            if (currentStack.isEmpty()) {
                continue;
            }

            if (canMergeItems(mainStack, currentStack)) {
                mainStackCount += currentStack.getCount();
                continue;
            }

            if (canMergeItems(mainStack, nextStack) && currentStack.getCount() > (safeMode ? 1 : 0)) {
                nextStack = currentStack;
                nextStackCount = currentStack.getCount();
                continue;
            }

            if (canMergeItems(nextStack, currentStack)) {
                nextStackCount += currentStack.getCount();
            }
        }

        ItemStack prevStack = mainStack;
        int prevStackCount = mainStackCount;

        for (int i = 1; i < groupSize; i++) {
            int newPointer = Math.floorMod(currentIntraIndex - i, groupSize);
            ItemStack currentStack = getStackFromInventory(deposit, group.get(newPointer));

            if (currentStack.isEmpty()) {
                continue;
            }

            if (canMergeItems(mainStack, currentStack)) {
                continue;
            }

            if (canMergeItems(mainStack, prevStack) && currentStack.getCount() > (safeMode ? 1 : 0)) {
                prevStack = currentStack;
                prevStackCount = currentStack.getCount();
                continue;
            }

            // No quiero que el proximo y el anterior sean el mismo stack
            if (canMergeItems(prevStack, nextStack) && !canMergeItems(prevStack, currentStack) && !canMergeItems(mainStack, currentStack) && currentStack.getCount() > (safeMode ? 1 :0)) {
                prevStack = currentStack;
                prevStackCount = currentStack.getCount();
                continue;
            }

            if (canMergeItems(prevStack, currentStack)) {
                prevStackCount += currentStack.getCount();
            }
        }

        return new StacksToShow(prevStack, prevStackCount, mainStack, mainStackCount, nextStack, nextStackCount);
    }

    private record StacksToShow(ItemStack prevStack, Integer countPrevStack,
                                ItemStack currentStack, Integer countCurrentStack,
                                ItemStack nextStack, Integer countNextStack) {

    }
}
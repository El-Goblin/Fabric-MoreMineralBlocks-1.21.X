package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.InstrumentComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class DimensionalPocketItem extends Item {

    public DimensionalPocketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos positionWhereUsed = context.getBlockPos();

        if (world.isClient()) {
            return ActionResult.PASS;
        }

        ItemStack infiniteItemstack = context.getStack();
        boolean safeModeOn = infiniteItemstack.getOrDefault(ModDataComponentTypes.SAFE_MODE, false);

        BlockState blockState = world.getBlockState(positionWhereUsed);
        Block usedOn = blockState.getBlock();
        Inventory inventory = null;

        if (usedOn instanceof ChestBlock chest) {
            inventory = ChestBlock.getInventory(chest, blockState, world, positionWhereUsed, true);
        }
        if (inventory == null && world.getBlockEntity(positionWhereUsed) instanceof Inventory otherInventory) {
            inventory = otherInventory;
        }

        if (!safeModeOn && inventory != null) {
            infiniteItemstack.set(ModDataComponentTypes.LINKED_CHEST, positionWhereUsed);
            infiniteItemstack.set(ModDataComponentTypes.SERVERWORLD, world.getRegistryKey().getValue());
            infiniteItemstack.set(ModDataComponentTypes.INTER_GROUP_POINTER, 0);
            infiniteItemstack.set(ModDataComponentTypes.SAFE_MODE, false);

            int startingIndex = 0;
            if (!inventory.isEmpty()){
                while (inventory.getStack(startingIndex).isEmpty()) {startingIndex++;}
            }

            ArrayList<Integer> intraGroupPointers = new ArrayList<>(Collections.nCopies(16, 0));
            intraGroupPointers.set(0,startingIndex);
            infiniteItemstack.set(ModDataComponentTypes.INTRA_GROUP_POINTERS, intraGroupPointers);

            infiniteItemstack.set(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(0)), IntStream.range(0,inventory.size()).boxed().toList());
            for (int colorIndex = 1 ; colorIndex < 16 ; colorIndex++) {
                context.getStack().set(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(colorIndex)), Collections.emptyList());
            }

            if (context.getPlayer() != null) {
                context.getPlayer().sendMessage(Text.of("Linked successfully at position (x=" + positionWhereUsed.getX() + ", y=" + positionWhereUsed.getY() + ", z=" + positionWhereUsed.getZ() +")"), false);
            }
            return ActionResult.SUCCESS;
        }

        PlayerEntity user = context.getPlayer();

        if (user == null) {
            return ActionResult.FAIL;
        }

        BlockPos storagePos = infiniteItemstack.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier dimension = infiniteItemstack.get(ModDataComponentTypes.SERVERWORLD);
        Integer interGroupPointer = infiniteItemstack.get(ModDataComponentTypes.INTER_GROUP_POINTER);
        List<Integer> intraGroupPointers = infiniteItemstack.get(ModDataComponentTypes.INTRA_GROUP_POINTERS);

        if (storagePos == null || dimension == null || interGroupPointer == null || intraGroupPointers == null) {
            return ActionResult.FAIL;
        }

        MinecraftServer server = world.getServer();
        ServerWorld targetWorld = server != null ? server.getWorld(RegistryKey.of(RegistryKeys.WORLD, dimension)) : null;

        if (targetWorld == null || !targetWorld.isPosLoaded(storagePos)) {
            return ActionResult.FAIL;
        }

        BlockState storageBlockState = targetWorld.getBlockState(storagePos);
        Block depositBlock = storageBlockState.getBlock();
        Inventory deposit = null;
        if (depositBlock instanceof ChestBlock chestDeposit) {
            deposit = ChestBlock.getInventory(chestDeposit, storageBlockState, targetWorld, storagePos, true);
        }
        if (deposit == null && world.getBlockEntity(storagePos) instanceof Inventory otherInventory) {
            deposit = otherInventory;
        }
        if (deposit == null) {
            return ActionResult.FAIL;
        }

        List<Integer> group = infiniteItemstack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(interGroupPointer)));

        if (group == null) {
            return ActionResult.FAIL;
        }
        if (group.isEmpty() || isOffBounds(intraGroupPointers.get(interGroupPointer), group.size())) {
            return ActionResult.FAIL;
        }

        ItemStack usedStack = deposit.getStack(group.get(intraGroupPointers.get(interGroupPointer)));
        if (isBannedItem(usedStack)) {
            return ActionResult.FAIL;
        }
        if (!user.isInCreativeMode()
                && safeModeOn
                && (usedStack.isDamageable() && (usedStack.getMaxDamage() - usedStack.getDamage()) <= 1
                || !usedStack.isDamageable() && usedStack.getCount() <= 1)) {

            usedStack = findNonEmptyStack(usedStack, deposit, group);
        }
        if (usedStack.isEmpty()) {
            return ActionResult.FAIL;
        }

        BlockHitResult hitResult = new BlockHitResult(context.getHitPos(), context.getSide(), context.getBlockPos(), false);
        ItemUsageContext newContext = new ItemUsageContext(context.getWorld(), context.getPlayer(), context.getHand(), usedStack, hitResult);
        ActionResult result = usedStack.useOnBlock(newContext);
        if (result == ActionResult.PASS) {
            result = use(world, user, Hand.MAIN_HAND);
        }

        if (result.isAccepted()) {
            BlockState placedState = world.getBlockState(context.getBlockPos().offset(context.getSide()));

            if (placedState.isAir()) {
                placedState = world.getBlockState(context.getBlockPos());
            }

            if (!placedState.isAir()) {
                BlockSoundGroup soundGroup = placedState.getSoundGroup();
                world.playSound(
                        null, // Player is null so EVERYONE (including the placing player) hears it
                        context.getBlockPos(),
                        soundGroup.getPlaceSound(),
                        net.minecraft.sound.SoundCategory.BLOCKS,
                        (soundGroup.getVolume() + 1.0F) / 2.0F,
                        soundGroup.getPitch() * 0.8F
                );
            }
            user.swingHand(Hand.MAIN_HAND, true);
        }

        return result;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient()) {
            return ActionResult.PASS;
        }

        ItemStack stack = user.getStackInHand(hand);
        ItemStack copy = stack.copy();

        BlockPos storagePos = stack.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier dimension = stack.get(ModDataComponentTypes.SERVERWORLD);
        Integer interGroupPointer = stack.get(ModDataComponentTypes.INTER_GROUP_POINTER);
        List<Integer> intraGroupPointers = stack.get(ModDataComponentTypes.INTRA_GROUP_POINTERS);
        boolean safeModeOn = stack.getOrDefault(ModDataComponentTypes.SAFE_MODE, false);

        if (storagePos == null || dimension == null || interGroupPointer == null || intraGroupPointers == null) {
            return ActionResult.FAIL;
        }

        MinecraftServer server = world.getServer();
        ServerWorld targetWorld = server != null ? server.getWorld(RegistryKey.of(RegistryKeys.WORLD, dimension)) : null;

        if (targetWorld == null || !targetWorld.isPosLoaded(storagePos)) {
            return ActionResult.FAIL;
        }

        BlockState storageBlockState = world.getBlockState(storagePos);
        Block depositBlock = storageBlockState.getBlock();
        Inventory deposit = ChestBlock.getInventory((ChestBlock) depositBlock, storageBlockState, world, storagePos, true);

        if (deposit == null && world.getBlockEntity(storagePos) instanceof Inventory otherInventory) {
            deposit = otherInventory;
        }
        if (deposit == null) {
            return ActionResult.FAIL;
        }

        List<Integer> group = stack.get(ModDataComponentTypes.COLOR_INVENTORIES.get(DyeColor.byIndex(interGroupPointer)));

        if (group == null) {
            return ActionResult.FAIL;
        }
        if (group.isEmpty() || isOffBounds(intraGroupPointers.get(interGroupPointer), group.size())) {
            return ActionResult.FAIL;
        }

        ItemStack usedStack = deposit.getStack(group.get(intraGroupPointers.get(interGroupPointer)));

        if (isBannedItem(usedStack)) {
            return ActionResult.FAIL;
        }
        if (!user.isInCreativeMode()
                && safeModeOn
                && (usedStack.isDamageable() && (usedStack.getMaxDamage() - usedStack.getDamage()) <= 1
                || !usedStack.isDamageable() && usedStack.getCount() <= 1)) {

            usedStack = findNonEmptyStack(usedStack, deposit, group);
        }
        if (usedStack.isEmpty()) {
            return ActionResult.FAIL;
        }

        user.equipStack(hand.getEquipmentSlot(), usedStack);
        ActionResult result = usedStack.use(world, user, hand);

        if (result.isAccepted()) {
            if (usedStack.isOf(net.minecraft.item.Items.GOAT_HORN)) {

                InstrumentComponent instrumentComponent = usedStack.get(DataComponentTypes.INSTRUMENT);
                Optional<RegistryEntry<Instrument>> optional = instrumentComponent != null ? instrumentComponent.getInstrument(user.getRegistryManager()) : Optional.empty();

                if (optional.isPresent()) {
                    Instrument instrument = (Instrument)((RegistryEntry)optional.get()).value();

                    SoundEvent soundEvent = instrument.soundEvent().value();
                    float f = instrument.range() / 16.0F;
                    world.playSoundFromEntity(null, user, soundEvent, SoundCategory.RECORDS, f, 1.0F);

                    user.getItemCooldownManager().set(usedStack, MathHelper.floor(instrument.useDuration() * 20.0F));
                }
            }

            user.swingHand(hand, true);
        }

        user.equipStack(hand.getEquipmentSlot(), copy);
        return ActionResult.FAIL;
    }

    private boolean isOffBounds(int slot, int size) {
        return slot < 0 || slot >= size;
    }

    private boolean isBannedItem(ItemStack stack) {
        if (stack.isOf(ModItems.DIMENSIONAL_POCKET)) {return true;}
        if (stack.isOf(Items.ENDER_EYE)) {return true;}
        if (stack.isOf(ModItems.FLASH)) {return true;}
        if (stack.getItem() instanceof BucketItem) {return true;}
        if (stack.isOf(Items.POWDER_SNOW_BUCKET)) {return true;}
        return stack.isEmpty();
    }

    private ItemStack findNonEmptyStack(ItemStack usedStack, Inventory deposit, List<Integer> group) {
        for (int i = 0 ; i < group.size() ; i++) {
            System.out.println(i);
            int depositPointer = Math.floorMod(group.get(i), deposit.size());
            ItemStack currentStack = deposit.getStack(depositPointer);
            if (currentStack.isOf(usedStack.getItem()) && currentStack.getCount() > 1) {
                return currentStack;
            }
        }
        return ItemStack.EMPTY;
    }

    public record DimensionalPocketData(BlockPos storagePos,
                                        Identifier dimension,
                                        ServerWorld targetWorld,
                                        List<Integer> intraGroupPointers,
                                        Integer interGroupPointer,
                                        boolean safeMode) {

    }
}
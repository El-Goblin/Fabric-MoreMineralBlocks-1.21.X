package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class InfiniteItem extends SpawnEggItem {
    public InfiniteItem(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(settings.spawnEgg(type));
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Hand hand = context.getHand();
        Hand otherHand = hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND;
        if (!hasBeenUsed(context.getStack())) {
            PlayerEntity player = context.getPlayer();
            if (player != null && !player.getStackInHand(otherHand).isEmpty() && player.getStackInHand(otherHand).getItem() != this) {
                ItemStack toStore = player.getStackInHand(otherHand).copy();
//                toStore.increment(1);
                player.getStackInHand(hand).set(ModDataComponentTypes.CHOSEN_INFINITE_ITEM, toStore);
            }
        }
        else {
            PlayerEntity player = context.getPlayer();
            if (player != null) {
                ItemStack chosenItem = player.getStackInHand(hand).get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM);
                if (chosenItem != null) {
                    ItemStack originalStack = new ItemStack(this, 1);
                    ItemStack copy = chosenItem.copy();
                    copy.setCount(2);
                    originalStack.set(ModDataComponentTypes.CHOSEN_INFINITE_ITEM, copy);

                    EquipmentSlot slot = hand.equals(Hand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

                    BlockHitResult hitResult = new BlockHitResult(context.getHitPos(), context.getSide(), context.getBlockPos(), false);
                    ItemUsageContext newContext = new ItemUsageContext(context.getWorld(), context.getPlayer(), context.getHand(), chosenItem, hitResult);
                    ActionResult result = copy.useOnBlock(newContext);
                    player.equipStack(slot, originalStack);
                    return ActionResult.PASS;
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        if (!hasBeenUsed(user.getStackInHand(hand))) {
            Hand otherHand = hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND;
            if (!user.getStackInHand(otherHand).isEmpty() && user.getStackInHand(otherHand).getItem() != this) {
                ItemStack toStore = user.getStackInHand(otherHand).copy();
//                toStore.increment(1);
                user.getStackInHand(hand).set(ModDataComponentTypes.CHOSEN_INFINITE_ITEM, toStore);
            }
        }
        else {
            ItemStack chosenItem = user.getStackInHand(hand).get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM);
            if (chosenItem != null) {
                ItemStack originalStack = new ItemStack(this, 1);
                originalStack.set(ModDataComponentTypes.CHOSEN_INFINITE_ITEM, chosenItem);

                ActionResult result = chosenItem.use(world, user, hand);
                return ActionResult.SUCCESS;
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Hand otherHand = hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND;
        if (!hasBeenUsed(user.getStackInHand(hand))) {
            if (!user.getStackInHand(otherHand).isEmpty() && user.getStackInHand(otherHand).getItem() != this) {
                ItemStack toStore = user.getStackInHand(otherHand).copy();
//                toStore.increment(1);
                user.getStackInHand(hand).set(ModDataComponentTypes.CHOSEN_INFINITE_ITEM, toStore);
            }
        }
        else {
            ItemStack chosenItem = user.getStackInHand(hand).get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM);
            if (chosenItem != null) {
                ItemStack originalStack = new ItemStack(this, 1);
                ItemStack copy = chosenItem.copy();
                copy.setCount(2);
                originalStack.set(ModDataComponentTypes.CHOSEN_INFINITE_ITEM, copy);

                EquipmentSlot slot = hand.equals(Hand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

                ActionResult result = copy.useOnEntity(user, entity, hand);
                user.equipStack(slot, originalStack);
                return ActionResult.PASS;
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    private boolean hasBeenUsed(ItemStack stack) {
        return stack.get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM) != null;
    }

    @Override
    public Optional<MobEntity> spawnBaby(PlayerEntity user, MobEntity entity, EntityType<? extends MobEntity> entityType, ServerWorld world, Vec3d pos, ItemStack stack) {
        if (hasBeenUsed(stack)) {
            ItemStack chosen = stack.get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM);
            if (chosen != null && chosen.getItem() instanceof SpawnEggItem) {
                if (!((SpawnEggItem) chosen.getItem()).isOfSameEntityType(chosen, entityType)) {
                    return Optional.empty();
                } else {
                    MobEntity mobEntity;
                    if (entity instanceof PassiveEntity) {
                        mobEntity = ((PassiveEntity)entity).createChild(world, (PassiveEntity)entity);
                    } else {
                        mobEntity = entityType.create(world, SpawnReason.BREEDING);
                    }

                    if (mobEntity == null) {
                        return Optional.empty();
                    } else {
                        mobEntity.setBaby(true);
                        if (!mobEntity.isBaby()) {
                            return Optional.empty();
                        } else {
                            mobEntity.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
                            world.spawnEntityAndPassengers(mobEntity);
                            mobEntity.setCustomName(stack.get(DataComponentTypes.CUSTOM_NAME));
                            return Optional.of(mobEntity);
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        if (stack.get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM) != null) {
            textConsumer.accept(Text.literal("Infinite stack of " + stack.get(ModDataComponentTypes.CHOSEN_INFINITE_ITEM).getName().getString()));
        }
        else {
            textConsumer.accept(Text.literal("Empty infinite stack"));
        }

        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}

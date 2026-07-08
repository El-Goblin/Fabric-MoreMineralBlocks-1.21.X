package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class InfiniteItemV2 extends Item {

    public InfiniteItemV2(Settings settings) {
        super(settings);
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

        PlayerEntity user = context.getPlayer();
        World world = context.getWorld();

        ItemStack stack = user.getMainHandStack();
        BlockPos storagePos = stack.get(ModDataComponentTypes.LINKED_CHEST);
        Inventory inventory = (Inventory) world.getBlockEntity(storagePos);
        ItemStack usedStack = inventory.getStack(0);

        BlockHitResult hitResult = new BlockHitResult(context.getHitPos(), context.getSide(), context.getBlockPos(), false);
        ItemUsageContext newContext = new ItemUsageContext(context.getWorld(), context.getPlayer(), context.getHand(), usedStack, hitResult);
        ActionResult result = usedStack.useOnBlock(newContext);

        return ActionResult.FAIL;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();
        ItemStack copy = stack.copy();
        BlockPos storagePos = stack.get(ModDataComponentTypes.LINKED_CHEST);
        Inventory inventory = (Inventory) world.getBlockEntity(storagePos);
        ItemStack usedStack = inventory.getStack(0);

        ActionResult result = usedStack.use(world, user, hand);

        if (usedStack.isStackable() && result.isAccepted()) {
            usedStack.decrementUnlessCreative(1, user);
        }

        user.equipStack(hand.getEquipmentSlot(), copy);

        return ActionResult.FAIL;
    }
}

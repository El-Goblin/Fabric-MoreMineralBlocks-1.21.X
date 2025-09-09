package net.elgoblin.moremineralblocks.item.custom;

import com.mojang.datafixers.util.Pair;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LegendarySwordItem extends SwordItem {
    public LegendarySwordItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    // Con esto hago que no pierda durabilidad al atacar cosas
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        ItemEnchantmentsComponent oldEnchantments = itemStack.get(DataComponentTypes.ENCHANTMENTS);
        ItemEnchantmentsComponent oldStoredEnchantments = itemStack.get(DataComponentTypes.STORED_ENCHANTMENTS);

        ItemEnchantmentsComponent newEnchantments = itemStack.get(ModDataComponentTypes.OTHER_ENCHANTMENTS);
        ItemEnchantmentsComponent newStoredEnchantments = itemStack.get(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS);

        itemStack.set(DataComponentTypes.ENCHANTMENTS, newEnchantments);
        itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, newStoredEnchantments);

        if (newEnchantments == null) {
            itemStack.set(DataComponentTypes.STORED_ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            itemStack.set(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        }
        itemStack.set(ModDataComponentTypes.OTHER_ENCHANTMENTS, oldEnchantments);
        itemStack.set(ModDataComponentTypes.OTHER_STORED_ENCHANTMENTS, oldStoredEnchantments);

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
        return toolComponent != null;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return false;
    }
}

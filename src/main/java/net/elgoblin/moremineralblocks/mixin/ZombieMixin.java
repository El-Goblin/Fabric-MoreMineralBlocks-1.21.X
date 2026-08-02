package net.elgoblin.moremineralblocks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Zombie.class)
public class ZombieMixin {

    @Redirect(
            method = "populateDefaultEquipmentSlots",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    private ItemStack redirectItemStack(ItemLike item) {
        // If the method was trying to create an IRON_SHOVEL, give it a chance to roll your item instead
        if (item == Items.IRON_SWORD && ((Zombie)(Object)this).getRandom().nextBoolean()) {
            return new ItemStack(ModItems.FLAMEBERGE_LONGSWORD);
        }
        return new ItemStack(item);
    }
}
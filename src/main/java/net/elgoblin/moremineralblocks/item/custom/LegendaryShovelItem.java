package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.util.LegendaryItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;

import java.util.function.Consumer;

public class LegendaryShovelItem extends ShovelItem {
    public LegendaryShovelItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(material, attackDamageBaseline, attackSpeedBaseline, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos position = context.getClickedPos();

        if (!context.getLevel().isClientSide()) {
            LegendaryItemUtils.linkOrUnlinkContainer(context, position);
        }

        if (context.getLevel().getBlockEntity(position) instanceof Container inventory) {
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        LegendaryItemUtils.appendTooltip(itemStack, builder);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}

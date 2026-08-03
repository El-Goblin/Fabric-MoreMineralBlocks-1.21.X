package net.elgoblin.moremineralblocks.networking;

import net.elgoblin.moremineralblocks.tags.ModTags;
import net.elgoblin.moremineralblocks.util.LegendaryItemUtils;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class ServerPayloadReceivers {

    public static void registerServerGlobalReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(
                SwitchEnchantmentToggleSafeModePayload.TYPE,
                (payload, context) -> {
                    context.server().execute(() -> {
                        ServerPlayer player = context.player();
                        ItemStack mainHandItem = player.getMainHandItem();
                        if (mainHandItem.is(ModTags.Items.LEGENDARY_TOOLS)) {
                            LegendaryItemUtils.switchEnchantmentSet(mainHandItem);
                        }
                        else {
                            ItemStack offHandItem = player.getOffhandItem();
                            if (offHandItem.is(ModTags.Items.LEGENDARY_TOOLS)) {
                                LegendaryItemUtils.switchEnchantmentSet(offHandItem);
                            }
                        }
                    });
                }
        );
    }
}

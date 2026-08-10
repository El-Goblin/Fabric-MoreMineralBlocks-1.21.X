package net.elgoblin.moremineralblocks.item.custom;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;

public class MagicMirrorItem extends Item {

    public MagicMirrorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);

        if (!level.isClientSide()) {
            MinecraftServer server = level.getServer();

            if (server != null && user instanceof ServerPlayer serverPlayer) {
                TeleportTransition respawnTarget = serverPlayer.findRespawnPositionAndUseSpawnBlock(true, TeleportTransition.DO_NOTHING);
                serverPlayer.teleport(respawnTarget);
                user.hurtMarked = true;
                user.fallDistance = 0;
            }
            itemStack.consume(1, user);
        }
        return super.use(level, user, hand);
    }
}
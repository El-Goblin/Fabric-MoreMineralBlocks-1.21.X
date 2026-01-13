package net.elgoblin.moremineralblocks.item.custom;

import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.List;

public class ChaosMirrorItem extends Item {

    private final Random random = Random.create();

    public ChaosMirrorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            MinecraftServer server = world.getServer();

            if (server != null) {
                Vec3d newCoordinates = new Vec3d(random.nextBetween(-29999980, 29999980),
                        user.getY(),
                        random.nextBetween(-29999980, 29999980));

                List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();

                for (ServerPlayerEntity playerEntity : players) {
                    if (playerEntity == null) {
                        continue;
                    }

                    if (playerEntity.getServer() != null) {
                        ServerWorld dimension = playerEntity.getServer().getWorld(playerEntity.getWorld().getRegistryKey());

                        if (dimension != null) {
                            Identifier world_ID = dimension.getRegistryKey().getValue();

                            Vec3d currentCoordinates = playerEntity.getPos();
                            ItemStack magicMirror = ModItems.REFLECTIVE_MIRROR.getDefaultStack();
                            magicMirror.set(ModDataComponentTypes.COORDINATES, currentCoordinates);
                            magicMirror.set(ModDataComponentTypes.SERVERWORLD, world_ID);

                            TeleportTarget teleportTarget = new TeleportTarget((ServerWorld) world,
                                    newCoordinates,
                                    new Vec3d(0, 0, 0),
                                    playerEntity.getYaw(),
                                    playerEntity.getPitch(),
                                    TeleportTarget.NO_OP);
                            playerEntity.teleportTo(teleportTarget);
                            playerEntity.giveItemStack(magicMirror);
                        }
                    }
                }
            }
        }
        itemStack.decrementUnlessCreative(1, user);
        return super.use(world, user, hand);
    }
}

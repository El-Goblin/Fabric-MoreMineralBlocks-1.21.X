package net.elgoblin.moremineralblocks.block.entity.custom;

import net.elgoblin.moremineralblocks.block.entity.ModBlockEntities;
import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ProtectorBlockEntity extends BlockEntity {
    private boolean hasBeenRegistered = false;
    ProtectorManager.Interval3i protectedArea;

    public ProtectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PROTECTOR_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);

        if (!world.isClient && !hasBeenRegistered) {
            MinecraftServer server = world.getServer();
            if (server != null) {
                ProtectorManager protectorManager = ProtectorManager.getProtectorManager(server);
                protectedArea = new ProtectorManager.Interval3i(this.pos, 64, 64, 64);

                protectorManager.addInterval(protectedArea);
                hasBeenRegistered = true;
            }
        }
    }

    @Override
    public void markRemoved() {
        if (world != null && !world.isClient && hasBeenRegistered) {
            MinecraftServer server = world.getServer();
            if (server != null) {
                ProtectorManager protectorManager = ProtectorManager.getProtectorManager(server);
                protectorManager.remove(protectedArea);
            }
        }
        super.markRemoved();
    }
}

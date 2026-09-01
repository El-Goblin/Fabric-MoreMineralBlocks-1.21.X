package net.elgoblin.umamium.block.custom;

import com.mojang.serialization.MapCodec;
import net.elgoblin.umamium.util.ProtectorManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PoweredBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ProtectorBlock extends Block {

    public static final MapCodec<ProtectorBlock> CODEC = simpleCodec(ProtectorBlock::new);

    public ProtectorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {

        if (!level.isClientSide() && !movedByPiston && !state.is(oldState.getBlock())) {
            MinecraftServer server = level.getServer();
            if (server != null) {
                ProtectorManager protectorManager = ProtectorManager.getProtectorManager(server);
                protectorManager.addProtector(pos, 64, 64, 64);
            }

        }

        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {

        if (!level.isClientSide()) {
            MinecraftServer server = level.getServer();
            ProtectorManager protectorManager = ProtectorManager.getProtectorManager(server);
            protectorManager.removeProtector(pos);
        }

        super.affectNeighborsAfterRemoval(state, level, pos, movedByPiston);
    }
}

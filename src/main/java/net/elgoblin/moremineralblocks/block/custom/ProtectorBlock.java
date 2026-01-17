package net.elgoblin.moremineralblocks.block.custom;

import com.mojang.serialization.MapCodec;
import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.block.entity.custom.ProtectorBlockEntity;
//import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class ProtectorBlock extends BlockWithEntity implements BlockEntityProvider {
    public ProtectorBlock(Settings settings) {
        super(settings);
    }

    public static final MapCodec<ProtectorBlock> CODEC = ProtectorBlock.createCodec(ProtectorBlock::new);

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ProtectorBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    //    @Override
//    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
//        if (!world.isClient()) {
//            MinecraftServer server = world.getServer();
//            if (server != null) {
//                ProtectorManager protectorManager = ProtectorManager.getProtectorManager(world.getServer());
//
//                ProtectorManager.Interval3i protectedArea = new ProtectorManager.Interval3i(pos, 64, 64, 64);
//                protectorManager.addInterval(protectedArea);
//            }
//
//        }
//
//        super.onPlaced(world, pos, state, placer, itemStack);
//    }


//    @Override
//    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//        if (blockEntity instanceof ProtectorBlockEntity protector) {
//            protector.markRemoved();
//        }
//
//        return super.onBreak(world, pos, state, player);
//    }

//    @Override
//    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
//
//        if (!world.isClient()) {
//            MinecraftServer server = world.getServer();
//            if (server != null) {
//                ProtectorManager protectorManager = ProtectorManager.getProtectorManager(world.getServer());
//
//                ProtectorManager.Interval3i protectedArea = new ProtectorManager.Interval3i(pos, 64, 64, 64);
//                protectorManager.remove(protectedArea);
//            }
//        }
//
//        super.onBroken(world, pos, state);
//    }
}

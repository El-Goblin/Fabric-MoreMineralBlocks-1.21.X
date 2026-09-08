package net.elgoblin.umamium.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DropExperienceStairBlock extends StairBlock {

    public static final MapCodec<DropExperienceStairBlock> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    IntProviders.codec(0, 10).fieldOf("experience").forGetter(b -> b.xpRange),
                    BlockState.CODEC.fieldOf("base_state").forGetter(b -> b.baseState),
                    propertiesCodec()).apply(i, DropExperienceStairBlock::new)
    );
    private final IntProvider xpRange;

    @Override
    public MapCodec<? extends DropExperienceStairBlock> codec() {
        return CODEC;
    }

    public DropExperienceStairBlock(final IntProvider xpRange, BlockState baseState, Properties properties) {
        super(baseState, properties);
        this.xpRange = xpRange;
    }

    @Override
    protected void spawnAfterBreak(final BlockState state, final ServerLevel level, final BlockPos pos, final ItemStack tool, final boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, tool, dropExperience);
        if (dropExperience) {
            this.tryDropExperience(level, pos, tool, this.xpRange);
        }
    }
}

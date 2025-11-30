package net.elgoblin.moremineralblocks.terrain;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayDeque;
import java.util.Queue;

public interface TerrainJob {
    public boolean process(int maxBlockOperations);
}
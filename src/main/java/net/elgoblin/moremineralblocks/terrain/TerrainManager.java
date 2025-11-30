package net.elgoblin.moremineralblocks.terrain;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class TerrainManager {
    private int maxBlockOperations = 50000;
    private int timer = 0;

    public static final TerrainManager TERRAIN_MANAGER = new TerrainManager();
    List<TerrainJob> listOfJobs = new ArrayList<>();

    public TerrainManager() {
    }

    public void addJob(TerrainJob newJob) {
        this.listOfJobs.add(newJob);
    }

    public void tick() {
//        timer++;
        if (!listOfJobs.isEmpty()) {
            int assignedBlockOperations = maxBlockOperations / listOfJobs.size();
            for (int i = listOfJobs.size()-1 ; i >= 0 ; i--) {
                boolean hasFinished = listOfJobs.get(i).process(assignedBlockOperations);
                if (hasFinished) { listOfJobs.remove(i);}
            }
        }
//        else {
////            if (timer % 600 == 0) {
//////                System.out.println("No tengo laburos");
////            }
//        }
    }

    public void adjustMaxOperationsPerJob(int newMaxBlockOperations) {
        maxBlockOperations = newMaxBlockOperations;
    }
}

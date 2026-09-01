package net.elgoblin.umamium.terrain;

import net.elgoblin.umamium.Umamium;
import net.elgoblin.umamium.gamerule.ChaosOrbGameRules;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class TerrainJobsManager {
    private int maxBlockOperations = 50000;
    private int timer = 0;

    public static final TerrainJobsManager TERRAIN_MANAGER = new TerrainJobsManager();
    List<TerrainJob> listOfJobs = new ArrayList<>();

    public TerrainJobsManager() {
    }

    public static void init() {
        Umamium.LOGGER.info("Starting the Terrain jobs manager for " + Umamium.MOD_ID);
    }

    public void addJob(TerrainJob newJob) {
        this.listOfJobs.add(newJob);
    }

    public void tick(MinecraftServer server) {
        if (!listOfJobs.isEmpty()) {
            maxBlockOperations = server.getGameRules().get(ChaosOrbGameRules.BLOCKS_PER_TICK);
            int assignedBlockOperations = maxBlockOperations / listOfJobs.size();
            for (int i = listOfJobs.size() - 1; i >= 0; i--) {
                boolean hasFinished = listOfJobs.get(i).process(assignedBlockOperations);
                if (hasFinished) {
                    listOfJobs.remove(i);
                }
            }
        }
    }

    public void adjustMaxOperationsPerJob(int newMaxBlockOperations) {
        maxBlockOperations = newMaxBlockOperations;
    }
}
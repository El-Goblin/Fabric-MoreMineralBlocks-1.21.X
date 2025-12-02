package net.elgoblin.moremineralblocks;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class StateSaverAndLoader extends PersistentState {

    private boolean skyBlockHappened = false;

    private StateSaverAndLoader(boolean skyBlockHappened) {
        this.skyBlockHappened = skyBlockHappened;
    }

    private static final Type<StateSaverAndLoader> type = new Type<>(
            StateSaverAndLoader::createNew,
            StateSaverAndLoader::createFromNbt,
            null
    );

    public static StateSaverAndLoader createNew() {
        return new StateSaverAndLoader();
    }

    public StateSaverAndLoader() {
        this(false);
    }

    public boolean hasSkyblockHappened() {
        return skyBlockHappened;
    }

    public void setSkyBlockHappened(boolean value) {
        skyBlockHappened = value;
        markDirty(); // important: marks the data for saving
    }

    // CODEC — describes how to read/write this state
    public static final Codec<StateSaverAndLoader> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL
                            .fieldOf("skyblock_happened")
                            .forGetter(state -> state.skyBlockHappened)
            ).apply(instance, StateSaverAndLoader::new)
    );

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putBoolean("skyblock_happened", this.skyBlockHappened);
        return nbt;
    }

    public static StateSaverAndLoader createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        return new StateSaverAndLoader(
                nbt.getBoolean("skyblock_happened")
        );
    }

    public static StateSaverAndLoader loadSave(MinecraftServer server){
        PersistentStateManager stateManager = server.getOverworld().getPersistentStateManager();
        return stateManager.getOrCreate(type, MoreMineralBlocks.MOD_ID);
    }
}

package net.elgoblin.moremineralblocks.block.entity;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.block.entity.custom.ProtectorBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<ProtectorBlockEntity> PROTECTOR_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MoreMineralBlocks.MOD_ID, "protector_block_entity"),
                    BlockEntityType.Builder.create(ProtectorBlockEntity::new, ModBlocks.PROTECTOR_BLOCK).build(null));

    public static void registerBlockEntities() {
        MoreMineralBlocks.LOGGER.info("Registering block entities for " + MoreMineralBlocks.MOD_ID);
    }
}

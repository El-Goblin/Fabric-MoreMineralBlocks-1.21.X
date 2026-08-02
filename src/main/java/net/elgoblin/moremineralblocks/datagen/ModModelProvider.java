package net.elgoblin.moremineralblocks.datagen;

import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

        // CUSTOM

        blockModelGenerators.createTrivialCube(ModBlocks.PROTECTOR_BLOCK);

        // GOLD

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.GOLD_SET);
        blockModelGenerators.createDoor(ModBlocks.GOLD_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.GOLD_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.GOLD_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_GOLD_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_GOLD_SET);

        // DIAMOND

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.DIAMOND_SET);
        blockModelGenerators.createDoor(ModBlocks.DIAMOND_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.DIAMOND_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.DIAMOND_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_DIAMOND_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_DIAMOND_SET);

        // IRON

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.IRON_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.IRON_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_IRON_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_IRON_SET);

        // EMERALD

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.EMERALD_SET);
        blockModelGenerators.createDoor(ModBlocks.EMERALD_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.EMERALD_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.EMERALD_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_EMERALD_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_EMERALD_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CHISELED_EMERALD_SET);

        // AMETHYST

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.AMETHYST_SET);
        blockModelGenerators.createDoor(ModBlocks.AMETHYST_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.AMETHYST_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.AMETHYST_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_AMETHYST_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_AMETHYST_SET);

        // LAPIS

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.LAPIS_SET);
        blockModelGenerators.createDoor(ModBlocks.LAPIS_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.LAPIS_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.LAPIS_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_LAPIS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_LAPIS_SET);

        // COAL

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.COAL_SET);
        blockModelGenerators.createDoor(ModBlocks.COAL_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.COAL_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.COAL_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_COAL_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_COAL_SET);

        // NETHERITE

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.NETHERITE_SET);
        blockModelGenerators.createDoor(ModBlocks.NETHERITE_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.NETHERITE_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.NETHERITE_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_NETHERITE_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_NETHERITE_SET);

        // REDSTONE

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.REDSTONE_SET);
        blockModelGenerators.createDoor(ModBlocks.REDSTONE_SET.door());
        blockModelGenerators.createTrapdoor(ModBlocks.REDSTONE_SET.trapdoor());

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.REDSTONE_BRICKS_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCIFIED_REDSTONE_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.POLISHED_REDSTONE_SET);

        // OTHERS

        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.ICE_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.PACKED_ICE_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.BLUE_ICE_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.OBSIDIAN_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.SCULK_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.FLINT_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.BEDROCK_SET);
        generateBlockStateModelsForSet(blockModelGenerators, ModBlocks.CALCITE_SET);

        // STAIRS

        blockModelGenerators.family(Blocks.GLASS).stairs(ModBlocks.GLASS_STAIRS).slab(ModBlocks.GLASS_SLAB);

        // WALLS

        blockModelGenerators.family(Blocks.PRISMARINE_BRICKS).wall(ModBlocks.PRISMARINE_BRICK_WALL);
        blockModelGenerators.family(Blocks.DARK_PRISMARINE).wall(ModBlocks.DARK_PRISMARINE_WALL);
        blockModelGenerators.family(Blocks.POLISHED_ANDESITE).wall(ModBlocks.POLISHED_ANDESITE_WALL);
        blockModelGenerators.family(Blocks.POLISHED_GRANITE).wall(ModBlocks.POLISHED_GRANITE_WALL);
        blockModelGenerators.family(Blocks.POLISHED_DIORITE).wall(ModBlocks.POLISHED_DIORITE_WALL);
        blockModelGenerators.family(Blocks.PURPUR_BLOCK).wall(ModBlocks.PURPUR_WALL);
//        blockModelGenerators.family(Blocks.QUARTZ_BLOCK).wall(ModBlocks.QUARTZ_WALL); // Revienta por algun motivo
        blockModelGenerators.family(Blocks.SMOOTH_QUARTZ).wall(ModBlocks.SMOOTH_QUARTZ_WALL);

        // FENCE GATES

        blockModelGenerators.family(Blocks.NETHER_BRICKS).fenceGate(ModBlocks.NETHER_BRICK_FENCE_GATE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.MOSS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLASH, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LEGENDARY_ROCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MAGIC_MIRROR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.REFLECTIVE_MIRROR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CHAOS_MIRROR, ModelTemplates.FLAT_ITEM);
    }

    private void generateBlockStateModelsForSet(BlockModelGenerators blockModelGenerators,
                                                ModBlocks.BlockSet blockSet) {
        blockModelGenerators.family(blockSet.base())
                .stairs(blockSet.stairs())
                .slab(blockSet.slab())
                .fence(blockSet.fence())
                .fenceGate(blockSet.fenceGate())
                .wall(blockSet.wall());
    }
}

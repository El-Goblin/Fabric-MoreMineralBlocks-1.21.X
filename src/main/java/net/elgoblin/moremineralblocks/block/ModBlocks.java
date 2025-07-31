package net.elgoblin.moremineralblocks.block;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    /*public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
            new Block(AbstractBlock.Settings.create().strength(3f).requiresTool()));

    public static final Block PINK_GARNET_ORE = registerBlock("pink_garnet_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2,5), AbstractBlock.Settings.create().strength(3f).requiresTool()));

    public static final Block PINK_GARNET_DEEPSLATE_ORE = registerBlock("pink_garnet_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3,6), AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup .DEEPSLATE)));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block", new MagicBlock(AbstractBlock.Settings.create().strength(1).requiresTool()));

    // ----------------------

    public static final Block PINK_GARNET_STAIRS = registerBlock("pink_garnet_stairs",
            new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(2).requiresTool()));

    public static final Block PINK_GARNET_SLAB = registerBlock("pink_garnet_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2).requiresTool()));



    public static final Block PINK_GARNET_BUTTON = registerBlock("pink_garnet_button",
            new ButtonBlock(BlockSetType.IRON, 2, AbstractBlock.Settings.create().strength(2).requiresTool().noCollision()));

    public static final Block PINK_GARNET_PRESSURE_PLATE = registerBlock("pink_garnet_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(2).requiresTool()));



    public static final Block PINK_GARNET_FENCE = registerBlock("pink_garnet_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2).requiresTool()));

    public static final Block PINK_GARNET_FENCE_GATE = registerBlock("pink_garnet_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2).requiresTool()));

    public static final Block PINK_GARNET_WALL = registerBlock("pink_garnet_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2).requiresTool()));

     */

    // GOLD BLOCKS

    public static final Block GOLD_STAIRS = registerBlock("gold_stairs",
            new StairsBlock(Blocks.GOLD_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block GOLD_SLAB = registerBlock("gold_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block GOLD_FENCE = registerBlock("gold_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block GOLD_FENCE_GATE = registerBlock("gold_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block GOLD_WALL = registerBlock("gold_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block GOLD_DOOR = registerBlock("gold_door",
            new DoorBlock(BlockSetType.GOLD, AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block GOLD_TRAPDOOR = registerBlock("gold_trapdoor",
            new TrapdoorBlock(BlockSetType.GOLD, AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().nonOpaque().sounds(BlockSoundGroup.METAL)));
    public static final Block POLISHED_GOLD_BLOCK = registerBlock("polished_gold_block",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).instrument(NoteBlockInstrument.BELL)));
    public static final Block GOLD_BLOCK = registerBlock("gold_block",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block CALCIFIED_GOLD_BLOCK = registerBlock("calcified_gold_block",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block GOLD_BRICKS = registerBlock("gold_bricks",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).instrument(NoteBlockInstrument.BELL)));

    // DIAMOND BLOCKS

    public static final Block DIAMOND_STAIRS = registerBlock("diamond_stairs",
            new StairsBlock(Blocks.DIAMOND_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_SLAB = registerBlock("diamond_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_FENCE = registerBlock("diamond_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_FENCE_GATE = registerBlock("diamond_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_WALL = registerBlock("diamond_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_DOOR = registerBlock("diamond_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().nonOpaque().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_TRAPDOOR = registerBlock("diamond_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().nonOpaque().sounds(BlockSoundGroup.METAL)));
    public static final Block POLISHED_DIAMOND_BLOCK = registerBlock("polished_diamond_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_BLOCK = registerBlock("diamond_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block CALCIFIED_DIAMOND_BLOCK = registerBlock("calcified_diamond_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block DIAMOND_BRICKS = registerBlock("diamond_bricks",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));

    // IRON BLOCKS

    public static final Block IRON_STAIRS = registerBlock("iron_stairs",
            new StairsBlock(Blocks.IRON_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_SLAB = registerBlock("iron_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_FENCE = registerBlock("iron_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_FENCE_GATE = registerBlock("iron_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_WALL = registerBlock("iron_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block POLISHED_IRON_BLOCK = registerBlock("polished_iron_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)));
    public static final Block IRON_BLOCK = registerBlock("iron_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block CALCIFIED_IRON_BLOCK = registerBlock("calcified_iron_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block IRON_BRICKS = registerBlock("iron_bricks",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)));

    // EMERALD BLOCKS

    public static final Block EMERALD_STAIRS = registerBlock("emerald_stairs",
            new StairsBlock(Blocks.EMERALD_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block EMERALD_SLAB = registerBlock("emerald_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block EMERALD_FENCE = registerBlock("emerald_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block EMERALD_FENCE_GATE = registerBlock("emerald_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block EMERALD_WALL = registerBlock("emerald_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block EMERALD_DOOR = registerBlock("emerald_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block EMERALD_TRAPDOOR = registerBlock("emerald_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block CHISELED_EMERALD_BLOCK = registerBlock("chiseled_emerald_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).instrument(NoteBlockInstrument.BIT)));
    public static final Block POLISHED_EMERALD_BLOCK = registerBlock("polished_emerald_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).instrument(NoteBlockInstrument.BIT)));
    public static final Block EMERALD_BLOCK = registerBlock("emerald_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block CALCIFIED_EMERALD_BLOCK = registerBlock("calcified_emerald_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block EMERALD_BRICKS = registerBlock("emerald_bricks",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).instrument(NoteBlockInstrument.BIT)));

    // AMETHYST BLOCKS

    public static final Block AMETHYST_STAIRS = registerBlock("amethyst_stairs",
            new StairsBlock(Blocks.AMETHYST_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block AMETHYST_SLAB = registerBlock("amethyst_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block AMETHYST_FENCE = registerBlock("amethyst_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block AMETHYST_FENCE_GATE = registerBlock("amethyst_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block AMETHYST_WALL = registerBlock("amethyst_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block AMETHYST_DOOR = registerBlock("amethyst_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK).nonOpaque()));
    public static final Block AMETHYST_TRAPDOOR = registerBlock("amethyst_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK).nonOpaque()));
    public static final Block POLISHED_AMETHYST_BLOCK = registerBlock("polished_amethyst_block",
            new Block(AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block CALCIFIED_AMETHYST_BLOCK = registerBlock("calcified_amethyst_block",
            new Block(AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block AMETHYST_BRICKS = registerBlock("amethyst_bricks",
            new Block(AbstractBlock.Settings.create().strength(1.5F).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    // LAPIS LAZULI BLOCKS

    public static final Block LAPIS_STAIRS = registerBlock("lapis_stairs",
            new StairsBlock(Blocks.LAPIS_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block LAPIS_SLAB = registerBlock("lapis_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block LAPIS_FENCE = registerBlock("lapis_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block LAPIS_FENCE_GATE = registerBlock("lapis_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block LAPIS_WALL = registerBlock("lapis_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block LAPIS_DOOR = registerBlock("lapis_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool().nonOpaque()));
    public static final Block LAPIS_TRAPDOOR = registerBlock("lapis_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool().nonOpaque()));
    public static final Block POLISHED_LAPIS_BLOCK = registerBlock("polished_lapis_block",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block LAPIS_BLOCK = registerBlock("lapis_block",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block CALCIFIED_LAPIS_BLOCK = registerBlock("calcified_lapis_block",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));
    public static final Block LAPIS_BRICKS = registerBlock("lapis_bricks",
            new Block(AbstractBlock.Settings.create().strength(3.0F, 3.0F).requiresTool()));

    // COAL BLOCKS

    public static final Block COAL_STAIRS = registerBlock("coal_stairs",
            new StairsBlock(Blocks.COAL_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool()));
    public static final Block COAL_SLAB = registerBlock("coal_slab",
            new SlabBlock(AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool()));
    public static final Block COAL_FENCE = registerBlock("coal_fence",
            new FenceBlock(AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool()));
    public static final Block COAL_FENCE_GATE = registerBlock("coal_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool()));
    public static final Block COAL_WALL = registerBlock("coal_wall",
            new WallBlock(AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool()));
    public static final Block COAL_DOOR = registerBlock("coal_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool().nonOpaque()));
    public static final Block COAL_TRAPDOOR = registerBlock("coal_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool().nonOpaque()));
    public static final Block POLISHED_COAL_BLOCK = registerBlock("polished_coal_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool().instrument(NoteBlockInstrument.BASEDRUM)));
    public static final Block CALCIFIED_COAL_BLOCK = registerBlock("calcified_coal_block",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool()));
    public static final Block COAL_BRICKS = registerBlock("coal_bricks",
            new Block(AbstractBlock.Settings.create().requiresTool().strength(5.0F, 6.0F).requiresTool().instrument(NoteBlockInstrument.BASEDRUM)));

    // CALCITE BLOCKS

    public static final Block CALCITE_STAIRS = registerBlock("calcite_stairs",
            new StairsBlock(Blocks.CALCITE.getDefaultState(),
                    AbstractBlock.Settings.create().strength(0.75F).requiresTool().sounds(BlockSoundGroup.CALCITE)));
    public static final Block CALCITE_SLAB = registerBlock("calcite_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(0.75F).requiresTool().sounds(BlockSoundGroup.CALCITE)));
    public static final Block CALCITE_FENCE = registerBlock("calcite_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(0.75F).requiresTool().sounds(BlockSoundGroup.CALCITE)));
    public static final Block CALCITE_FENCE_GATE = registerBlock("calcite_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(0.75F).requiresTool().sounds(BlockSoundGroup.CALCITE)));
    public static final Block CALCITE_WALL = registerBlock("calcite_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(0.75F).requiresTool().sounds(BlockSoundGroup.CALCITE)));

    // NETHERITE BLOCKS

    public static final Block NETHERITE_STAIRS = registerBlock("netherite_stairs",
            new StairsBlock(Blocks.NETHERITE_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));
    public static final Block NETHERITE_SLAB = registerBlock("netherite_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));
    public static final Block NETHERITE_FENCE = registerBlock("netherite_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));
    public static final Block NETHERITE_FENCE_GATE = registerBlock("netherite_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));
    public static final Block NETHERITE_WALL = registerBlock("netherite_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));
    public static final Block NETHERITE_DOOR = registerBlock("netherite_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE).nonOpaque()));
    public static final Block NETHERITE_TRAPDOOR = registerBlock("netherite_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE).nonOpaque()));
    public static final Block POLISHED_NETHERITE_BLOCK = registerBlock("polished_netherite_block",
            new Block(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));
    public static final Block CALCIFIED_NETHERITE_BLOCK = registerBlock("calcified_netherite_block",
            new Block(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));
    public static final Block NETHERITE_BRICKS = registerBlock("netherite_bricks",
            new Block(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool().sounds(BlockSoundGroup.NETHERITE)));

    // REDSTONE

    public static final Block REDSTONE_STAIRS = registerBlock("redstone_stairs",
            new StairsBlock(Blocks.REDSTONE_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block REDSTONE_SLAB = registerBlock("redstone_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block REDSTONE_FENCE = registerBlock("redstone_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block REDSTONE_FENCE_GATE = registerBlock("redstone_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block REDSTONE_WALL = registerBlock("redstone_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block REDSTONE_DOOR = registerBlock("redstone_door",
            new DoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block REDSTONE_TRAPDOOR = registerBlock("redstone_trapdoor",
            new TrapdoorBlock(BlockSetType.ACACIA, AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block POLISHED_REDSTONE_BLOCK = registerBlock("polished_redstone_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block REDSTONE_BLOCK = registerBlock("redstone_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block CALCIFIED_REDSTONE_BLOCK = registerBlock("calcified_redstone_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));
    public static final Block REDSTONE_BRICKS = registerBlock("redstone_bricks",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));

    // OBSIDIAN

    public static final Block OBSIDIAN_STAIRS = registerBlock("obsidian_stairs",
            new StairsBlock(Blocks.OBSIDIAN.getDefaultState(),
                    AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool()));
    public static final Block OBSIDIAN_SLAB = registerBlock("obsidian_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool()));
    public static final Block OBSIDIAN_FENCE = registerBlock("obsidian_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool()));
    public static final Block OBSIDIAN_FENCE_GATE = registerBlock("obsidian_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool()));
    public static final Block OBSIDIAN_WALL = registerBlock("obsidian_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).requiresTool()));

    // BEDROCK

    public static final Block BEDROCK_STAIRS = registerBlock("bedrock_stairs",
            new StairsBlock(Blocks.BEDROCK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(-1F).resistance(3600000F).dropsNothing().allowsSpawning(Blocks::never)));
    public static final Block BEDROCK_SLAB = registerBlock("bedrock_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(-1F).resistance(3600000F).dropsNothing().allowsSpawning(Blocks::never)));
    public static final Block BEDROCK_FENCE = registerBlock("bedrock_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(-1F).resistance(3600000F).dropsNothing().allowsSpawning(Blocks::never)));
    public static final Block BEDROCK_FENCE_GATE = registerBlock("bedrock_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(-1F).resistance(3600000F).dropsNothing().allowsSpawning(Blocks::never)));
    public static final Block BEDROCK_WALL = registerBlock("bedrock_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(-1F).resistance(3600000F).dropsNothing().allowsSpawning(Blocks::never)));

    // SCULK

    //TODO add drop table con silk touch unicamente
    public static final Block SCULK_STAIRS = registerBlock("sculk_stairs",
            new StairsBlock(Blocks.SCULK.getDefaultState(),
                    AbstractBlock.Settings.create().strength(0.2F).sounds(BlockSoundGroup.SCULK)));
    public static final Block SCULK_SLAB = registerBlock("sculk_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(0.2F).sounds(BlockSoundGroup.SCULK)));
    public static final Block SCULK_FENCE = registerBlock("sculk_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(0.2F).sounds(BlockSoundGroup.SCULK)));
    public static final Block SCULK_FENCE_GATE = registerBlock("sculk_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(0.2F).sounds(BlockSoundGroup.SCULK)));
    public static final Block SCULK_WALL = registerBlock("sculk_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(0.2F).sounds(BlockSoundGroup.SCULK)));

    // PACKED_ICE

    public static final Block PACKED_ICE_STAIRS = registerBlock("packed_ice_stairs",
            new StairsBlock(Blocks.PACKED_ICE.getDefaultState(), AbstractBlock.Settings.create().instrument(NoteBlockInstrument.CHIME).slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block PACKED_ICE_SLAB = registerBlock("packed_ice_slab",
            new SlabBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.CHIME).slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block PACKED_ICE_FENCE = registerBlock("packed_ice_fence",
            new FenceBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.CHIME).slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block PACKED_ICE_FENCE_GATE = registerBlock("packed_ice_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.CHIME).slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block PACKED_ICE_WALL = registerBlock("packed_ice_wall",
            new WallBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.CHIME).slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));

    // BLUE_ICE

    public static final Block BLUE_ICE_STAIRS = registerBlock("blue_ice_stairs",
            new StairsBlock(Blocks.BLUE_ICE.getDefaultState(),
                    AbstractBlock.Settings.create().strength(2.8F).slipperiness(0.989F).sounds(BlockSoundGroup.GLASS)));
    public static final Block BLUE_ICE_SLAB = registerBlock("blue_ice_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2.8F).slipperiness(0.989F).sounds(BlockSoundGroup.GLASS)));
    public static final Block BLUE_ICE_FENCE = registerBlock("blue_ice_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2.8F).slipperiness(0.989F).sounds(BlockSoundGroup.GLASS)));
    public static final Block BLUE_ICE_FENCE_GATE = registerBlock("blue_ice_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2.8F).slipperiness(0.989F).sounds(BlockSoundGroup.GLASS)));
    public static final Block BLUE_ICE_WALL = registerBlock("blue_ice_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2.8F).slipperiness(0.989F).sounds(BlockSoundGroup.GLASS)));

    // ICE

    public static final Block ICE_STAIRS = registerBlock("ice_stairs",
            new StairsBlock(Blocks.ICE.getDefaultState(),
                    AbstractBlock.Settings.create().slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block ICE_SLAB = registerBlock("ice_slab",
            new SlabBlock(AbstractBlock.Settings.create().slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block ICE_FENCE = registerBlock("ice_fence",
            new FenceBlock(AbstractBlock.Settings.create().slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block ICE_FENCE_GATE = registerBlock("ice_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));
    public static final Block ICE_WALL = registerBlock("ice_wall",
            new WallBlock(AbstractBlock.Settings.create().slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS)));

    // DEEPSLATE_EMERALD_ORE

    public static final Block DEEPSLATE_EMERALD_ORE_STAIRS = registerBlock("deepslate_emerald_ore_stairs",
            new StairsBlock(Blocks.DEEPSLATE_EMERALD_ORE.getDefaultState(),
                    AbstractBlock.Settings.create().strength(2).requiresTool()));
    public static final Block DEEPSLATE_EMERALD_ORE_SLAB = registerBlock("deepslate_emerald_ore_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(2).requiresTool()));
    public static final Block DEEPSLATE_EMERALD_ORE_FENCE = registerBlock("deepslate_emerald_ore_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(2).requiresTool()));
    public static final Block DEEPSLATE_EMERALD_ORE_FENCE_GATE = registerBlock("deepslate_emerald_ore_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create().strength(2).requiresTool()));
    public static final Block DEEPSLATE_EMERALD_ORE_WALL = registerBlock("deepslate_emerald_ore_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(2).requiresTool()));

    // PRISMARINE

    public static final Block PRISMARINE_BRICK_WALL = registerBlock("prismarine_brick_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F).requiresTool()));
    public static final Block DARK_PRISMARINE_WALL = registerBlock("dark_prismarine_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(1.5F, 6.0F).requiresTool()));


    // Otra funcion de ayuda para registrar bloques nuevos
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MoreMineralBlocks.MOD_ID, name), block);
    }

    // Funcion de ayuda para registrar bloques nuevos
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MoreMineralBlocks.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MoreMineralBlocks.LOGGER.info("Registering Mod Blocks for" + MoreMineralBlocks.MOD_ID);

        // Para agregar bloques a una tab existente de minecraft vanilla en creativo
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            //entries.add(ModBlocks.PINK_GARNET_BLOCK);
            //entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);

            entries.add(ModBlocks.GOLD_STAIRS);
            entries.add(ModBlocks.GOLD_SLAB);
            entries.add(ModBlocks.GOLD_FENCE);
            entries.add(ModBlocks.GOLD_FENCE_GATE);
            entries.add(ModBlocks.GOLD_WALL);
            entries.add(ModBlocks.GOLD_DOOR);
            entries.add(ModBlocks.GOLD_TRAPDOOR);
            entries.add(ModBlocks.POLISHED_GOLD_BLOCK);
            entries.add(ModBlocks.CALCIFIED_GOLD_BLOCK);
            entries.add(ModBlocks.GOLD_BRICKS);
            entries.add(ModBlocks.GOLD_BLOCK);

            entries.add(ModBlocks.DIAMOND_STAIRS);
            entries.add(ModBlocks.DIAMOND_SLAB);
            entries.add(ModBlocks.DIAMOND_FENCE);
            entries.add(ModBlocks.DIAMOND_FENCE_GATE);
            entries.add(ModBlocks.DIAMOND_WALL);
            entries.add(ModBlocks.DIAMOND_DOOR);
            entries.add(ModBlocks.DIAMOND_TRAPDOOR);
            entries.add(ModBlocks.POLISHED_DIAMOND_BLOCK);
            entries.add(ModBlocks.CALCIFIED_DIAMOND_BLOCK);
            entries.add(ModBlocks.DIAMOND_BRICKS);
            entries.add(ModBlocks.DIAMOND_BLOCK);

            entries.add(ModBlocks.IRON_STAIRS);
            entries.add(ModBlocks.IRON_SLAB);
            entries.add(ModBlocks.IRON_FENCE);
            entries.add(ModBlocks.IRON_FENCE_GATE);
            entries.add(ModBlocks.IRON_WALL);
            entries.add(ModBlocks.POLISHED_IRON_BLOCK);
            entries.add(ModBlocks.CALCIFIED_IRON_BLOCK);
            entries.add(ModBlocks.IRON_BRICKS);
            entries.add(ModBlocks.IRON_BLOCK);

            entries.add(ModBlocks.EMERALD_STAIRS);
            entries.add(ModBlocks.EMERALD_SLAB);
            entries.add(ModBlocks.EMERALD_FENCE);
            entries.add(ModBlocks.EMERALD_FENCE_GATE);
            entries.add(ModBlocks.EMERALD_WALL);
            entries.add(ModBlocks.EMERALD_DOOR);
            entries.add(ModBlocks.EMERALD_TRAPDOOR);
            entries.add(ModBlocks.CHISELED_EMERALD_BLOCK);
            entries.add(ModBlocks.POLISHED_EMERALD_BLOCK);
            entries.add(ModBlocks.CALCIFIED_EMERALD_BLOCK);
            entries.add(ModBlocks.EMERALD_BRICKS);
            entries.add(ModBlocks.EMERALD_BLOCK);

            entries.add(ModBlocks.LAPIS_STAIRS);
            entries.add(ModBlocks.LAPIS_SLAB);
            entries.add(ModBlocks.LAPIS_FENCE);
            entries.add(ModBlocks.LAPIS_FENCE_GATE);
            entries.add(ModBlocks.LAPIS_WALL);
            entries.add(ModBlocks.LAPIS_DOOR);
            entries.add(ModBlocks.LAPIS_TRAPDOOR);
            entries.add(ModBlocks.POLISHED_LAPIS_BLOCK);
            entries.add(ModBlocks.CALCIFIED_LAPIS_BLOCK);
            entries.add(ModBlocks.LAPIS_BRICKS);
            entries.add(ModBlocks.LAPIS_BLOCK);

            entries.add(ModBlocks.COAL_STAIRS);
            entries.add(ModBlocks.COAL_SLAB);
            entries.add(ModBlocks.COAL_FENCE);
            entries.add(ModBlocks.COAL_FENCE_GATE);
            entries.add(ModBlocks.COAL_WALL);
            entries.add(ModBlocks.COAL_DOOR);
            entries.add(ModBlocks.COAL_TRAPDOOR);
            entries.add(ModBlocks.POLISHED_COAL_BLOCK);
            entries.add(ModBlocks.CALCIFIED_COAL_BLOCK);
            entries.add(ModBlocks.COAL_BRICKS);

            entries.add(ModBlocks.CALCITE_STAIRS);
            entries.add(ModBlocks.CALCITE_SLAB);
            entries.add(ModBlocks.CALCITE_FENCE);
            entries.add(ModBlocks.CALCITE_FENCE_GATE);
            entries.add(ModBlocks.CALCITE_WALL);

            entries.add(ModBlocks.NETHERITE_STAIRS);
            entries.add(ModBlocks.NETHERITE_SLAB);
            entries.add(ModBlocks.NETHERITE_FENCE);
            entries.add(ModBlocks.NETHERITE_FENCE_GATE);
            entries.add(ModBlocks.NETHERITE_WALL);
            entries.add(ModBlocks.NETHERITE_DOOR);
            entries.add(ModBlocks.NETHERITE_TRAPDOOR);
            entries.add(ModBlocks.POLISHED_NETHERITE_BLOCK);
            entries.add(ModBlocks.CALCIFIED_NETHERITE_BLOCK);
            entries.add(ModBlocks.NETHERITE_BRICKS);

            entries.add(ModBlocks.AMETHYST_STAIRS);
            entries.add(ModBlocks.AMETHYST_SLAB);
            entries.add(ModBlocks.AMETHYST_FENCE);
            entries.add(ModBlocks.AMETHYST_FENCE_GATE);
            entries.add(ModBlocks.AMETHYST_WALL);
            entries.add(ModBlocks.AMETHYST_DOOR);
            entries.add(ModBlocks.AMETHYST_TRAPDOOR);
            entries.add(ModBlocks.POLISHED_AMETHYST_BLOCK);
            entries.add(ModBlocks.CALCIFIED_AMETHYST_BLOCK);
            entries.add(ModBlocks.AMETHYST_BRICKS);

            entries.add(ModBlocks.REDSTONE_STAIRS);
            entries.add(ModBlocks.REDSTONE_SLAB);
            entries.add(ModBlocks.REDSTONE_FENCE);
            entries.add(ModBlocks.REDSTONE_FENCE_GATE);
            entries.add(ModBlocks.REDSTONE_WALL);
            entries.add(ModBlocks.REDSTONE_DOOR);
            entries.add(ModBlocks.REDSTONE_TRAPDOOR);
            entries.add(ModBlocks.POLISHED_REDSTONE_BLOCK);
            entries.add(ModBlocks.REDSTONE_BLOCK);
            entries.add(ModBlocks.CALCIFIED_REDSTONE_BLOCK);
            entries.add(ModBlocks.REDSTONE_BRICKS);

            entries.add(ModBlocks.OBSIDIAN_STAIRS);
            entries.add(ModBlocks.OBSIDIAN_SLAB);
            entries.add(ModBlocks.OBSIDIAN_FENCE);
            entries.add(ModBlocks.OBSIDIAN_FENCE_GATE);
            entries.add(ModBlocks.OBSIDIAN_WALL);

            entries.add(ModBlocks.BEDROCK_STAIRS);
            entries.add(ModBlocks.BEDROCK_SLAB);
            entries.add(ModBlocks.BEDROCK_FENCE);
            entries.add(ModBlocks.BEDROCK_FENCE_GATE);
            entries.add(ModBlocks.BEDROCK_WALL);

            entries.add(ModBlocks.SCULK_STAIRS);
            entries.add(ModBlocks.SCULK_SLAB);
            entries.add(ModBlocks.SCULK_FENCE);
            entries.add(ModBlocks.SCULK_FENCE_GATE);
            entries.add(ModBlocks.SCULK_WALL);

            entries.add(ModBlocks.PACKED_ICE_STAIRS);
            entries.add(ModBlocks.PACKED_ICE_SLAB);
            entries.add(ModBlocks.PACKED_ICE_FENCE);
            entries.add(ModBlocks.PACKED_ICE_FENCE_GATE);
            entries.add(ModBlocks.PACKED_ICE_WALL);

            entries.add(ModBlocks.BLUE_ICE_STAIRS);
            entries.add(ModBlocks.BLUE_ICE_SLAB);
            entries.add(ModBlocks.BLUE_ICE_FENCE);
            entries.add(ModBlocks.BLUE_ICE_FENCE_GATE);
            entries.add(ModBlocks.BLUE_ICE_WALL);

            entries.add(ModBlocks.ICE_STAIRS);
            entries.add(ModBlocks.ICE_SLAB);
            entries.add(ModBlocks.ICE_FENCE);
            entries.add(ModBlocks.ICE_FENCE_GATE);
            entries.add(ModBlocks.ICE_WALL);

            entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_STAIRS);
            entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_SLAB);
            entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE);
            entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_FENCE_GATE);
            entries.add(ModBlocks.DEEPSLATE_EMERALD_ORE_WALL);

            entries.add(ModBlocks.PRISMARINE_BRICK_WALL);
            entries.add(ModBlocks.DARK_PRISMARINE_WALL);
        });
    }
}

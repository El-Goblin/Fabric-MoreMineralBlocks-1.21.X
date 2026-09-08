package net.elgoblin.umamium.block;

import net.elgoblin.umamium.Umamium;
import net.elgoblin.umamium.block.custom.*;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {

    public record BlockSet(
            Block base,
            Block stairs,
            Block slab,
            Block fence,
            Block fenceGate,
            Block wall,
            Block door,
            Block trapdoor
    ) {}

    // GOLD BLOCKS

    public static final BlockSet GOLD_SET = registerBlockSet(
            "gold",
            Blocks.GOLD_BLOCK,
            BlockSetType.GOLD,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .strength(3.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            true,
            true
    );

    public static final Block GOLD_BRICKS = registerBlock("gold_bricks", properties -> new Block(properties
            .mapColor(MapColor.GOLD)
            .instrument(NoteBlockInstrument.BELL)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet GOLD_BRICKS_SET = registerBlockSet(
            "gold_brick",
            ModBlocks.GOLD_BRICKS,
            BlockSetType.GOLD,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .strength(3.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block POLISHED_GOLD_BLOCK = registerBlock("polished_gold_block", properties -> new Block(properties
            .mapColor(MapColor.GOLD)
            .instrument(NoteBlockInstrument.BELL)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet POLISHED_GOLD_SET = registerBlockSet(
            "polished_gold",
            ModBlocks.POLISHED_GOLD_BLOCK,
            BlockSetType.GOLD,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .strength(3.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block CALCIFIED_GOLD_BLOCK = registerBlock("calcified_gold_block", properties -> new Block(properties
            .mapColor(MapColor.GOLD)
            .instrument(NoteBlockInstrument.BELL)
            .requiresCorrectToolForDrops()
            .strength(2.0F, 4.0F)
            .sound(SoundType.CALCITE)
    ));

    public static final BlockSet CALCIFIED_GOLD_SET = registerBlockSet(
            "calcified_gold",
            ModBlocks.CALCIFIED_GOLD_BLOCK,
            BlockSetType.GOLD,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .strength(2.0F, 4.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE),
            false,
            false
    );

    // DIAMOND BLOCKS

    public static final BlockSet DIAMOND_SET = registerBlockSet(
            "diamond",
            Blocks.DIAMOND_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DIAMOND)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            true,
            true
    );

    public static final Block DIAMOND_BRICKS = registerBlock("diamond_bricks", properties -> new Block(properties
            .mapColor(MapColor.DIAMOND)
            .instrument(NoteBlockInstrument.CHIME)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet DIAMOND_BRICKS_SET = registerBlockSet(
            "diamond_brick",
            ModBlocks.DIAMOND_BRICKS,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DIAMOND)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block POLISHED_DIAMOND_BLOCK = registerBlock("polished_diamond_block", properties -> new Block(properties
            .mapColor(MapColor.DIAMOND)
            .instrument(NoteBlockInstrument.CHIME)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet POLISHED_DIAMOND_SET = registerBlockSet(
            "polished_diamond",
            ModBlocks.POLISHED_DIAMOND_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DIAMOND)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block CALCIFIED_DIAMOND_BLOCK = registerBlock("calcified_diamond_block", properties -> new Block(properties
            .mapColor(MapColor.DIAMOND)
            .instrument(NoteBlockInstrument.CHIME)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 4.0F)
            .sound(SoundType.CALCITE)
    ));

    public static final BlockSet CALCIFIED_DIAMOND_SET = registerBlockSet(
            "calcified_diamond",
            ModBlocks.CALCIFIED_DIAMOND_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DIAMOND)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(3.0F, 4.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE),
            false,
            false
    );

    // IRON BLOCKS

    public static final BlockSet IRON_SET = registerBlockSet(
            "iron",
            Blocks.IRON_BLOCK,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block IRON_BRICKS = registerBlock("iron_bricks", properties -> new Block(properties
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet IRON_BRICKS_SET = registerBlockSet(
            "iron_brick",
            ModBlocks.IRON_BRICKS,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block POLISHED_IRON_BLOCK = registerBlock("polished_iron_block", properties -> new Block(properties
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet POLISHED_IRON_SET = registerBlockSet(
            "polished_iron",
            ModBlocks.POLISHED_IRON_BLOCK,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block CALCIFIED_IRON_BLOCK = registerBlock("calcified_iron_block", properties -> new Block(properties
            .mapColor(MapColor.METAL)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 4.0F)
            .sound(SoundType.CALCITE)
    ));

    public static final BlockSet CALCIFIED_IRON_SET = registerBlockSet(
            "calcified_iron",
            ModBlocks.CALCIFIED_IRON_BLOCK,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .strength(3.0F, 4.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE),
            false,
            false
    );

    // EMERALD

    public static final BlockSet EMERALD_SET = registerBlockSet(
            "emerald",
            Blocks.EMERALD_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            true,
            true
    );

    public static final Block EMERALD_BRICKS = registerBlock("emerald_bricks", properties -> new Block(properties
            .mapColor(MapColor.EMERALD)
            .instrument(NoteBlockInstrument.BIT)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet EMERALD_BRICKS_SET = registerBlockSet(
            "emerald_brick",
            ModBlocks.EMERALD_BRICKS,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block POLISHED_EMERALD_BLOCK = registerBlock("polished_emerald_block", properties -> new Block(properties
            .mapColor(MapColor.EMERALD)
            .instrument(NoteBlockInstrument.BIT)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet POLISHED_EMERALD_SET = registerBlockSet(
            "polished_emerald",
            ModBlocks.POLISHED_EMERALD_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block CALCIFIED_EMERALD_BLOCK = registerBlock("calcified_emerald_block", properties -> new Block(properties
            .mapColor(MapColor.EMERALD)
            .instrument(NoteBlockInstrument.BIT)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 4.0F)
            .sound(SoundType.CALCITE)
    ));

    public static final BlockSet CALCIFIED_EMERALD_SET = registerBlockSet(
            "calcified_emerald",
            ModBlocks.CALCIFIED_EMERALD_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(3.0F, 4.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE),
            false,
            false
    );

    public static final Block CHISELED_EMERALD_BLOCK = registerBlock("chiseled_emerald_block", properties -> new Block(properties
            .mapColor(MapColor.EMERALD)
            .instrument(NoteBlockInstrument.BIT)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet CHISELED_EMERALD_SET = registerBlockSet(
            "chiseled_emerald",
            ModBlocks.CHISELED_EMERALD_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    // AMETHYST

    public static final BlockSet AMETHYST_SET = registerBlockSet(
            "amethyst",
            Blocks.AMETHYST_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(1.5F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST),
            true,
            true
    );

    public static final Block AMETHYST_BRICKS = registerBlock("amethyst_bricks", properties -> new Block(properties
            .mapColor(MapColor.COLOR_PURPLE)
            .instrument(NoteBlockInstrument.BIT)
            .requiresCorrectToolForDrops()
            .strength(1.5F)
            .sound(SoundType.AMETHYST)
    ));

    public static final BlockSet AMETHYST_BRICKS_SET = registerBlockSet(
            "amethyst_brick",
            ModBlocks.AMETHYST_BRICKS,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(1.5F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST),
            false,
            false
    );

    public static final Block POLISHED_AMETHYST_BLOCK = registerBlock("polished_amethyst_block", properties -> new Block(properties
            .mapColor(MapColor.COLOR_PURPLE)
            .instrument(NoteBlockInstrument.BIT)
            .requiresCorrectToolForDrops()
            .strength(1.5F)
            .sound(SoundType.AMETHYST)
    ));

    public static final BlockSet POLISHED_AMETHYST_SET = registerBlockSet(
            "polished_amethyst",
            ModBlocks.POLISHED_AMETHYST_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DIAMOND)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(1.5F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST),
            false,
            false
    );

    public static final Block CALCIFIED_AMETHYST_BLOCK = registerBlock("calcified_amethyst_block", properties -> new Block(properties
            .mapColor(MapColor.COLOR_PURPLE)
            .instrument(NoteBlockInstrument.BIT)
            .requiresCorrectToolForDrops()
            .strength(1F)
            .sound(SoundType.AMETHYST)
    ));

    public static final BlockSet CALCIFIED_AMETHYST_SET = registerBlockSet(
            "calcified_amethyst",
            ModBlocks.CALCIFIED_AMETHYST_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BIT)
                    .strength(1F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST),
            false,
            false
    );

    // LAPIS

    public static final BlockSet LAPIS_SET = registerBlockSet(
            "lapis",
            Blocks.LAPIS_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.LAPIS)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE),
            true,
            true
    );

    public static final Block LAPIS_BRICKS = registerBlock("lapis_bricks", properties -> new Block(properties
            .mapColor(MapColor.LAPIS)
            .instrument(NoteBlockInstrument.CHIME)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F)
            .sound(SoundType.STONE)
    ));

    public static final BlockSet LAPIS_BRICKS_SET = registerBlockSet(
            "lapis_brick",
            ModBlocks.LAPIS_BRICKS,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.LAPIS)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE),
            false,
            false
    );

    public static final Block POLISHED_LAPIS_BLOCK = registerBlock("polished_lapis_block", properties -> new Block(properties
            .mapColor(MapColor.LAPIS)
            .instrument(NoteBlockInstrument.CHIME)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F)
            .sound(SoundType.STONE)
    ));

    public static final BlockSet POLISHED_LAPIS_SET = registerBlockSet(
            "polished_lapis",
            ModBlocks.POLISHED_LAPIS_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.LAPIS)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE),
            false,
            false
    );

    public static final Block CALCIFIED_LAPIS_BLOCK = registerBlock("calcified_lapis_block", properties -> new Block(properties
            .mapColor(MapColor.LAPIS)
            .instrument(NoteBlockInstrument.CHIME)
            .requiresCorrectToolForDrops()
            .strength(1.0F, 3.0F)
            .sound(SoundType.CALCITE)
    ));

    public static final BlockSet CALCIFIED_LAPIS_SET = registerBlockSet(
            "calcified_lapis",
            ModBlocks.CALCIFIED_LAPIS_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.LAPIS)
                    .instrument(NoteBlockInstrument.CHIME)
                    .strength(1.0F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE),
            false,
            false
    );

    // COAL

    public static final BlockSet COAL_SET = registerBlockSet(
            "coal",
            Blocks.COAL_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            true,
            true
    );

    public static final Block COAL_BRICKS = registerBlock("coal_bricks", properties -> new Block(properties
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet COAL_BRICKS_SET = registerBlockSet(
            "coal_brick",
            ModBlocks.COAL_BRICKS,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block POLISHED_COAL_BLOCK = registerBlock("polished_coal_block", properties -> new Block(properties
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet POLISHED_COAL_SET = registerBlockSet(
            "polished_coal",
            ModBlocks.POLISHED_COAL_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block CALCIFIED_COAL_BLOCK = registerBlock("calcified_coal_block", properties -> new Block(properties
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 4.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet CALCIFIED_COAL_SET = registerBlockSet(
            "calcified_coal",
            ModBlocks.CALCIFIED_COAL_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 4.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    // CALCITE

    public static final BlockSet CALCITE_SET = registerBlockSet(
            "calcite",
            Blocks.CALCITE,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(0.75F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE),
            false,
            false
    );

    // NETHERITE

    public static final BlockSet NETHERITE_SET = registerBlockSet(
            "netherite",
            Blocks.NETHERITE_BLOCK,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(50.0F, 1200.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK),
            true,
            true
    );

    public static final Block NETHERITE_BRICKS = registerBlock("netherite_bricks", properties -> new Block(properties
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(50.0F, 1200.0F)
            .sound(SoundType.NETHERITE_BLOCK)
    ));

    public static final BlockSet NETHERITE_BRICKS_SET = registerBlockSet(
            "netherite_brick",
            ModBlocks.NETHERITE_BRICKS,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(50.0F, 1200.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK),
            false,
            false
    );

    public static final Block POLISHED_NETHERITE_BLOCK = registerBlock("polished_netherite_block", properties -> new Block(properties
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(50.0F, 1200.0F)
            .sound(SoundType.NETHERITE_BLOCK)
    ));

    public static final BlockSet POLISHED_NETHERITE_SET = registerBlockSet(
            "polished_netherite",
            ModBlocks.POLISHED_NETHERITE_BLOCK,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(50.0F, 1200.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK),
            false,
            false
    );

    public static final Block CALCIFIED_NETHERITE_BLOCK = registerBlock("calcified_netherite_block", properties -> new Block(properties
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(25.0F, 600.0F)
            .sound(SoundType.NETHERITE_BLOCK)
    ));

    public static final BlockSet CALCIFIED_NETHERITE_SET = registerBlockSet(
            "calcified_netherite",
            ModBlocks.CALCIFIED_NETHERITE_BLOCK,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(25.0F, 600.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK),
            false,
            false
    );

    // REDSTONE

    public static final BlockSet REDSTONE_SET = registerBlockSet(
            "redstone",
            Blocks.REDSTONE_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.FIRE)
                    .instrument(NoteBlockInstrument.SNARE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            true,
            true
    );

    public static final Block REDSTONE_BRICKS = registerBlock("redstone_bricks", properties -> new Block(properties
            .mapColor(MapColor.FIRE)
            .instrument(NoteBlockInstrument.SNARE)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet REDSTONE_BRICKS_SET = registerBlockSet(
            "redstone_brick",
            ModBlocks.REDSTONE_BRICKS,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.FIRE)
                    .instrument(NoteBlockInstrument.SNARE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block POLISHED_REDSTONE_BLOCK = registerBlock("polished_redstone_block", properties -> new Block(properties
            .mapColor(MapColor.FIRE)
            .instrument(NoteBlockInstrument.SNARE)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.METAL)
    ));

    public static final BlockSet POLISHED_REDSTONE_SET = registerBlockSet(
            "polished_redstone",
            ModBlocks.POLISHED_REDSTONE_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.FIRE)
                    .instrument(NoteBlockInstrument.SNARE)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL),
            false,
            false
    );

    public static final Block CALCIFIED_REDSTONE_BLOCK = registerBlock("calcified_redstone_block", properties -> new Block(properties
            .mapColor(MapColor.FIRE)
            .instrument(NoteBlockInstrument.SNARE)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 4.0F)
            .sound(SoundType.CALCITE)
    ));

    public static final BlockSet CALCIFIED_REDSTONE_SET = registerBlockSet(
            "calcified_redstone",
            ModBlocks.CALCIFIED_REDSTONE_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.FIRE)
                    .instrument(NoteBlockInstrument.SNARE)
                    .strength(3.0F, 4.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CALCITE),
            false,
            false
    );

    // OBSIDIAN

    public static final BlockSet OBSIDIAN_SET = registerBlockSet(
            "obsidian",
            Blocks.OBSIDIAN,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(50.0F, 1200.0F)
                    .requiresCorrectToolForDrops(),
            false,
            false
    );

    // BEDROCK

    public static final BlockSet BEDROCK_SET = registerBlockSet(
            "bedrock",
            Blocks.BEDROCK,
            BlockSetType.IRON,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(-1.0F, 3600000.0F)
                    .noLootTable()
                    .isValidSpawn(Blocks::never),
            false,
            false
    );

    // SCULK

    public static final BlockSet SCULK_SET = registerDropXPBlockSet(
            "sculk",
            Blocks.SCULK,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(0.2F)
                    .sound(SoundType.SCULK),
            1,
            1
    );

    // PACKED_ICE

    public static final BlockSet PACKED_ICE_SET = registerBlockSet(
            "packed_ice",
            Blocks.PACKED_ICE,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.ICE)
                    .instrument(NoteBlockInstrument.CHIME)
                    .friction(0.98F)
                    .strength(0.5F)
                    .sound(SoundType.GLASS),
            false,
            false
    );

    // BLUE_ICE

    public static final BlockSet BLUE_ICE_SET = registerBlockSet(
            "blue_ice",
            Blocks.BLUE_ICE,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.ICE)
                    .instrument(NoteBlockInstrument.CHIME)
                    .friction(0.989F)
                    .strength(2.8F)
                    .sound(SoundType.GLASS),
            false,
            false
    );

    // ICE

    public static final BlockSet ICE_SET = registerBlockSet(
            "ice",
            Blocks.ICE,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.ICE)
                    .instrument(NoteBlockInstrument.CHIME)
                    .friction(0.98F)
                    .strength(0.5F)
                    .noOcclusion()
                    .isValidSpawn((statex, blockGetter, blockPos, entityType) -> entityType == EntityTypes.POLAR_BEAR)
                    .sound(SoundType.GLASS)
                    .isRedstoneConductor(Blocks::never),
            false,
            false
    );

    // ORES

    public static final BlockSet GOLD_ORE_SET = registerDropXPBlockSet(
            "gold_ore",
            Blocks.GOLD_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            0,
            0
    );

    public static final BlockSet DEEPSLATE_GOLD_ORE_SET = registerDropXPBlockSet(
            "deepslate_gold_ore",
            Blocks.DEEPSLATE_GOLD_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            0,
            0
    );

    public static final BlockSet DIAMOND_ORE_SET = registerDropXPBlockSet(
            "diamond_ore",
            Blocks.DIAMOND_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            3,
            7
    );

    public static final BlockSet DEEPSLATE_DIAMOND_ORE_SET = registerDropXPBlockSet(
            "deepslate_diamond_ore",
            Blocks.DEEPSLATE_DIAMOND_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            3,
            7
    );

    public static final BlockSet IRON_ORE_SET = registerDropXPBlockSet(
            "iron_ore",
            Blocks.IRON_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            0,
            0
    );

    public static final BlockSet DEEPSLATE_IRON_ORE_SET = registerDropXPBlockSet(
            "deepslate_iron_ore",
            Blocks.DEEPSLATE_IRON_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            0,
            0
    );

    public static final BlockSet EMERALD_ORE_SET = registerDropXPBlockSet(
            "emerald_ore",
            Blocks.EMERALD_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            3,
            7
    );

    public static final BlockSet DEEPSLATE_EMERALD_ORE_SET = registerDropXPBlockSet(
            "deepslate_emerald_ore",
            Blocks.DEEPSLATE_EMERALD_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            3,
            7
    );

    public static final BlockSet LAPIS_ORE_SET = registerDropXPBlockSet(
            "lapis_ore",
            Blocks.LAPIS_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            2,
            5
    );

    public static final BlockSet DEEPSLATE_LAPIS_ORE_SET = registerDropXPBlockSet(
            "deepslate_lapis_ore",
            Blocks.DEEPSLATE_LAPIS_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            2,
            5
    );

    public static final BlockSet COAL_ORE_SET = registerDropXPBlockSet(
            "coal_ore",
            Blocks.COAL_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            0,
            2
    );

    public static final BlockSet DEEPSLATE_COAL_ORE_SET = registerDropXPBlockSet(
            "deepslate_coal_ore",
            Blocks.DEEPSLATE_COAL_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            0,
            2
    );

    public static final BlockSet REDSTONE_ORE_SET = registerDropXPBlockSet(
            "redstone_ore",
            Blocks.REDSTONE_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            3,
            7
    );

    public static final BlockSet DEEPSLATE_REDSTONE_ORE_SET = registerDropXPBlockSet(
            "deepslate_redstone_ore",
            Blocks.DEEPSLATE_REDSTONE_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            3,
            7
    );

    public static final BlockSet COPPER_ORE_SET = registerDropXPBlockSet(
            "copper_ore",
            Blocks.COPPER_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            0,
            0
    );

    public static final BlockSet DEEPSLATE_COPPER_ORE_SET = registerDropXPBlockSet(
            "deepslate_copper_ore",
            Blocks.DEEPSLATE_COPPER_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE),
            0,
            0
    );

    public static final BlockSet NETHER_GOLD_ORE_SET = registerDropXPBlockSet(
            "nether_gold_ore",
            Blocks.NETHER_GOLD_ORE,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.NETHER)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(),
            0,
            1
    );

    // PRISMARINE

    public static final Block PRISMARINE_BRICK_WALL = registerBlock("prismarine_brick_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.DIAMOND)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
    ));

    public static final Block DARK_PRISMARINE_WALL = registerBlock("dark_prismarine_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.DIAMOND)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
    ));

    // FLINT

    public static final Block FLINT_BLOCK = registerBlock("flint_block", properties -> new Block(properties
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .sound(SoundType.STONE)
    ));

    public static final BlockSet FLINT_SET = registerBlockSet(
            "flint",
            ModBlocks.FLINT_BLOCK,
            BlockSetType.ACACIA,
            WoodType.ACACIA,
            props -> props
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.STONE),
            false,
            false
    );

    // POLISHED ANDESITE/GRANITE/DIORITE WALL

    public static final Block POLISHED_ANDESITE_WALL = registerBlock("polished_andesite_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
    ));

    public static final Block POLISHED_GRANITE_WALL = registerBlock("polished_granite_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.DIRT)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
    ));

    public static final Block POLISHED_DIORITE_WALL = registerBlock("polished_diorite_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.QUARTZ)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
    ));

    // STONE

    public static final Block STONE_WALL = registerBlock("stone_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
    ));

    // NETHER BRICK FENCE GATE

    public static final Block NETHER_BRICK_FENCE_GATE = registerBlock("nether_brick_fence_gate", properties -> new FenceGateBlock(WoodType.ACACIA,properties
            .mapColor(MapColor.NETHER)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(2F, 6.0F)
    ));

    // PURPUR WALL

    public static final Block PURPUR_WALL = registerBlock("purpur_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.COLOR_MAGENTA)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F)
    ));

    // COPPER FENCES/WALLS
    // QUARTZ WALL

//    public static final Block QUARTZ_WALL = registerBlock("quartz_wall", properties -> new WallBlock(properties
//            .mapColor(MapColor.QUARTZ)
//            .instrument(NoteBlockInstrument.BASEDRUM)
//            .requiresCorrectToolForDrops()
//            .strength(0.8F)
//    ));

    public static final Block SMOOTH_QUARTZ_WALL = registerBlock("smooth_quartz_wall", properties -> new WallBlock(properties
            .mapColor(MapColor.QUARTZ)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(2.0F, 6.0F)
    ));

    // Todos los glasses

//    public static final Block GLASS_STAIRS = registerBlock("glass_stairs", properties -> new StairBlock(Blocks.GLASS.defaultBlockState(), properties
//            .instrument(NoteBlockInstrument.HAT)
//            .strength(0.3F)
//            .sound(SoundType.GLASS)
//            .noOcclusion()
//            .isValidSpawn(Blocks::never)
//            .isRedstoneConductor(Blocks::never)
//            .isSuffocating(Blocks::never)
//            .isViewBlocking(Blocks::never)
//    ));

//    public static final Block GLASS_SLAB = registerBlock("glass_slab", properties -> new SlabBlock(properties
//            .instrument(NoteBlockInstrument.HAT)
//            .strength(0.3F)
//            .sound(SoundType.GLASS)
//            .noOcclusion()
//            .isValidSpawn(Blocks::never)
//            .isRedstoneConductor(Blocks::never)
//            .isSuffocating(Blocks::never)
//            .isViewBlocking(Blocks::never)
//    ));

//    public static final BlockSet GLASS_SET = registerBlockSet(
//            "glass",
//            Blocks.GLASS,
//            BlockSetType.ACACIA,
//            WoodType.ACACIA,
//            props -> props
//                    .instrument(NoteBlockInstrument.HAT)
//                    .strength(0.3F)
//                    .sound(SoundType.GLASS)
//                    .noOcclusion()
//                    .isValidSpawn(Blocks::never)
//                    .isRedstoneConductor(Blocks::never)
//                    .isSuffocating(Blocks::never)
//                    .isViewBlocking(Blocks::never),
//            false,
//            false
//    );

    // CUSTOM BLOCKS

    public static final Block PROTECTOR_BLOCK = registerBlock("protector_block", properties -> new ProtectorBlock(properties
            .mapColor(MapColor.DIAMOND)
            .instrument(NoteBlockInstrument.HARP)
            .requiresCorrectToolForDrops()
            .strength(10F, 3600000F)
    ));

    // Funciones

    public static BlockSet registerBlockSet(
            String prefix,
            Block base,
            BlockSetType blockSetType,
            WoodType woodType,
            Function<BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesModifier,
            boolean makeDoor,
            boolean makeTrapdoor
    ) {

        Block stairs = registerBlock(prefix + "_stairs", prop -> new StairBlock(base.defaultBlockState(),propertiesModifier.apply(prop)));
        Block slab = registerBlock(prefix + "_slab", prop -> new SlabBlock(propertiesModifier.apply(prop)));
        Block fence = registerBlock(prefix + "_fence", prop -> new FenceBlock(propertiesModifier.apply(prop)));
        Block fence_gate = registerBlock(prefix + "_fence_gate", prop -> new FenceGateBlock(woodType ,propertiesModifier.apply(prop)));
        Block wall = registerBlock(prefix + "_wall", prop -> new WallBlock(propertiesModifier.apply(prop)));
        Block door = null;
        Block trapdoor = null;
        if (makeDoor) {
            door = registerBlock(prefix + "_door", prop -> new DoorBlock(blockSetType ,propertiesModifier.apply(prop).noOcclusion()));
        }
        if (makeTrapdoor) {
            trapdoor = registerBlock(prefix + "_trapdoor", prop -> new TrapDoorBlock(blockSetType ,propertiesModifier.apply(prop).noOcclusion()));
        }
        return new BlockSet(base, stairs, slab, fence, fence_gate, wall, door, trapdoor);
    }

    public static BlockSet registerDropXPBlockSet(
            String prefix,
            Block base,
            WoodType woodType,
            Function<BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesModifier,
            float minXp,
            float maxXP
    ) {

        Block stairs = registerBlock(prefix + "_stairs", prop -> new DropExperienceStairBlock(UniformInt.of((int) Math.ceil(minXp * 0.75), (int) Math.ceil(maxXP * 0.75)) ,base.defaultBlockState(),propertiesModifier.apply(prop)));
        Block slab = registerBlock(prefix + "_slab", prop -> new DropExperienceSlabBlock(UniformInt.of((int) Math.ceil(minXp * 0.5), (int) Math.ceil(maxXP * 0.5)), propertiesModifier.apply(prop)));
        Block fence = registerBlock(prefix + "_fence", prop -> new DropExperienceFenceBlock(UniformInt.of((int) Math.ceil(minXp * 0.25), (int) Math.ceil(maxXP * 0.25)), propertiesModifier.apply(prop)));
        Block fence_gate = registerBlock(prefix + "_fence_gate", prop -> new DropExperienceFenceGateBlock(UniformInt.of((int) Math.ceil(minXp * 0.25), (int) Math.ceil(maxXP * 0.25)), woodType ,propertiesModifier.apply(prop)));
        Block wall = registerBlock(prefix + "_wall", prop -> new DropExperienceWallBlock(UniformInt.of((int) Math.ceil(minXp * 0.5), (int) Math.ceil(maxXP * 0.5)), propertiesModifier.apply(prop)));
        return new BlockSet(base, stairs, slab, fence, fence_gate, wall, null, null);
    }

    public static ResourceKey<Block> getResourceKey(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(
                ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Umamium.MOD_ID, name))));

        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK,
                Identifier.fromNamespaceAndPath(Umamium.MOD_ID, name),
                toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Umamium.MOD_ID, name),
                new BlockItem(block, new Item.Properties()
                        .useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM,
                                Identifier.fromNamespaceAndPath(Umamium.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        Umamium.LOGGER.info("Registering Mod Blocks for " + Umamium.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            output.accept(DARK_PRISMARINE_WALL);
            output.accept(PRISMARINE_BRICK_WALL);
            output.accept(PURPUR_WALL);
            output.accept(POLISHED_ANDESITE_WALL);
            output.accept(POLISHED_GRANITE_WALL);
            output.accept(POLISHED_DIORITE_WALL);
            output.accept(STONE_WALL);
//            output.accept(QUARTZ_WALL);
            output.accept(SMOOTH_QUARTZ_WALL);
            output.accept(NETHER_BRICK_FENCE_GATE);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
            output.accept(PROTECTOR_BLOCK);
        });

        addSetToCreativeTab(GOLD_SET, false);
        addSetToCreativeTab(GOLD_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_GOLD_SET, true);
        addSetToCreativeTab(POLISHED_GOLD_SET, true);

        addSetToCreativeTab(GOLD_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_GOLD_ORE_SET, false);
        addSetToCreativeTab(NETHER_GOLD_ORE_SET, false);

        addSetToCreativeTab(DIAMOND_SET, false);
        addSetToCreativeTab(DIAMOND_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_DIAMOND_SET, true);
        addSetToCreativeTab(POLISHED_DIAMOND_SET, true);

        addSetToCreativeTab(DIAMOND_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_DIAMOND_ORE_SET, false);

        addSetToCreativeTab(IRON_SET, false);
        addSetToCreativeTab(IRON_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_IRON_SET, true);
        addSetToCreativeTab(POLISHED_IRON_SET, true);

        addSetToCreativeTab(IRON_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_IRON_ORE_SET, false);

        addSetToCreativeTab(EMERALD_SET, false);
        addSetToCreativeTab(EMERALD_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_EMERALD_SET, true);
        addSetToCreativeTab(POLISHED_EMERALD_SET, true);
        addSetToCreativeTab(CHISELED_EMERALD_SET, false);

        addSetToCreativeTab(EMERALD_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_EMERALD_ORE_SET, false);

        addSetToCreativeTab(AMETHYST_SET, false);
        addSetToCreativeTab(AMETHYST_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_AMETHYST_SET, true);
        addSetToCreativeTab(POLISHED_AMETHYST_SET, true);

        addSetToCreativeTab(LAPIS_SET, false);
        addSetToCreativeTab(LAPIS_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_LAPIS_SET, true);
        addSetToCreativeTab(POLISHED_LAPIS_SET, true);

        addSetToCreativeTab(LAPIS_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_LAPIS_ORE_SET, false);

        addSetToCreativeTab(COAL_SET, false);
        addSetToCreativeTab(COAL_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_COAL_SET, true);
        addSetToCreativeTab(POLISHED_COAL_SET, true);

        addSetToCreativeTab(COAL_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_COAL_ORE_SET, false);

        addSetToCreativeTab(COPPER_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_COPPER_ORE_SET, false);

        addSetToCreativeTab(NETHERITE_SET, false);
        addSetToCreativeTab(NETHERITE_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_NETHERITE_SET, true);
        addSetToCreativeTab(POLISHED_NETHERITE_SET, true);

        addSetToCreativeTab(REDSTONE_SET, false);
        addSetToCreativeTab(REDSTONE_BRICKS_SET, true);
        addSetToCreativeTab(CALCIFIED_REDSTONE_SET, true);
        addSetToCreativeTab(POLISHED_REDSTONE_SET, true);

        addSetToCreativeTab(REDSTONE_ORE_SET, false);
        addSetToCreativeTab(DEEPSLATE_REDSTONE_ORE_SET, false);

        addSetToCreativeTab(OBSIDIAN_SET, false);
        addSetToCreativeTab(SCULK_SET, false);
        addSetToCreativeTab(BEDROCK_SET, false);
        addSetToCreativeTab(CALCITE_SET, false);
        addSetToCreativeTab(FLINT_SET, true);
        addSetToCreativeTab(ICE_SET, false);
        addSetToCreativeTab(PACKED_ICE_SET, false);
        addSetToCreativeTab(BLUE_ICE_SET, false);
    }

    private static void addSetToCreativeTab(BlockSet blockSet, boolean includeBase) {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            if (includeBase) {output.accept(blockSet.base);}
            output.accept(blockSet.stairs);
            output.accept(blockSet.slab);
            output.accept(blockSet.fence);
            output.accept(blockSet.fenceGate);
            output.accept(blockSet.wall);
            if (blockSet.door != null) {output.accept(blockSet.door);}
            if (blockSet.trapdoor != null) {output.accept(blockSet.trapdoor);}
        });
    }
}

package net.elgoblin.moremineralblocks.util;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> NEEDS_LEGENDARY_TOOL = createTag("needs_legendary_tool");
        public static final TagKey<Block> INCORRECT_FOR_LEGENDARY_TOOL = createTag("incorrect_for_legendary_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MoreMineralBlocks.MOD_ID, name));
        }
    }

    public static class Items {
        // Este es el grupo de items que van a transformarse en diamantes en el magic block
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MoreMineralBlocks.MOD_ID, name));
        }
    }
}

package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.block.ModBlocks;
//import net.elgoblin.moremineralblocks.client.InfiniteItemRenderer;
import net.elgoblin.moremineralblocks.client.ClientEvents;
import net.elgoblin.moremineralblocks.client.InfiniteItemClientCache;
import net.elgoblin.moremineralblocks.client.InfiniteItemstackOverlay;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.entity.ModEntities;
//import net.elgoblin.moremineralblocks.entity.client.DevilmonModel;
//import net.elgoblin.moremineralblocks.entity.client.MantisModel;
//import net.elgoblin.moremineralblocks.entity.client.MantisRenderer;
//import net.elgoblin.moremineralblocks.entity.client.DevilmonRenderer;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.particle.custom.ChaosOrbFeedbackParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;


public class MoreMineralBlocksClient implements ClientModInitializer {

    public static final KeyBinding.Category LEGENDARY_TOOLS = KeyBinding.Category.create(Identifier.of(MoreMineralBlocks.MOD_ID, "legendary_tools"));
    public static KeyBinding switchEnchantments_toggleSafeMode;
    public static KeyBinding intraGroupScroll;
    public static KeyBinding interGroupScroll;

    @Override
    public void onInitializeClient() {
        switchEnchantments_toggleSafeMode = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.moremineralblocks.switch_enchantments_toggle_safe_mode", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, LEGENDARY_TOOLS));
        intraGroupScroll = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.moremineralblocks.intra_group_scroll", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_TAB, LEGENDARY_TOOLS));
        interGroupScroll = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.moremineralblocks.inter_group_scroll", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_GRAVE_ACCENT, LEGENDARY_TOOLS));

        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLD_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.AMETHYST_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.AMETHYST_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.DIAMOND_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.DIAMOND_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(ModBlocks.EMERALD_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.EMERALD_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.NETHERITE_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.NETHERITE_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.LAPIS_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.LAPIS_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.COAL_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.COAL_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.REDSTONE_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.REDSTONE_TRAPDOOR, BlockRenderLayer.CUTOUT);

        EntityRendererRegistry.register(ModEntities.CHAOS_ORB, FlyingItemEntityRenderer::new);
        InfiniteItemstackOverlay.register();
        ClientEvents.register();

//        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.INFINITE_ITEMSTACK, new InfiniteItemRenderer());

//        EntityModelLayerRegistry.registerModelLayer(MantisModel.MANTIS, MantisModel::getTexturedModelData);
//        EntityRendererRegistry.register(ModEntities.MANTIS, MantisRenderer::new);
//
//        EntityModelLayerRegistry.registerModelLayer(DevilmonModel.DEVILMON, DevilmonModel::getTexturedModelData);
//        EntityRendererRegistry.register(ModEntities.DEVILMON, DevilmonRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.PICKAXE_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_HASTE_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_JUMP_BOOST_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_REGENERATION_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_RESISTANCE_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_SPEED_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_STRENGTH_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_COUNTER_BLINK_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_BLINKING_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CHAOS_ORB_FRAGILE_PARTICLE, ChaosOrbFeedbackParticle.Factory::new);

        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) {return;}

            ItemStack mainHoldedStack = client.player.getMainHandStack();
            ItemStack offHoldedStack = client.player.getOffHandStack();

            if (mainHoldedStack.isOf(ModItems.DIMENSIONAL_POCKET)) {
                renderSelectedStack(drawContext, client, client.player.getMainHandStack());
            }
            else if (offHoldedStack.isOf(ModItems.DIMENSIONAL_POCKET)) {
                renderSelectedStack(drawContext, client, client.player.getOffHandStack());
            }
        }));
    }

    private void renderSelectedStack(DrawContext drawContext, MinecraftClient client, ItemStack stack) {
        BlockPos storagePos = stack.get(ModDataComponentTypes.LINKED_CHEST);
        Identifier dimension = stack.get(ModDataComponentTypes.SERVERWORLD);

        if (storagePos == null || dimension == null) {
            return;
        }
        ItemStack selectedStack = InfiniteItemClientCache.mainRenderedStack;

        if (!selectedStack.isEmpty() && client.player != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            int hotbarLeftX = width / 2 - 90;
            int selectedSlot = client.player.getInventory().getSelectedSlot();
            int x = hotbarLeftX + (selectedSlot * 20) + 2;
            int y = height - 22 + 3;

            if (client.player.getOffHandStack() == stack) {
                x = (width / 2) - 90 - 29 + 2;
            }


            drawContext.drawItem(selectedStack, x, y);
            drawContext.drawStackOverlay(
                    client.textRenderer,
                    selectedStack,
                    x,
                    y,
                    String.valueOf(selectedStack.getCount()));

        }
    }
}

package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.block.ModBlocks;
//import net.elgoblin.moremineralblocks.client.InfiniteItemRenderer;
import net.elgoblin.moremineralblocks.entity.ModEntities;
//import net.elgoblin.moremineralblocks.entity.client.DevilmonModel;
//import net.elgoblin.moremineralblocks.entity.client.MantisModel;
//import net.elgoblin.moremineralblocks.entity.client.MantisRenderer;
//import net.elgoblin.moremineralblocks.entity.client.DevilmonRenderer;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.particle.custom.ChaosOrbFeedbackParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;


public class MoreMineralBlocksClient implements ClientModInitializer {

//    private static KeyBinding switchEnchantments = KeyBindingHelper.registerKeyBinding(new KeyBinding(
//            "key.moremineralblocks.switch_enchantments",
//            InputUtil.Type.KEYSYM,
//            GLFW.GLFW_KEY_K,
//            "category.moremineralblocks.moremineralblocks"
//    ));

    //public TypedActionResult<ItemStack> advanceEnchantments(PlayerEntity user) {
        //return user.getMainHandStack().getItem().
    //};

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOLD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AMETHYST_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AMETHYST_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIAMOND_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIAMOND_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERALD_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERALD_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NETHERITE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NETHERITE_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAPIS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAPIS_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COAL_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COAL_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REDSTONE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REDSTONE_TRAPDOOR, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.CHAOS_ORB, FlyingItemEntityRenderer::new);

//        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.INFINITE_ITEMSTACK, new InfiniteItemRenderer());

//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//
////            if (switchEnchantments.wasPressed()) {
////                    if (client.player.getMainHandStack().getItem() == ModItems.LEGENDARY_PICKAXE) {
////                        //advanceEnchantments(client.player);
////                    }
////            }
//        });

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
    }
}

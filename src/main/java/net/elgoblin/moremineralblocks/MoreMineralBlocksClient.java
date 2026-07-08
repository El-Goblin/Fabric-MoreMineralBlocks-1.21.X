package net.elgoblin.moremineralblocks;

import net.elgoblin.moremineralblocks.block.ModBlocks;
//import net.elgoblin.moremineralblocks.client.InfiniteItemRenderer;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.entity.ModEntities;
//import net.elgoblin.moremineralblocks.entity.client.DevilmonModel;
//import net.elgoblin.moremineralblocks.entity.client.MantisModel;
//import net.elgoblin.moremineralblocks.entity.client.MantisRenderer;
//import net.elgoblin.moremineralblocks.entity.client.DevilmonRenderer;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.item.ItemModelManager;
import net.elgoblin.moremineralblocks.networking.LegendaryToolsSwitchEnchantmentPayload;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.particle.custom.ChaosOrbFeedbackParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

    private static KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        KeyBinding.Category LEGENDARY_TOOLS = KeyBinding.Category.create(Identifier.of(MoreMineralBlocks.MOD_ID, "legendary_tools"));
        KeyBinding switchEnchantments = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.moremineralblocks.switch_enchantments", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, LEGENDARY_TOOLS));

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


        ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
            while (switchEnchantments.wasPressed()) {
//                if (minecraftClient.player != null) {
//                    minecraftClient.player.sendMessage(Text.of("asd"), false);
//                }
                ClientPlayNetworking.send(new LegendaryToolsSwitchEnchantmentPayload());
            }
        });

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

        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) {return;}

            ItemStack mainHoldedStack = client.player.getMainHandStack();
            ItemStack offHoldedStack = client.player.getOffHandStack();

            if (mainHoldedStack.isOf(ModItems.INFINITE_ITEMV2)) {
                renderSelectedStack(drawContext, client, client.player.getMainHandStack(), (ServerWorld) client.player.getEntityWorld());
            }
            else if (offHoldedStack.isOf(ModItems.INFINITE_ITEMV2)) {
                renderSelectedStack(drawContext, client, client.player.getOffHandStack(), (ServerWorld) client.player.getEntityWorld());
            }
        }));
    }

    private void renderSelectedStack(DrawContext drawContext, MinecraftClient client, ItemStack stack, ServerWorld world) {
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        int x = (width / 2) + 101;
        int y = height - 22;

        ItemStack selectedStack = ModItems.CHAOS_ORB.getDefaultStack();

        selectedStack.setCount(35);

        if (!selectedStack.isEmpty()) {
            drawContext.drawItem(selectedStack, x, y);
            int number = 3456;
            drawContext.drawStackOverlay(client.textRenderer, selectedStack, x + (int) (2*Math.log10(number)), y, "3456");

        }
    }
}

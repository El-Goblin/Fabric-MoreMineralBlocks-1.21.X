package net.elgoblin.moremineralblocks.entity.custom;

import net.elgoblin.moremineralblocks.MoreMineralBlocks;
import net.elgoblin.moremineralblocks.block.ModBlocks;
import net.elgoblin.moremineralblocks.component.ModDataComponentTypes;
import net.elgoblin.moremineralblocks.effect.ModEffects;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.ChaosOrbItem;
import net.elgoblin.moremineralblocks.particle.ModParticles;
import net.elgoblin.moremineralblocks.terrain.SingleBlockSphereJob;
import net.elgoblin.moremineralblocks.terrain.SkyBlockJob;
import net.elgoblin.moremineralblocks.terrain.TerrainManager;
import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SkeletonHorseEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerLightingProvider;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.chunk.*;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ChaosOrbEntity extends ThrownItemEntity {

    private final Map<String, SimpleParticleType> particleMap = Map.of("minecraft:haste", ModParticles.CHAOS_ORB_HASTE_PARTICLE,
            "minecraft:jump_boost", ModParticles.CHAOS_ORB_JUMP_BOOST_PARTICLE,
            "minecraft:regeneration", ModParticles.CHAOS_ORB_REGENERATION_PARTICLE,
            "minecraft:resistance", ModParticles.CHAOS_ORB_RESISTANCE_PARTICLE,
            "minecraft:speed", ModParticles.CHAOS_ORB_SPEED_PARTICLE,
            "minecraft:strength", ModParticles.CHAOS_ORB_STRENGTH_PARTICLE);
    private final Random random = Random.create();

    private boolean tunneler = false;
    private LinkedList<BlockPos> tunnelQueue = new LinkedList<>();
    private static boolean skyBlockHappened = false;

    private List<Consumer<HitResult>> pointChaosEffects = new ArrayList<>(List.of(
            this::spawnMobPack,
            this::getMythicItem,
            this::spawnSkeletonHorse,
            this::breakGameProgression,
            this::getArmorSet,
            this::getToolsSet,
            this::spawn5ChaosOrbs,
            this::voidSphere,
            this::explosion,
            this::fireExplosion,
            this::getFood,
            this::getEnchantedBook,
            this::smallPrize,
            this::xp,
            this::getInfiniteItem
    ));
    private List<BiConsumer<HitResult, Box>> areaChaosEffects = new ArrayList<>(List.of(
            this::applyBeaconEffect
    ));
    private List<BiConsumer<HitResult, Box>> selfAreaChaosEffects = new ArrayList<>(List.of(
            this::fragile
    ));
    private List<Consumer<HitResult>> selfChaosEffects = new ArrayList<>(List.of(
//            this::crash
    ));
    private List<Consumer<HitResult>> globalChaosEffects = new ArrayList<>(List.of(
            this::beginThunderstorm,
            this::randomizePlayersPositions
//            this::createSkyblock
            // Skyblock se va a ir agregando en cada llamado hasta que salga una vez.
    ));
    private List<BiConsumer<HitResult, Box>> targetsOrSelfChaosEffects = new ArrayList<>(List.of(
            this::counterBlinking,
//            this::adventureGamemode
//            this::onanaHands,
            this::blinking,
            this::moveXBlocks
    ));


    public ChaosOrbEntity(EntityType<? extends ChaosOrbEntity> entityType, World world) {
        super(entityType, world);
//        this.addSkyBlock();
        this.tunneler = random.nextBetween(0, this.effectCount()) == 0;
    }

    public ChaosOrbEntity(World world, LivingEntity owner) {
        super(ModEntities.CHAOS_ORB, owner, world);
//        this.addSkyBlock();
        this.tunneler = random.nextBetween(0, this.effectCount()) == 0;
    }

    public ChaosOrbEntity(World world, double x, double y, double z) {
        super(ModEntities.CHAOS_ORB, x, y, z, world);
//        this.addSkyBlock();
        this.tunneler = random.nextBetween(0, this.effectCount()) == 0;
    }

    private void addSkyBlock() {
        MinecraftServer server = this.getWorld().getServer();
        if (server != null) {
            if (!MoreMineralBlocks.getStateSaverAndLoader(server).hasSkyblockHappened()) {
                this.globalChaosEffects.add(this::createSkyblock);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tunneler) {
            if (!this.getWorld().isClient) {
                BlockPos center = new BlockPos(new Vec3i((int) this.getX(), (int) this.getY(), (int) this.getZ()));

                for (int x = -5; x <= 5; x++) {
                    for (int z = -5; z <= 5; z++) {
                        for (int y = -5; y <= 5; y++) {
                            if (x * x + y * y + z * z > 25) {
                                continue;
                            }

                            BlockPos currentBlock = center.add(x, y, z);
                            this.tunnelQueue.add(currentBlock);
                        }
                    }
                }
                World world = this.getWorld();
                while (!tunnelQueue.isEmpty()) {
                    BlockPos blockToRemove = tunnelQueue.pop();
                    BlockState currentState = world.getBlockState(blockToRemove);

                    MinecraftServer server = this.getServer();
                    if (server != null) {
                        ProtectorManager protectorManager = ProtectorManager.getProtectorManager(this.getServer());

                        if (!currentState.isAir() && !protectorManager.isProtected(blockToRemove)) {
                            world.setBlockState(blockToRemove, Blocks.AIR.getDefaultState(), 50);
                        }
                    }
                }
            }
        }
    }

    private int nextCategory() {

        int pointChaosEffectsInterval = pointChaosEffects.size();
        int areaChaosEffectsInterval = pointChaosEffectsInterval + areaChaosEffects.size();
        int selfAreaChaosEffectsInterval = areaChaosEffectsInterval + selfAreaChaosEffects.size();
        int selfChaosEffectsInterval = selfAreaChaosEffectsInterval + selfChaosEffects.size();
        int globalChaosEffectsInterval = selfChaosEffectsInterval + globalChaosEffects.size();
        int targetsOrSelfChaosEffectsInterval = globalChaosEffectsInterval + targetsOrSelfChaosEffects.size();


        int category = this.random.nextBetween(0, targetsOrSelfChaosEffectsInterval-1);

        if (category < pointChaosEffectsInterval) {
            return 0;
        } else if (category < areaChaosEffectsInterval) {
            return 1;
        } else if (category < selfAreaChaosEffectsInterval) {
            return 2;
        } else if (category < selfChaosEffectsInterval) {
            return 3;
        } else if (category < globalChaosEffectsInterval) {
            return 4;
        }
        else { return 5;} // Targets or Self Chaos Effects Interval
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);

            int eventCategory = nextCategory();
            int nextEffect;
            Box boundingBox = this.getBoundingBox();

            switch (eventCategory) {
                // POINT
                case 0:
                    // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                    if (!pointChaosEffects.isEmpty()) {
                        nextEffect = random.nextBetween(0, pointChaosEffects.size()-1);
                        pointChaosEffects.get(nextEffect).accept(hitResult);
                    }
                    break;
                //AREA
                case 1:
                    // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                    if (!areaChaosEffects.isEmpty()) {
                        nextEffect = random.nextBetween(0, areaChaosEffects.size()-1);
                        areaChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                    }
                    break;
                //SELF AREA
                case 2:
                    // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                    if (!selfAreaChaosEffects.isEmpty()) {
                        nextEffect = random.nextBetween(0, selfAreaChaosEffects.size()-1);
                        selfAreaChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                    }
                    break;
                //SELF
                case 3:
                    // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                    if (!selfChaosEffects.isEmpty()) {
                        nextEffect = random.nextBetween(0, selfChaosEffects.size()-1);
                        selfChaosEffects.get(nextEffect).accept(hitResult);
                    }
                    break;
                //GLOBAL
                case 4:
                    // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                    if (!globalChaosEffects.isEmpty()) {
                        nextEffect = random.nextBetween(0, globalChaosEffects.size()-1);
                        globalChaosEffects.get(nextEffect).accept(hitResult);
                    }
                    break;
                //TARGET SELF
                case 5:
                    // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                    if (!targetsOrSelfChaosEffects.isEmpty()) {
                        nextEffect = random.nextBetween(0, targetsOrSelfChaosEffects.size()-1);
                        targetsOrSelfChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                    }
                    break;
            }

            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.CHAOS_ORB;
    }

    public int effectCount() {
        return this.pointChaosEffects.size() +
                this.areaChaosEffects.size() +
                this.selfAreaChaosEffects.size() +
                this.selfChaosEffects.size() +
                this.globalChaosEffects.size() +
                this.targetsOrSelfChaosEffects.size();
    }

    private static final List<TropicalFishEntity.Variant> TROPICAL_FISH_VARIANTS = List.of(
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.STRIPEY, DyeColor.ORANGE, DyeColor.GRAY),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.FLOPPER, DyeColor.GRAY, DyeColor.GRAY),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.FLOPPER, DyeColor.GRAY, DyeColor.BLUE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.CLAYFISH, DyeColor.WHITE, DyeColor.GRAY),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.SUNSTREAK, DyeColor.BLUE, DyeColor.GRAY),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.KOB, DyeColor.ORANGE, DyeColor.WHITE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.SPOTTY, DyeColor.PINK, DyeColor.LIGHT_BLUE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.BLOCKFISH, DyeColor.PURPLE, DyeColor.YELLOW),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.CLAYFISH, DyeColor.WHITE, DyeColor.RED),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.SPOTTY, DyeColor.WHITE, DyeColor.YELLOW),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.GLITTER, DyeColor.WHITE, DyeColor.GRAY),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.CLAYFISH, DyeColor.WHITE, DyeColor.ORANGE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.DASHER, DyeColor.CYAN, DyeColor.PINK),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.BRINELY, DyeColor.LIME, DyeColor.LIGHT_BLUE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.BETTY, DyeColor.RED, DyeColor.WHITE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.SNOOPER, DyeColor.GRAY, DyeColor.RED),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.BLOCKFISH, DyeColor.RED, DyeColor.WHITE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.FLOPPER, DyeColor.WHITE, DyeColor.YELLOW),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.KOB, DyeColor.RED, DyeColor.WHITE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.SUNSTREAK, DyeColor.GRAY, DyeColor.WHITE),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.DASHER, DyeColor.CYAN, DyeColor.YELLOW),
            new TropicalFishEntity.Variant(TropicalFishEntity.Variety.FLOPPER, DyeColor.YELLOW, DyeColor.YELLOW)
    );

    private void spawnMobPack(HitResult hitResult) {

        // Como es esto un tipo???
        List<EntityType<?>> mobs = Registries.ENTITY_TYPE.stream()
                .filter(type -> type.create(MinecraftClient.getInstance().world) instanceof MobEntity)
                .toList();

        int nextEntity = this.random.nextBetween(0, mobs.size()-1);
        int spawnsToPerform = this.random.nextBetween(5, 14);

        EntityType<?> entityType = mobs.get(nextEntity);
        Identifier entityTypeID = Registries.ENTITY_TYPE.getId(entityType);

        while (spawnsToPerform-- > 0) {
            Entity entity = entityType.create(this.getWorld());

            switch (entityTypeID.toString()) {

                case "minecraft:wither":
                    if (entity != null) {
                        ((WitherEntity) entity).setInvulTimer(440);
                    }
                    spawnsToPerform -= 4;
                    break;

                case "minecraft:ender_dragon", "minecraft:warden", "moremineralblocks:devilmon":
                    nextEntity = this.random.nextBetween(0, mobs.size() - 1);
                    entityType = mobs.get(nextEntity);
                    entityTypeID = Registries.ENTITY_TYPE.getId(entityType);
                    entity = entityType.create(this.getWorld());
                    spawnsToPerform++;
                    break;


                case "minecraft:bat", "minecraft:bee", "minecraft:chicken", "minecraft:cod", "minecraft:frog", "minecraft:pufferfish", "minecraft:rabbit", "minecraft:salmon", "minecraft:silverfish", "minecraft:endermite", "minecraft:tadpole":
                    Entity entity2 = entityType.create(this.getWorld());
                    if (entity2 != null) {
                        entity2.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                        this.getWorld().spawnEntity(entity2);
                    }
                    Entity entity3 = entityType.create(this.getWorld());
                    if (entity3 != null) {
                        entity3.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                        this.getWorld().spawnEntity(entity3);
                    }
                    break;

                case "minecraft:tropical_fish":
                    for (int i = 0 ; i < 3 ; i++) {
                        entity = entityType.create(this.getWorld());
                        if (entity != null) {
                            TropicalFishEntity.Variant variant = TROPICAL_FISH_VARIANTS.get(random.nextBetween(0, TROPICAL_FISH_VARIANTS.size()-1));
                            int variantInt = variant.variety().getId() & 65535 |  (variant.baseColor().getId() & 0xFF) << 16 | (variant.patternColor().getId() & 0xFF) << 24;

                            NbtCompound nbt = new NbtCompound();
                            entity.writeNbt(nbt);
                            nbt.putInt("Variant", variantInt);
                            entity.readNbt(nbt);
                            entity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                            this.getWorld().spawnEntity(entity);
                        }
                    }
                    break;

                case "minecraft:axolotl":
                    spawnsToPerform -= 3;
                    if (entity != null) {
                        ((AxolotlEntity) entity).setVariant(AxolotlEntity.Variant.byId(random.nextBetween(0,4)));
                    }
                    break;

                case "minecraft:elder_guardian":
                    spawnsToPerform-=4;
                    break;

                case "minecraft:slime":
                    if (entity != null) {
                        ((SlimeEntity) entity).setSize(random.nextBetween(1, 4), true);
                    }
                    break;

                case "minecraft:magma_cube":
                    if (entity != null) {
                        ((MagmaCubeEntity) entity).setSize(random.nextBetween(1, 4), true);
                    }
                    break;

                case "minecraft:evoker", "minecraft:ravager":
                    spawnsToPerform-=2;
                    break;
            }

            if (entity != null) {
                entity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                this.getWorld().spawnEntity(entity);
            }
        }
    }

    private void spawnSkeletonHorse(HitResult hitResult) {
        SkeletonHorseEntity skeletonHorseEntity = EntityType.SKELETON_HORSE.create(this.getWorld());
        if (skeletonHorseEntity != null) {
            skeletonHorseEntity.setTrapped(true);
            skeletonHorseEntity.setBreedingAge(0);
            skeletonHorseEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);

            this.getWorld().spawnEntity(skeletonHorseEntity);
        }
    }

    private void beginThunderstorm(HitResult hitResult) {
        if (!this.getWorld().isClient) {
            if (this.getServer() != null) {
                ServerWorld world = this.getServer().getOverworld();
                world.setWeather(0, UniformIntProvider.create(3600, 15600).get(world.getRandom()), true, true);
                LightningEntity bolt = EntityType.LIGHTNING_BOLT.create(this.getWorld());
                if (bolt != null) {
                    bolt.refreshPositionAndAngles(hitResult.getPos(),1f, 1f);
                    this.getWorld().spawnEntity(bolt);
                }
            }
        }
    }

    private void moveXBlocks(HitResult hitResult, Box boundingBox) {
        List<LivingEntity> entities = this.getWorld().getNonSpectatingEntities(LivingEntity.class, boundingBox.expand(16.0, 8.0, 16.0));

        boolean goDown = this.random.nextBoolean();

        if (entities.isEmpty() && this.getOwner() != null) {
            entities.add((LivingEntity) this.getOwner());
        }
        for (LivingEntity entity : entities) {

            // Ocurre cuando se muere el player antes de que se calcule la lista
            if (entity == null) {
                continue;
            }

            if (this.getServer() != null) {
                ServerWorld server = this.getServer().getWorld(entity.getWorld().getRegistryKey());
                TeleportTarget teleportTarget = new TeleportTarget(server,
                        new Vec3d(entity.getX(),
                                entity.getY() + (goDown ? -20 : 20),
                                entity.getZ()),
                        new Vec3d(0, 0, 0),
                        entity.getYaw(),
                        entity.getPitch(),
                        TeleportTarget.NO_OP);
                entity.teleportTo(teleportTarget);
                if (goDown) {
                    entity.sendMessage(Text.of("-20"));
                } else {
                    entity.sendMessage(Text.of("+20"));
                }
            }
        }

    }

    private void randomizePlayersPositions(HitResult hitResult) {
        int forceTeleport = random.nextInt(20);
        if (forceTeleport == 0 && this.getOwner() instanceof PlayerEntity) {
            // Notar que de esta forma se aumenta la estadistica de veces usadas el item. Me parece correcto
            ModItems.CHAOS_MIRROR.use(this.getWorld(), (PlayerEntity) this.getOwner(), Hand.MAIN_HAND);
        }
        else {
            this.dropStack(ModItems.CHAOS_MIRROR.getDefaultStack());
        }
    }

    private void applyBeaconEffect(HitResult hitResult, Box boundingBox) {

        List<LivingEntity> entities = this.getWorld().getNonSpectatingEntities(LivingEntity.class, boundingBox.expand(32.0, 200.0, 32.0));
        List<RegistryEntry<StatusEffect>> pool = BeaconBlockEntity.EFFECTS_BY_LEVEL.stream().flatMap(List::stream).toList();

        long poolSize = pool.size();
        int nextEffect = this.random.nextBetween(0, (int) poolSize-1);
        int nextLevel = this.random.nextBetween(0, 9);
        RegistryEntry<StatusEffect> effect = pool.get(nextEffect);
        SimpleParticleType effectParticle = particleMap.get(effect.getIdAsString());
        ((ServerWorld) this.getWorld()).spawnParticles(effectParticle,this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            StatusEffectInstance effectInstance = new StatusEffectInstance(effect, 24000, nextLevel);
            if (entity != null) {
                entity.addStatusEffect(effectInstance);
            }
        }
    }

    private void crash(HitResult hitResult) {
        // Esto nunca va a funcionar porque el chaosOrb corre en server
        // Quizas podria hacer que el chequeo lo haga en ChaosOrbItem como hacia para el tunel
        // No se si vale la pena tener este efecto y ademas mepa que va a generar una banda de bugs
        if (this.getWorld().isClient) {
            LivingEntity entity = (LivingEntity) this.getOwner();
            if (entity != null) { entity.sendMessage(Text.of("crash"));}
            throw new RuntimeException("Chaos Crash");
        }
    }

    private void saveAndQuit(HitResult hitResult) {
    }

    private void getFood(HitResult hitResult) {
        List<Item> foodItems = new ArrayList<>();

        for (Item item : Registries.ITEM) {
            if (item.getComponents().get(DataComponentTypes.FOOD) != null && !(item instanceof PotionItem) && !(item instanceof OminousBottleItem)) {
                foodItems.add(item);
            }
        }

        ItemStack food = foodItems.get(random.nextInt(foodItems.size())).getDefaultStack();
        if (food.getItem() != Items.ENCHANTED_GOLDEN_APPLE && food.getItem() != Items.GOLDEN_APPLE) {
            food.setCount(32);
        }
        // For Suspicious Stew, you need to add a random effect
        if (food.getItem() == Items.SUSPICIOUS_STEW) {
            List<RegistryEntry<StatusEffect>> statusEffects = Registries.STATUS_EFFECT.streamEntries()
                    .map(entry -> (RegistryEntry<StatusEffect>) entry)
                    .toList();
            RegistryEntry<StatusEffect> effectEntry = statusEffects.get(random.nextInt(statusEffects.size()));

            SuspiciousStewEffectsComponent.StewEffect chosenEffect = new SuspiciousStewEffectsComponent.StewEffect(effectEntry, 160);

            food.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffectsComponent(List.of(chosenEffect)));
            food.setCount(1);
        }
        this.dropStack(food, 0);
    }

    private void getInfiniteItem(HitResult hitResult) {
        ItemStack infiniteItem = new ItemStack(ModItems.INFINITE_ITEMSTACK);
        List<Item> items = new ArrayList<>();

        for (Item item : Registries.ITEM) {
//            if (!(item instanceof ToolItem) && !(item instanceof EnderEyeItem) && !(item.getComponents().contains(DataComponentTypes.FOOD))) {
//                boolean isMusicDisc = item.getComponents().contains(DataComponentTypes.JUKEBOX_PLAYABLE);
//                if (!isMusicDisc) {
                    boolean isBlock = item instanceof BlockItem;
                    boolean isBucket = item instanceof FluidModificationItem;
                    boolean isSpawnEgg = item instanceof SpawnEggItem;
                    boolean isThrowableProjectile = item instanceof EnderPearlItem ||
                                                    item instanceof EggItem ||
                                                    item instanceof SnowballItem ||
                                                    item instanceof FireChargeItem ||
                                                    item instanceof BoneMealItem ||
                                                    item instanceof WindChargeItem ||
                                                    item instanceof ChaosOrbItem;
                    boolean isDye = item instanceof DyeItem;
                    boolean isBoat = item instanceof BoatItem;
                    boolean isInkSac = item instanceof InkSacItem || item instanceof GlowInkSacItem;
                    boolean isMinecart = item instanceof MinecartItem;
                    if (isBlock || isBucket || isSpawnEgg || isThrowableProjectile || isDye || isBoat || isInkSac || isMinecart) {
                        items.add(item);
//                    }
//                }
            }
        }

        Item chosenItem = items.get(random.nextInt(items.size()-1));
        if (this.getOwner() != null) {
            this.getOwner().sendMessage(Text.of(chosenItem.getName()));
        }
        infiniteItem.set(ModDataComponentTypes.CHOSEN_INFINITE_ITEM, chosenItem.getDefaultStack());
        this.dropStack(infiniteItem, 0);
    }

    private void getEnchantedBook(HitResult hitResult) {
        ItemStack enchantedBook = Items.ENCHANTED_BOOK.getDefaultStack();
        List<Enchantment> enchantments = this.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).stream().toList();
        Enchantment enchantment = enchantments.get(random.nextBetween(0, enchantments.size() - 1));
        RegistryEntry<Enchantment> enchantmentEntry = this.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(enchantment);
        enchantedBook.addEnchantment(enchantmentEntry, random.nextBetween(1, enchantment.getMaxLevel()));
        this.dropStack(enchantedBook, 0);
    }

    private void xp(HitResult hitResult) {
        //float prizeMultiplier = (int) Math.pow((random.nextFloat()+1),5);
        //ExperienceOrbEntity.spawn((ServerWorld) this.getWorld(), this.getPos(), (int) (random.nextBetween(32, 128) * prizeMultiplier));
        // El segundo boostea ligeramente las chances para arriba
        ExperienceOrbEntity.spawn((ServerWorld) this.getWorld(), this.getPos(), (int) Math.pow(Math.min(random.nextBetween(8,32), random.nextBetween(16,32)),3));
    }

    private void smallPrize(HitResult hitResult) {
        List<ItemStack> prizes = new ArrayList<>();
        prizes.add(new ItemStack(Items.OAK_LOG, 64));
        prizes.add(new ItemStack(Items.BONE, 48));
        prizes.add(new ItemStack(Items.COAL, 64));
        prizes.add(new ItemStack(Items.STONE, 64));
        prizes.add(new ItemStack(Items.SOUL_SAND, 64));
        prizes.add(new ItemStack(Items.MAGMA_BLOCK, 64));
        prizes.add(new ItemStack(Items.OBSIDIAN, 20));
        prizes.add(new ItemStack(Items.BLUE_BED, 1));
        prizes.add(new ItemStack(Items.ENDER_PEARL, 16));
        prizes.add(new ItemStack(Items.POINTED_DRIPSTONE, 12));
        prizes.add(new ItemStack(Items.TURTLE_HELMET, 1));
        prizes.add(new ItemStack(Items.SCAFFOLDING, 64));

        int nextItem = this.random.nextBetween(0, prizes.size() -1);
        ItemStack reward = prizes.get(nextItem);

        if (reward.getItem() == Items.POINTED_DRIPSTONE) {
            this.dropStack(new ItemStack(Items.LAVA_BUCKET, 1), 0);
            this.dropStack(new ItemStack(Items.WATER_BUCKET, 1), 0);
            this.dropStack(new ItemStack(Items.CAULDRON, 1), 0);
        }
        if (reward.getItem() == Items.STONE) {
            this.dropStack(new ItemStack(Items.STONE, 64), 0);
            this.dropStack(new ItemStack(Items.STONE, 64), 0);
        }

        this.dropStack(reward, 0);
    }

    private void breakGameProgression(HitResult hitResult) {
        List<ItemStack> rareItems = new ArrayList<>();
        rareItems.add(Items.ELYTRA.getDefaultStack());
        rareItems.add(Items.MACE.getDefaultStack());
        rareItems.add(Items.DRAGON_EGG.getDefaultStack());
        rareItems.add(Items.BEACON.getDefaultStack());
        rareItems.add(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE.getDefaultStack());
        rareItems.add(Items.TOTEM_OF_UNDYING.getDefaultStack());
        rareItems.add(Items.TRIDENT.getDefaultStack());
        rareItems.add(Items.SHULKER_BOX.getDefaultStack());
        rareItems.add(ModBlocks.PROTECTOR_BLOCK.asItem().getDefaultStack());

        ItemStack mendingBook = Items.ENCHANTED_BOOK.getDefaultStack();
        RegistryEntry<Enchantment> mendingEntry =
                this.getWorld().getRegistryManager()
                        .get(RegistryKeys.ENCHANTMENT)
                        .getEntry(Enchantments.MENDING)
                        .orElseThrow();
        mendingBook.addEnchantment(mendingEntry, 1);
        rareItems.add(mendingBook);

        ItemStack fortuneBook = Items.ENCHANTED_BOOK.getDefaultStack();
        RegistryEntry<Enchantment> fortuneEntry =
                this.getWorld().getRegistryManager()
                        .get(RegistryKeys.ENCHANTMENT)
                        .getEntry(Enchantments.FORTUNE)
                        .orElseThrow();
        fortuneBook.addEnchantment(fortuneEntry, 3);
        rareItems.add(fortuneBook);

        ItemStack lootingBook = Items.ENCHANTED_BOOK.getDefaultStack();
        RegistryEntry<Enchantment> lootingEntry =
                this.getWorld().getRegistryManager()
                        .get(RegistryKeys.ENCHANTMENT)
                        .getEntry(Enchantments.LOOTING)
                        .orElseThrow();
        lootingBook.addEnchantment(lootingEntry, 3);
        rareItems.add(lootingBook);

        ItemStack diamonds = Items.DIAMOND.getDefaultStack();
        diamonds.setCount(32);
        rareItems.add(diamonds);

        ItemStack goldenCarrots = Items.GOLDEN_CARROT.getDefaultStack();
        goldenCarrots.setCount(32);
        rareItems.add(goldenCarrots);

        ItemStack netherite = Items.NETHERITE_INGOT.getDefaultStack();
        netherite.setCount(2);
        rareItems.add(netherite);

        ItemStack bookshelves = Items.BOOKSHELF.getDefaultStack();
        bookshelves.setCount(15);
        rareItems.add(bookshelves);

        ItemStack goldenApples = Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack();
        goldenApples.setCount(2);
        rareItems.add(goldenApples);

        ItemStack sponges = Items.SPONGE.getDefaultStack();
        sponges.setCount(64);
        rareItems.add(sponges);

        ItemStack iron_ingots = Items.IRON_INGOT.getDefaultStack();
        iron_ingots.setCount(64);
        rareItems.add(iron_ingots);

        int nextItem = this.random.nextBetween(0, (int) rareItems.size()-1);
        ItemStack reward = rareItems.get(nextItem);

        if (reward.getItem() == Items.BOOKSHELF) {
            this.dropStack(Items.ENCHANTING_TABLE.getDefaultStack(), 0);
        }

        this.dropStack(reward, 0);
    }

    private void explosion(HitResult hitResult) {
        int kase = random.nextBetween(1, 20);

        if (kase == 20) {
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(),(float) 127.0, World.ExplosionSourceType.BLOCK);
        }
        else if (kase > 17 && this.getOwner() != null) {
            this.getWorld().createExplosion(this, this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ(),(float) 1.0, World.ExplosionSourceType.BLOCK);
        }
        else {
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(),(float) 5.0, World.ExplosionSourceType.BLOCK);
        }
    }

    private void fireExplosion(HitResult hitResult) {
        int kase = random.nextBetween(1, 20);

        FireballEntity fireballEntity;

//        if (kase == 20) {
//            if (this.getOwner() != null) {
//                fireballEntity = new FireballEntity(this.getWorld(), (LivingEntity) this.getOwner(), new Vec3d(0, -1.0f, 0), 127);
//            }
//            else {
//                LivingEntity dummy = new PigEntity(EntityType.PIG, this.getWorld());
//                dummy.setPosition(this.getX(), this.getY(), this.getZ());
//                fireballEntity = new FireballEntity(this.getWorld(), dummy, new Vec3d(0, -1.0f, 0), 4);
//            }
//        }
//        else {
//            if (this.getOwner() != null) {
//                fireballEntity = new FireballEntity(this.getWorld(), (LivingEntity) this.getOwner(), new Vec3d(0, -1.0f, 0), 3);
//            }
//            else {
//                LivingEntity dummy = new PigEntity(EntityType.PIG, this.getWorld());
//                dummy.setPosition(this.getX(), this.getY(), this.getZ());
//                fireballEntity = new FireballEntity(this.getWorld(), dummy, new Vec3d(0, -1.0f, 0), 4);
//            }
//        }

        if (kase > 17 && kase < 20 && this.getOwner() != null) {
            fireballEntity = new FireballEntity(this.getWorld(), (LivingEntity) this.getOwner(), new Vec3d(0, -1.0f, 0), 1);
            fireballEntity.setPosition(this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ());
        }
        else {
            fireballEntity = new FireballEntity(this.getWorld(), (LivingEntity) this.getOwner(), new Vec3d(0, -1.0f, 0), 4);
            fireballEntity.setPosition(this.getX(), this.getY(), this.getZ());
        }
        this.getWorld().spawnEntity(fireballEntity);
    }

    private void spawn5ChaosOrbs(HitResult hitResult) {
        ChaosOrbEntity chaosOrbEntity = new ChaosOrbEntity(this.getWorld(), hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
        chaosOrbEntity.setOwner(this.getOwner());
        chaosOrbEntity.setVelocity( 1.0f, 2.0f, 0f, 0.5f, 0.0f);

        this.getWorld().spawnEntity(chaosOrbEntity);

        chaosOrbEntity = new ChaosOrbEntity(this.getWorld(), hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
        chaosOrbEntity.setOwner(this.getOwner());
        chaosOrbEntity.setVelocity( -1.0f, 2.0f, 0f, 0.5f, 0.0f);

        this.getWorld().spawnEntity(chaosOrbEntity);

        chaosOrbEntity = new ChaosOrbEntity(this.getWorld(), hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
        chaosOrbEntity.setOwner(this.getOwner());
        chaosOrbEntity.setVelocity( 0f, 2.0f, 1.0f, 0.5f, 0.0f);

        this.getWorld().spawnEntity(chaosOrbEntity);

        chaosOrbEntity = new ChaosOrbEntity(this.getWorld(), hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
        chaosOrbEntity.setOwner(this.getOwner());
        chaosOrbEntity.setVelocity( 0f, 2.0f, -1.0f, 0.5f, 0.0f);

        this.getWorld().spawnEntity(chaosOrbEntity);

        chaosOrbEntity = new ChaosOrbEntity(this.getWorld(), hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
        chaosOrbEntity.setOwner(this.getOwner());
        chaosOrbEntity.setVelocity( 0f, 2.0f, 0.0f, 0f, 0f);

        this.getWorld().spawnEntity(chaosOrbEntity);
    }

    private void voidSphere(HitResult hitResult) {
        float randomNumber = random.nextFloat();
        while (randomNumber < 0.0000000001f) {
            randomNumber = random.nextFloat();
        }
        int radius = Math.max((int) (-1 * (5.6f * Math.log(randomNumber * 1369)/Math.log(1.375f) - 127)), 10);
        World world = this.getWorld();
        BlockPos center = new BlockPos(new Vec3i((int) hitResult.getPos().x, (int) hitResult.getPos().y, (int) hitResult.getPos().z));

        if (this.getOwner() != null) {
            TerrainManager.TERRAIN_MANAGER.addJob(new SingleBlockSphereJob(world, (PlayerEntity) this.getOwner(), center, radius, Blocks.AIR));
            StatusEffectInstance slowFall = new StatusEffectInstance(StatusEffects.SLOW_FALLING, radius * 4, 0);
            ((LivingEntity) this.getOwner()).addStatusEffect(slowFall);
        }
    }
    // TODO
    private void createSkyblock(HitResult hitResult) {

        if (this.getServer() != null) {
            List<ServerPlayerEntity> players = this.getServer().getPlayerManager().getPlayerList();

            World world = this.getWorld();

            int tpX = random.nextInt(1) == 0 ? random.nextBetween(10000, 20000) : random.nextBetween(-20000, -10000);
            int tpZ = random.nextInt(1) == 0 ? random.nextBetween(10000, 20000) : random.nextBetween(-20000, -10000);

            for (ServerPlayerEntity playerEntity : players) {
                if (playerEntity == null) {
                    continue;
                }
                StatusEffectInstance slowFall = new StatusEffectInstance(StatusEffects.SLOW_FALLING, 600, 0);
                playerEntity.addStatusEffect(slowFall);

                ServerWorld dimension = (ServerWorld) world;
                TeleportTarget teleportTarget = new TeleportTarget(dimension,
                        new Vec3d(tpX,
                                150,
                                tpZ),
                        new Vec3d(0, 0, 0),
                        0,
                        0,
                        TeleportTarget.NO_OP);
                playerEntity.teleportTo(teleportTarget);
                BlockPos asd = new BlockPos((int) playerEntity.getX() - (int) playerEntity.getX() % 16 + 11, 66, (int) playerEntity.getZ() - (int) playerEntity.getZ() % 16 + 11);
                playerEntity.setSpawnPoint(playerEntity.getWorld().getRegistryKey(), asd, 0, true, false);
            }

            if (this.getOwner() != null) {
                ChunkPos islandChunkPos = new ChunkPos(tpX >> 4, tpZ >> 4);

                for (int x = -1 ; x < 2 ; x++) {
                    for (int z = -1 ; z < 2 ; z++) {
                        ChunkPos chunkPos = new ChunkPos(islandChunkPos.x + x, islandChunkPos.z + z);

                        ServerChunkManager chunkManager = (ServerChunkManager) world.getChunkManager();
                        WorldChunk chunk = world.getChunk(chunkPos.x, chunkPos.z);

                        ChunkSection[] sections = chunk.getSectionArray();
                        Registry<Biome> registry = world.getRegistryManager().get(RegistryKeys.BIOME);
                        ReadableContainer<RegistryEntry<Biome>> readableContainer = new PalettedContainer<>(registry.getIndexedEntries(), registry.entryOf(BiomeKeys.SNOWY_TAIGA), PalettedContainer.PaletteProvider.BIOME);

                        Box chunkBox = new Box(chunkPos.getStartX(), 0, chunkPos.getStartZ(), chunkPos.getEndX() + 1, world.getHeight(), chunkPos.getEndZ() + 1);
                        for (Entity entity : world.getOtherEntities(this.getOwner(), chunkBox)) {
                            entity.remove(Entity.RemovalReason.DISCARDED);
                        }

                        chunk.clear();
                        for (int i = 0 ; i < sections.length ; i++) {
                            ChunkSection empty = new ChunkSection(new PalettedContainer<>(Block.STATE_IDS, Blocks.AIR.getDefaultState(), PalettedContainer.PaletteProvider.BLOCK_STATE), readableContainer);
                            sections[i] = empty;
                        }

                        chunk.setLightOn(false);
                        ServerLightingProvider lighting = chunkManager.getLightingProvider();
                        lighting.enqueue(chunkPos.x, chunkPos.z);
                        chunk.setNeedsSaving(true);

                        ChunkDataS2CPacket packet = new ChunkDataS2CPacket(chunk, chunkManager.getLightingProvider(), null, null);
                        chunkManager.sendToNearbyPlayers(this.getOwner(), packet);
                    }
                }

                ChunkPos chunkPos = new ChunkPos(islandChunkPos.x, islandChunkPos.z);
                MinecraftServer server = world.getServer();
                if (server != null) {
                    StructureTemplateManager structureManager = world.getServer().getStructureTemplateManager();
                    StructureTemplate template = structureManager.getTemplateOrBlank(Identifier.of(MoreMineralBlocks.MOD_ID, "skyblock_start"));

                    StructurePlacementData settings = new StructurePlacementData()
                            .setRotation(BlockRotation.NONE)
                            .setMirror(BlockMirror.NONE)
                            .setIgnoreEntities(false);


                    template.place((ServerWorldAccess) world, chunkPos.getBlockPos(4, 63, 4), chunkPos.getBlockPos(4,63,4), settings, world.getRandom(), 50);
                }
                TerrainManager.TERRAIN_MANAGER.addJob(new SkyBlockJob(world, chunkPos, (PlayerEntity) this.getOwner()));

                MoreMineralBlocks.getStateSaverAndLoader(this.getServer()).setSkyBlockHappened(true);
            }
        }
    }

//    private void createStructure(HitResult hitResult) {
//        hitResult.getPos();
//    }

    private void getToolsSet(HitResult hitResult) {
        int material = random.nextBetween(0, 4);

        switch (material) {
            case 0:
                this.dropStack(Items.WOODEN_AXE.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.WOODEN_HOE.getDefaultStack(), 0);
                break;
            case 1:
                this.dropStack(Items.STONE_AXE.getDefaultStack(), 0);
                this.dropStack(Items.STONE_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.STONE_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.STONE_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.STONE_HOE.getDefaultStack(), 0);
                break;
            case 2:
                this.dropStack(Items.IRON_AXE.getDefaultStack(), 0);
                this.dropStack(Items.IRON_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.IRON_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.IRON_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.IRON_HOE.getDefaultStack(), 0);
                break;
            case 3:
                this.dropStack(Items.GOLDEN_AXE.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_HOE.getDefaultStack(), 0);
                break;
            case 4:
                this.dropStack(Items.DIAMOND_AXE.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_SHOVEL.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_SWORD.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_PICKAXE.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_HOE.getDefaultStack(), 0);
                break;
        }
    }

    private void getArmorSet(HitResult hitResult) {
        int material = random.nextBetween(0, 4);

        switch (material) {
            case 0:
                this.dropStack(Items.LEATHER_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.LEATHER_BOOTS.getDefaultStack(), 0);
                break;
            case 1:
                this.dropStack(Items.IRON_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.IRON_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.IRON_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.IRON_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.IRON_BOOTS.getDefaultStack(), 0);
                break;
            case 2:
                this.dropStack(Items.GOLDEN_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.GOLDEN_BOOTS.getDefaultStack(), 0);
                break;
            case 3:
                this.dropStack(Items.DIAMOND_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_HORSE_ARMOR.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.DIAMOND_BOOTS.getDefaultStack(), 0);
                break;
            case 4:
                this.dropStack(Items.CHAINMAIL_HELMET.getDefaultStack(), 0);
                this.dropStack(Items.CHAINMAIL_CHESTPLATE.getDefaultStack(), 0);
                this.dropStack(Items.CHAINMAIL_LEGGINGS.getDefaultStack(), 0);
                this.dropStack(Items.CHAINMAIL_BOOTS.getDefaultStack(), 0);
                break;
        }
    }

    private void fragile(HitResult hitResult, Box boundingBox) {
        List<LivingEntity> entities = this.getWorld().getNonSpectatingEntities(LivingEntity.class, boundingBox.expand(4.0, 2.0, 4.0));
        ((ServerWorld) this.getWorld()).spawnParticles(ModParticles.CHAOS_ORB_FRAGILE_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.FRAGILE, 6000, 0);
            if (entity != null) {
                entity.addStatusEffect(effect);
            }
        }
    }

    private void counterBlinking(HitResult hitResult, Box boundingBox) {
        List<LivingEntity> entities = this.getWorld().getNonSpectatingEntities(LivingEntity.class, boundingBox.expand(16.0, 8.0, 16.0));
        if (entities.isEmpty() && this.getOwner() != null) {
            entities.add((LivingEntity) this.getOwner());
        }

        ((ServerWorld) this.getWorld()).spawnParticles(ModParticles.CHAOS_ORB_COUNTER_BLINK_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.COUNTER_BLINK, 9600, 0);
            if (entity != null) {
                entity.addStatusEffect(effect);
            }
        }
    }

    private void blinking(HitResult hitResult, Box boundingBox) {
        List<LivingEntity> entities = this.getWorld().getNonSpectatingEntities(LivingEntity.class, boundingBox.expand(8.0, 4.0, 8.0));
        if (entities.isEmpty() && this.getOwner() != null) {
            entities.add((LivingEntity) this.getOwner());
        }
        ((ServerWorld) this.getWorld()).spawnParticles(ModParticles.CHAOS_ORB_BLINKING_PARTICLE,this.getX(), this.getY(), this.getZ(), 1, 0.0, 1.0, 0.0, 1.0);

        for (LivingEntity entity : entities) {
            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.BLINKING, 1200, 0);
            if (entity != null) {
                entity.addStatusEffect(effect);
            }
        }
    }

//    private void adventureGamemode(HitResult hitResult, List<LivingEntity> entities) {
//        for (LivingEntity entity : entities) {
//            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.TELEPORTIS_DODGE, 72000, 1);
//            entity.addStatusEffect(effect);
//        }
//    }

//    private void onanaHands(HitResult hitResult, List<LivingEntity> entities) {
//        for (LivingEntity entity : entities) {
//            StatusEffectInstance effect = new StatusEffectInstance(ModEffects.ONANA_HANDS, 72000, 0);
//            entity.addStatusEffect(effect);
//        }
//    }

    private void getMythicItem(HitResult hitResult) {
        List<ItemStack> mythicItems = new ArrayList<>();

        mythicItems.add(ModItems.LEGENDARY_PICKAXE.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_SHOVEL.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_AXE.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_HOE.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_SWORD.getDefaultStack());
        mythicItems.add(ModItems.LEGENDARY_ROCKET.getDefaultStack());
        mythicItems.add(ModItems.SURVIVAL_DEBUG_STICK.getDefaultStack());
        mythicItems.add(ModItems.FLASH.getDefaultStack());
        mythicItems.add(Items.LIGHT.getDefaultStack());

        ItemStack bedrock = Items.BEDROCK.getDefaultStack();
        bedrock.setCount(64);
        mythicItems.add(bedrock);

        ItemStack reinforcedDeepslate = Items.REINFORCED_DEEPSLATE.getDefaultStack();
        reinforcedDeepslate.setCount(64);
        mythicItems.add(reinforcedDeepslate);

        ItemStack endPortalFrame = Items.END_PORTAL_FRAME.getDefaultStack();
        endPortalFrame.setCount(12);
        mythicItems.add(endPortalFrame);

        ItemStack buddingAmethyst = Items.BUDDING_AMETHYST.getDefaultStack();
        buddingAmethyst.setCount(64);
        mythicItems.add(buddingAmethyst);

//        ItemStack lootingBook = Items.ENCHANTED_BOOK.getDefaultStack();
//        RegistryEntry<Enchantment> lootingEntry =
//                this.getWorld().getRegistryManager()
//                        .get(RegistryKeys.ENCHANTMENT)
//                        .getEntry(Enchantments.LOOTING)
//                        .orElseThrow();
//        lootingBook.addEnchantment(lootingEntry, 5);
//        mythicItems.add(lootingBook);

        ItemStack trialSpawner = Items.TRIAL_SPAWNER.getDefaultStack();
        mythicItems.add(trialSpawner);

        ItemStack spawner = Items.SPAWNER.getDefaultStack();
        mythicItems.add(spawner);

        List<ItemStack> spawnEggs = ((ChaosOrbItem) (this.getDefaultItem())).getOrCreateSpawnEggList();

        int nextEgg = this.random.nextBetween(0, (int) spawnEggs.size()-1);
        mythicItems.add(spawnEggs.get(nextEgg));

        int nextItem = this.random.nextBetween(0, (int) mythicItems.size()-1);
        ItemStack reward = mythicItems.get(nextItem);

        if (reward.getItem() == Items.TRIAL_SPAWNER || reward.getItem() == Items.SPAWNER) {
            ItemStack newEgg = spawnEggs.get(nextEgg);
            newEgg.setCount(1);
            this.dropStack(newEgg, 0);
        }
        this.dropStack(reward, 0);
    }
}

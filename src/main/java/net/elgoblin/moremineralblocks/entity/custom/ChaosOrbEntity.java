package net.elgoblin.moremineralblocks.entity.custom;

import com.mojang.datafixers.util.Pair;
import net.elgoblin.moremineralblocks.entity.ModEntities;
import net.elgoblin.moremineralblocks.item.ModItems;
import net.elgoblin.moremineralblocks.item.custom.ChaosOrbItem;
import net.elgoblin.moremineralblocks.util.ProtectorManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ChaosOrbEntity extends ThrowableItemProjectile {

    private ServerLevel level = null;
    private final RandomSource random = RandomSource.create();
    private String seededEvent = "none";
    private final Map<String, Pair<Integer, Integer>> eventMap = Map.ofEntries(
//            Map.entry("mobpack", new Pair<>(0,0)),
            Map.entry("mythicitem", new Pair<>(0,0)),
//            Map.entry("mythicitem", new Pair<>(0,1)),
//            Map.entry("skeletonhorse", new Pair<>(0,2)),
//            Map.entry("progression", new Pair<>(0,3)),
//            Map.entry("armor", new Pair<>(0,4)),
//            Map.entry("tools", new Pair<>(0,5)),
//            Map.entry("chaos", new Pair<>(0,6)),
//            Map.entry("terrainsphere", new Pair<>(0,7)),
//            Map.entry("explosion", new Pair<>(0,8)),
//            Map.entry("fireexplosion", new Pair<>(0,9)),
//            Map.entry("food", new Pair<>(0,10)),
//            Map.entry("book", new Pair<>(0,11)),
//            Map.entry("prize", new Pair<>(0, 12)),
//            Map.entry("xp", new Pair<>(0, 13)),

            Map.entry("smallBoing", new Pair<>(1,0))
//            Map.entry("beacon", new Pair<>(1,0)),

//            Map.entry("range", new Pair<>(2, 0)),
//            Map.entry("fragile", new Pair<>(2,1)),
//
//            Map.entry("storm", new Pair<>(4,0)),
//            Map.entry("teleport", new Pair<>(4,1)),
//
//            Map.entry("counterBlink", new Pair<>(5,0)),
//            Map.entry("blinking", new Pair<>(5,1)),
//            Map.entry("20", new Pair<>(5,2)),
//            Map.entry("scale", new Pair<>(5,3)),
//
//            Map.entry("help", new Pair<>(6,0)),
//            Map.entry("skyblock", new Pair<>(6,1))
    );

    private boolean tunneler = false;
    private LinkedList<BlockPos> tunnelQueue = new LinkedList<>();

    private List<Consumer<HitResult>> pointChaosEffects = new ArrayList<>(List.of(
//            this::spawnMobPack,
            this::getMythicItem
//            this::spawnSkeletonHorse,
//            this::breakGameProgression,
//            this::getArmorSet,
//            this::getToolsSet,
//            this::spawn5ChaosOrbs,
//            this::voidSphere,
//            this::explosion, // Ponerle timer
//            this::fireExplosion, // Ponerle timer
//            this::getFood,
//            this::getEnchantedBook,
//            this::smallPrize,
//            this::xp
//            this::getInfiniteItem
    ));
    private List<BiConsumer<HitResult, AABB>> areaChaosEffects = new ArrayList<>(List.of(
            this::smallBoing
//            this::applyBeaconEffect
    ));
    private List<BiConsumer<HitResult, AABB>> selfAreaChaosEffects = new ArrayList<>(List.of(
//            this::increaseInteractionRange,
//            this::fragile
    ));
    private List<Consumer<HitResult>> selfChaosEffects = new ArrayList<>(List.of(
//            this::crash
    ));
    private List<Consumer<HitResult>> globalChaosEffects = new ArrayList<>(List.of(
//            this::beginThunderstorm,
//            this::randomizePlayersPositions
//            this::createSkyblock
            // Skyblock se va a ir agregando en cada llamado hasta que salga una vez.
    ));
    private List<BiConsumer<HitResult, AABB>> targetsOrSelfChaosEffects = new ArrayList<>(List.of(
//            this::counterBlinking,
////            this::adventureGamemode
////            this::onanaHands,
//            this::blinking,
//            this::moveXBlocks,
//            this::changeScale
    ));

    private List<Consumer<HitResult>> debugChaosEffects = new ArrayList<>(List.of(
//            this::debugHelp,
//            this::createSkyblock
    ));

    //

    private int nextCategory() {

        int pointChaosEffectsInterval = pointChaosEffects.size();
        int areaChaosEffectsInterval = pointChaosEffectsInterval + areaChaosEffects.size();
        int selfAreaChaosEffectsInterval = areaChaosEffectsInterval + selfAreaChaosEffects.size();
        int selfChaosEffectsInterval = selfAreaChaosEffectsInterval + selfChaosEffects.size();
        int globalChaosEffectsInterval = selfChaosEffectsInterval + globalChaosEffects.size();
        int targetsOrSelfChaosEffectsInterval = globalChaosEffectsInterval + targetsOrSelfChaosEffects.size();


        int category = this.random.nextIntBetweenInclusive(0, targetsOrSelfChaosEffectsInterval-1);

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
        } else { // Targets or Self Chaos Effects Interval
            return 5;
        }
    }

    public ChaosOrbEntity(final EntityType<? extends ChaosOrbEntity> type, final Level level) {
        super(type, level);
        this.tunneler = random.nextIntBetweenInclusive(0, this.effectCount()) == 0;
        if (!level.isClientSide()) {
            this.level = (ServerLevel) level;
        }
    }

    public ChaosOrbEntity(final Level level, final LivingEntity mob, final ItemStack itemStack) {
        super(ModEntities.CHAOS_ORB, mob, level, itemStack);
        this.tunneler = random.nextIntBetweenInclusive(0, this.effectCount()) == 0;
        if (!level.isClientSide()) {
            this.level = (ServerLevel) level;
        }
        if (itemStack.getCustomName() != null) {
            seededEvent = itemStack.getCustomName().getString().toLowerCase();
            if (itemStack.getCustomName().getString().equalsIgnoreCase("tunneler")) {
                tunneler = true;
            }
            else {
                tunneler = false;
            }
        }
    }

    public ChaosOrbEntity(final Level level, final double x, final double y, final double z, final ItemStack itemStack) {
        super(ModEntities.CHAOS_ORB, x, y, z, level, itemStack);
        this.tunneler = random.nextIntBetweenInclusive(0, this.effectCount()) == 0;
        if (!level.isClientSide()) {
            this.level = (ServerLevel) level;
        }
        if (itemStack.getCustomName() != null) {
            seededEvent = itemStack.getCustomName().getString().toLowerCase();
            if (itemStack.getCustomName().getString().equalsIgnoreCase("tunneler")) {
                tunneler = true;
            }
            else {
                tunneler = false;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tunneler) {
            if (this.level().isClientSide()) { return; }

            BlockPos center = new BlockPos(new Vec3i((int) this.getX(), (int) this.getY(), (int) this.getZ()));

            for (int x = -5; x <= 5; x++) {
                for (int z = -5; z <= 5; z++) {
                    for (int y = -5; y <= 5; y++) {
                        if (x * x + y * y + z * z > 25) {
                            continue;
                        }
                        BlockPos currentBlock = center.offset(x, y, z);
                        this.tunnelQueue.add(currentBlock);
                    }
                }
            }
            while (!tunnelQueue.isEmpty()) {
                BlockPos blockToRemove = tunnelQueue.pop();
                BlockState currentState = level.getBlockState(blockToRemove);

                MinecraftServer server = level.getServer();
                ProtectorManager protectorManager = ProtectorManager.getProtectorManager(server);

                if (!currentState.isAir() && !protectorManager.isProtected(blockToRemove)) {
                    level.setBlock(blockToRemove, Blocks.AIR.defaultBlockState(), 50);
                }
            }
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

    private ParticleOptions getParticle() {
        ItemStack item = this.getItem();
        return item.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(item));
    }

    @Override
    public void handleEntityEvent(final byte id) {
        if (id == 3) {
            ParticleOptions particle = this.getParticle();

            for (int i = 0; i < 8; i++) {
                this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onHitEntity(final EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity entity = hitResult.getEntity();
        double knockback = random.nextIntBetweenInclusive(1,20);
        knockback = knockback * 0.4;

        if (this.getOwner() == null) { return; }

        double x = entity.getX() - this.getOwner().getX();
        double z = entity.getZ() - this.getOwner().getZ();

        double distance = Math.sqrt(x * x + z * z);
        x = x / distance;
        z = z / distance;

        if (distance > 0) {
            entity.push(x * knockback, 0.2, z * knockback);
        }
    }

    @Override
    protected void onHit(final HitResult hitResult) {
        super.onHit(hitResult);
        if (this.level().isClientSide()) { return; }

        level.broadcastEntityEvent(this, (byte) 3);

        int eventCategory = nextCategory();
        int nextEffect;
        AABB boundingBox = this.getBoundingBox();

        if (eventMap.containsKey(seededEvent)) {
            eventCategory = eventMap.get(seededEvent).getFirst();
        }

        switch (eventCategory) {
            case 0: // POINT
                // Deberia pasar siempre pero si en algun momento llega a no pasar, crashearia y seria irrecuperable el mundo salvo tocar NBTs
                if (!pointChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, pointChaosEffects.size()-1);
                    }
                    pointChaosEffects.get(nextEffect).accept(hitResult);
                }
                break;
            case 1: //AREA
                if (!areaChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, areaChaosEffects.size()-1);
                    }
                    areaChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                }
                break;
            case 2: //SELF AREA
                if (!selfAreaChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, selfAreaChaosEffects.size()-1);
                    }
                    selfAreaChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                }
                break;
            case 3: //SELF
                if (!selfChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, selfChaosEffects.size()-1);
                    }
                    selfChaosEffects.get(nextEffect).accept(hitResult);
                }
                break;
            case 4: //GLOBAL
                if (!globalChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, globalChaosEffects.size()-1);
                    }
                    globalChaosEffects.get(nextEffect).accept(hitResult);
                }
                break;
            case 5: //TARGET SELF
                if (!targetsOrSelfChaosEffects.isEmpty()) {
                    if (eventMap.containsKey(seededEvent)) {
                        nextEffect = eventMap.get(seededEvent).getSecond();
                    }
                    else {
                        nextEffect = random.nextIntBetweenInclusive(0, targetsOrSelfChaosEffects.size()-1);
                    }
                    targetsOrSelfChaosEffects.get(nextEffect).accept(hitResult, boundingBox);
                }
                break;
            case 6: // DEBUG
                nextEffect = 0;
                if (eventMap.containsKey(seededEvent)) {
                    nextEffect = eventMap.get(seededEvent).getSecond();
                }
                debugChaosEffects.get(nextEffect).accept(hitResult);
        }

        this.discard();
    }

    // EVENTS

    private void smallBoing(HitResult hitResult, AABB boundingBox) {
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox.inflate(32.0, 32.0, 32.0), EntitySelector.NO_SPECTATORS);

        double knockback = 10;

        double chaosOrbX = this.getX();
        double chaosOrbY = this.getY();
        double chaosOrbZ = this.getZ();
        double x;
        double y;
        double z;
        double entityX;
        double entityY;
        double entityZ;

        for (LivingEntity entity : entities) {
            entityX = entity.getX();
            entityY = entity.getY();
            entityZ = entity.getZ();

            x = entityX - chaosOrbX;
            y = entityY - chaosOrbY;
            z = entityZ - chaosOrbZ;

            double distance = Math.sqrt(x*x + y*y + z*z);

            x = x / distance;
            y = y / distance;
            z = z / distance;

            if (distance > 0) {
                entity.push(x * knockback, y * 5, z * knockback);
                entity.hurtMarked = true;
            }
        }
    }
    private void getMythicItem(HitResult hitResult) {
        List<ItemStack> mythicItems = new ArrayList<>();

        ItemStack light = Items.LIGHT.getDefaultInstance();
        light.setCount(32);
        mythicItems.add(light);

        ItemStack bedrock = Items.BEDROCK.getDefaultInstance();
        bedrock.setCount(64);
        mythicItems.add(bedrock);

        ItemStack reinforcedDeepslate = Items.REINFORCED_DEEPSLATE.getDefaultInstance();
        reinforcedDeepslate.setCount(64);
        mythicItems.add(reinforcedDeepslate);

        ItemStack endPortalFrame = Items.END_PORTAL_FRAME.getDefaultInstance();
        endPortalFrame.setCount(12);
        mythicItems.add(endPortalFrame);

        ItemStack buddingAmethyst = Items.BUDDING_AMETHYST.getDefaultInstance();
        buddingAmethyst.setCount(64);
        mythicItems.add(buddingAmethyst);

        ItemStack trialSpawner = Items.TRIAL_SPAWNER.getDefaultInstance();
        mythicItems.add(trialSpawner);

        ItemStack spawner = Items.SPAWNER.getDefaultInstance();
        mythicItems.add(spawner);

        List<ItemStack> spawnEggs = ((ChaosOrbItem) (this.getDefaultItem())).getOrCreateSpawnEggList();

        mythicItems.add(ModItems.LEGENDARY_PICKAXE.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_SHOVEL.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_AXE.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_HOE.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_SWORD.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_LONGSWORD.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_SPEAR.getDefaultInstance());
        mythicItems.add(ModItems.LEGENDARY_ROCKET.getDefaultInstance());
//        mythicItems.add(ModItems.SURVIVAL_DEBUG_STICK.getDefaultStack());
        mythicItems.add(ModItems.DIMENSIONAL_POCKET.getDefaultInstance());
        mythicItems.add(ModItems.FLASH.getDefaultInstance());

        int nextItem = this.random.nextIntBetweenInclusive(0, mythicItems.size() -1);
        ItemStack reward = mythicItems.get(nextItem);

        int nextEgg = this.random.nextIntBetweenInclusive(0, spawnEggs.size() -1);
        mythicItems.add(spawnEggs.get(nextEgg));

        if (reward.getItem() == Items.TRIAL_SPAWNER || reward.getItem() == Items.SPAWNER) {
            ItemStack newEgg = spawnEggs.get(nextEgg);
            newEgg.setCount(1);
            this.spawnAtLocation(level, newEgg, 0);
        }

        this.spawnAtLocation(level, reward, 0);
    }

}

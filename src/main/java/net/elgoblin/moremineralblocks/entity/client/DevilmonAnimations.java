package net.elgoblin.moremineralblocks.entity.client;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class DevilmonAnimations {

    public static final Animation ANIM_DEVILMON_FLY = Animation.Builder.create(0.75f)
            .addBoneAnimation("WingL",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, -30f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.45833f, AnimationHelper.createRotationalVector(-56.75f, -9.08f, -32.22f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("WingR",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 30f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.45833f, AnimationHelper.createRotationalVector(-56.75f, 9.08f, 32.22f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
}

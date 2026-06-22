package com.cocktail.cocktailmod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import tocraft.walkers.api.PlayerShape;

public class SkeletonMorph extends MobEffect {
    public SkeletonMorph(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            Skeleton skeleton = new Skeleton(EntityType.SKELETON, player.serverLevel());
            PlayerShape.updateShapes(player, skeleton);
        }
    }
}
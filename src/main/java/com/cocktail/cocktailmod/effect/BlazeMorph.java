package com.cocktail.cocktailmod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import tocraft.walkers.api.PlayerShape;

public class BlazeMorph extends MobEffect {
    public BlazeMorph(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            Blaze blaze = new Blaze(EntityType.BLAZE, player.serverLevel());
            PlayerShape.updateShapes(player, blaze);
        }
    }
}
package com.cocktail.cocktailmod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Spider;
import tocraft.walkers.api.PlayerShape;

public class SpiderMorph extends MobEffect {
    public SpiderMorph(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            Spider spider = new Spider(EntityType.SPIDER, player.serverLevel());
            PlayerShape.updateShapes(player, spider);
        }
    }
}
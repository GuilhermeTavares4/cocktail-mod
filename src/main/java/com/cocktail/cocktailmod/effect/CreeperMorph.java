package com.cocktail.cocktailmod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import tocraft.walkers.api.PlayerShape;

public class CreeperMorph extends MobEffect {
    public CreeperMorph(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
//            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.06);
            Creeper creeper = new Creeper(EntityType.CREEPER, player.serverLevel());
            PlayerShape.updateShapes(player, creeper);
        }
    }
}
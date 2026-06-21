package com.cocktail.cocktailmod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Zombie;
import tocraft.walkers.api.PlayerShape;

public class EndermanMorph extends MobEffect {
    public EndermanMorph(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            EnderMan enderman = new EnderMan(EntityType.ENDERMAN, player.serverLevel());
            PlayerShape.updateShapes(player, enderman);
        }
    }
}
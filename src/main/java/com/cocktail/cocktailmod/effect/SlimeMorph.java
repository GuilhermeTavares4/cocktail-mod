package com.cocktail.cocktailmod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import tocraft.walkers.api.PlayerShape;

public class SlimeMorph extends MobEffect {
    public SlimeMorph(MobEffectCategory category, int color) { super(category, color); }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
//            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.03);
//            player.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(2.0);
            Slime slime = new Slime(EntityType.SLIME, player.serverLevel());
            slime.setSize(2, false);
            PlayerShape.updateShapes(player, slime);
        }
    }
}
package com.cocktail.cocktailmod.effect;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import tocraft.walkers.api.PlayerShape;

public class ZombieMorph extends MobEffect {
    public ZombieMorph(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            Zombie zombie = new Zombie(EntityType.ZOMBIE, player.serverLevel());
            PlayerShape.updateShapes(player, zombie);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
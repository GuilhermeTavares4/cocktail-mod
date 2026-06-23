package com.cocktail.cocktailmod.mixin; // Replace with your actual package

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tocraft.walkers.ability.impl.generic.ExplosionAbility;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;

@Mixin(ExplosionAbility.class)
public class ExplosionAbilityMixin {

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void modifyExplosion(Player player, LivingEntity shape, Level world, CallbackInfo ci) {
        world.explode(null, player.getX(), player.getY(), player.getZ(), 30.0F, true, ExplosionInteraction.TNT);
        ci.cancel();
    }
}
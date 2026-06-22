package com.cocktail.cocktailmod.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tocraft.walkers.ability.ShapeAbility;

@Mixin(ShapeAbility.class)
public class ShootArrowAbilityCooldownMixin {
    @Inject(method = "getCooldown", at = @At("HEAD"), cancellable = true)
    private void overrideCooldown(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (entity instanceof Skeleton) { cir.setReturnValue(20); }
    }
}

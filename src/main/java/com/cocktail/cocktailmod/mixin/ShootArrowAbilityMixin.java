package com.cocktail.cocktailmod.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tocraft.walkers.ability.impl.generic.GetItemAbility;
import com.cocktail.cocktailmod.ability.ShootArrowAbility;

@Mixin(GetItemAbility.class)
public class ShootArrowAbilityMixin {
    // substitui habilidade de ganhar 4 flechas por atirar flechas se tiver arco na mão
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void intercept(Player player, LivingEntity shape, Level level, CallbackInfo ci) {
        if (!(shape instanceof Skeleton skeleton)) return;
        new ShootArrowAbility().onUse(player, skeleton, level);
        ci.cancel();
    }
}
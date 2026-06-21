package com.cocktail.cocktailmod.event;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.effect.MorphEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import tocraft.walkers.network.impl.SwapPackets;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.Holder;

@EventBusSubscriber(modid = CocktailMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onMobEffectApplicable(MobEffectEvent.Applicable event) {
        if (!(event.getEntity() instanceof Player player)) return;

        Holder<MobEffect> newEffect = event.getEffectInstance().getEffect();
        boolean isNewEffectAMorph = newEffect.value().getDescriptionId().contains("morph");

        if (isNewEffectAMorph) {
            for (var activeEffectInstance : player.getActiveEffects()) {
                Holder<MobEffect> currentActiveEffect = activeEffectInstance.getEffect();
                boolean isActiveEffectAMorph = currentActiveEffect.value().getDescriptionId().contains("morph");

                if (isActiveEffectAMorph) {
                    player.removeEffect(currentActiveEffect);
                    SwapPackets.sendSwapRequest();
                    break;
                }
            }
        }
    }
    @SubscribeEvent
    public static void onMobEffectExpired(MobEffectEvent.Expired event) {
        if (!(event.getEntity() instanceof Player player)) return;

        var effect = event.getEffectInstance().getEffect();
        if (effect.toString().contains("morph")) { SwapPackets.sendSwapRequest(); } // remove morph

        if (effect == MorphEffects.ZOMBIE_MORPH
        || effect == MorphEffects.CREEPER_MORPH) {
            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
        }
    }
}

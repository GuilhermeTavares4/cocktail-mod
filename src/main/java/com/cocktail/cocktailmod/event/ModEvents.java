package com.cocktail.cocktailmod.event;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.effect.MorphEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import tocraft.walkers.network.impl.SwapPackets;

@EventBusSubscriber(modid = CocktailMod.MOD_ID)
public class ModEvents {
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

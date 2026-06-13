package com.cocktail.cocktailmod.event;

import com.cocktail.cocktailmod.CocktailMod;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import tocraft.walkers.network.impl.SwapPackets;

@EventBusSubscriber(modid = CocktailMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onMobEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof Player player && event.getEffectInstance().toString().equals("effect.cocktailmod.zombie_morph, Duration: 0")) {
            SwapPackets.sendSwapRequest();
        }
    }
}

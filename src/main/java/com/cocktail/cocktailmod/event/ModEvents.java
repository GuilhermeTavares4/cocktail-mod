package com.cocktail.cocktailmod.event;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.effect.MorphEffects;
import com.cocktail.cocktailmod.effect.ZombieMorph;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import tocraft.walkers.network.impl.SwapPackets;

import static com.cocktail.cocktailmod.effect.MorphEffects.ZOMBIE_MORPH;

@EventBusSubscriber(modid = CocktailMod.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onMobEffectExpired(MobEffectEvent.Expired event) {
        if (event.getEntity() instanceof Player player && event.getEffectInstance().toString().equals("effect.cocktailmod.zombie_morph, Duration: 0")) {
            SwapPackets.sendSwapRequest();
        }
    }
}

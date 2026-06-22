package com.cocktail.cocktailmod.ability;

import com.cocktail.cocktailmod.CocktailMod;
import net.minecraft.world.entity.monster.Skeleton;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import tocraft.walkers.ability.AbilityRegistry;

@EventBusSubscriber(modid = CocktailMod.MOD_ID)
public class ModAbilities {
    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AbilityRegistry.registerByClass(Skeleton.class, new ShootArrowAbility());
        });
    }
}
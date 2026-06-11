package com.cocktail.cocktailmod;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.common.NeoForge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.neoforged.fml.common.Mod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(CocktailMod.MODID)
public class CocktailMod {
    public static final String MODID = "cocktailmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, MODID);

    public static final DeferredHolder<Potion, Potion> COCKTAIL_POTION =
            POTIONS.register("cocktail",
                    () -> new Potion(
                            // teste que faz efeito de náusea
                            new MobEffectInstance(MobEffects.CONFUSION, 20 * 30) // 20ticks/s * 30s = 600 ticks (30s)
                    ));

    public CocktailMod(IEventBus modEventBus) {
        POTIONS.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerBrewing(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(
                Potions.AWKWARD,
                Items.EGG,
                COCKTAIL_POTION
        );
    }
}
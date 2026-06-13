package com.cocktail.cocktailmod.potion;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.effect.MorphEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CocktailPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, CocktailMod.MODID);

    public static final DeferredHolder<Potion, Potion> COCKTAIL_POTION =
            POTIONS.register("cocktail", () -> new Potion());

    public static final DeferredHolder<Potion, Potion> COCKTAIL_ZOMBIE_POTION =
            POTIONS.register("cocktail_zombie", () -> new Potion(new MobEffectInstance(MorphEffects.ZOMBIE_MORPH, 20 * 10, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}

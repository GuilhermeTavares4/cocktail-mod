package com.cocktail.cocktailmod.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.cocktail.cocktailmod.CocktailMod;

public class MorphEffects {
    public static final DeferredRegister<MobEffect> MORPH_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, CocktailMod.MODID);

    public static final Holder<MobEffect> ZOMBIE_MORPH =
            MORPH_EFFECTS.register("zombie_morph",
                    () -> new ZombieMorph(MobEffectCategory.NEUTRAL, 0x3B7A57));

    public static void register(IEventBus eventBus) {
        MORPH_EFFECTS.register(eventBus);
    }
}

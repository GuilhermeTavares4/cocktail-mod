package com.cocktail.cocktailmod.effect;

import com.cocktail.cocktailmod.CocktailMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MorphEffects {
    public static final DeferredRegister<MobEffect> MORPH_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, CocktailMod.MOD_ID);

    public static final Holder<MobEffect> ZOMBIE_MORPH =
            MORPH_EFFECTS.register("zombie_morph",
                    () -> new ZombieMorph(MobEffectCategory.NEUTRAL, 0x3B7A57));

    public static final Holder<MobEffect> CREEPER_MORPH =
            MORPH_EFFECTS.register("creeper_morph",
                    () -> new CreeperMorph(MobEffectCategory.NEUTRAL, 0x0F800F));

    public static final Holder<MobEffect> ENDERMAN_MORPH =
            MORPH_EFFECTS.register("enderman_morph",
                    () -> new EndermanMorph(MobEffectCategory.NEUTRAL, 0x3f003e));

    public static void register(IEventBus eventBus) {
        MORPH_EFFECTS.register(eventBus);
    }
}

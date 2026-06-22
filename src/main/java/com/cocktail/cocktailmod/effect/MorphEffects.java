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

    public static final Holder<MobEffect> SPIDER_MORPH =
            MORPH_EFFECTS.register("spider_morph",
                    () -> new SpiderMorph(MobEffectCategory.NEUTRAL, 0xbe0404));

    public static final Holder<MobEffect> SKELETON_MORPH =
            MORPH_EFFECTS.register("skeleton_morph",
                    () -> new SkeletonMorph(MobEffectCategory.NEUTRAL, 0xd3d3d3));

    public static final Holder<MobEffect> BLAZE_MORPH =
            MORPH_EFFECTS.register("blaze_morph",
                    () -> new BlazeMorph(MobEffectCategory.NEUTRAL, 0xfc9600));

    public static final Holder<MobEffect> SLIME_MORPH =
            MORPH_EFFECTS.register("slime_morph",
                    () -> new SlimeMorph(MobEffectCategory.NEUTRAL, 0x51A03E));

    public static void register(IEventBus eventBus) {
        MORPH_EFFECTS.register(eventBus);
    }
}

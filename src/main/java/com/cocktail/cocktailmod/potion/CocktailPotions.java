package com.cocktail.cocktailmod.potion;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.effect.MorphEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = CocktailMod.MOD_ID)
public class CocktailPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, CocktailMod.MOD_ID);

    public static final DeferredHolder<Potion, Potion> COCKTAIL_POTION =
            POTIONS.register("cocktail", () -> new Potion());

    public static final int MORPH_DURATION = 20 * 10; // 10s

    public static final DeferredHolder<Potion, Potion> COCKTAIL_ZOMBIE_POTION =
            POTIONS.register("cocktail_zombie", () -> new Potion(
                    new MobEffectInstance(MorphEffects.ZOMBIE_MORPH, MORPH_DURATION, 0)));

    public static final DeferredHolder<Potion, Potion> COCKTAIL_CREEPER_POTION =
            POTIONS.register("cocktail_creeper", () -> new Potion(
                    new MobEffectInstance(MorphEffects.CREEPER_MORPH, MORPH_DURATION, 0)));

    public static final DeferredHolder<Potion, Potion> COCKTAIL_ENDERMAN_POTION =
            POTIONS.register("cocktail_enderman", () -> new Potion(
                    new MobEffectInstance(MorphEffects.ENDERMAN_MORPH, MORPH_DURATION,0)));

    public static final DeferredHolder<Potion, Potion> COCKTAIL_SPIDER_POTION =
            POTIONS.register("cocktail_spider", () -> new Potion(
                    new MobEffectInstance(MorphEffects.SPIDER_MORPH, MORPH_DURATION,0)));

    public static final DeferredHolder<Potion, Potion> COCKTAIL_SKELETON_POTION =
            POTIONS.register("cocktail_skeleton", () -> new Potion(
                    new MobEffectInstance(MorphEffects.SKELETON_MORPH, MORPH_DURATION,0)));

    public static final DeferredHolder<Potion, Potion> COCKTAIL_BLAZE_POTION =
            POTIONS.register("cocktail_blaze", () -> new Potion(
                    new MobEffectInstance(MorphEffects.BLAZE_MORPH, MORPH_DURATION,0)));

    public static final DeferredHolder<Potion, Potion> COCKTAIL_SLIME_POTION =
            POTIONS.register("cocktail_slime", () -> new Potion(
                    new MobEffectInstance(MorphEffects.SLIME_MORPH, MORPH_DURATION, 0)));

    @SubscribeEvent
    public static void registerBrewing(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.AWKWARD, Items.EGG, COCKTAIL_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.ROTTEN_FLESH, COCKTAIL_ZOMBIE_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.TNT, COCKTAIL_CREEPER_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.ENDER_PEARL, COCKTAIL_ENDERMAN_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.SPIDER_EYE, COCKTAIL_SPIDER_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.BONE, COCKTAIL_SKELETON_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.BLAZE_ROD, COCKTAIL_BLAZE_POTION);
        event.getBuilder().addMix(COCKTAIL_POTION, Items.SLIME_BALL, COCKTAIL_SLIME_POTION);
    }

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}

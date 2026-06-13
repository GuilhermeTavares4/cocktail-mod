package com.cocktail.cocktailmod;

import com.cocktail.cocktailmod.potion.CocktailPotions;
import com.cocktail.cocktailmod.effect.MorphEffects;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(CocktailMod.MODID)
public class CocktailMod {
    public static final String MODID = "cocktailmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CocktailMod(IEventBus modEventBus) {
        CocktailPotions.register(modEventBus);
        MorphEffects.register(modEventBus);
    }
}
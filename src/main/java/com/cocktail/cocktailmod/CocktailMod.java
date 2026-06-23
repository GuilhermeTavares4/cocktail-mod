package com.cocktail.cocktailmod;

import com.cocktail.cocktailmod.item.ModItems;
import com.cocktail.cocktailmod.potion.CocktailPotions;
import com.cocktail.cocktailmod.effect.MorphEffects;
import com.cocktail.cocktailmod.sound.ModSounds;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(CocktailMod.MOD_ID)
public class CocktailMod {
    public static final String MOD_ID = "cocktailmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CocktailMod(IEventBus modEventBus) {
        CocktailPotions.register(modEventBus);
        MorphEffects.register(modEventBus);
        ModItems.register(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.RAP_DO_MINECRAFT_MUSIC_DISC);
            event.accept(ModItems.METAMORFOSE_AMBULANTE_MUSIC_DISC);
        }
    }
}
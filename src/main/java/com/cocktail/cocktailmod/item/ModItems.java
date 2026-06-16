package com.cocktail.cocktailmod.item;

import com.cocktail.cocktailmod.CocktailMod;
import com.cocktail.cocktailmod.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CocktailMod.MOD_ID);

    public static final DeferredItem<Item> RAP_DO_MINECRAFT_MUSIC_DISC = ITEMS.register("rap_do_minecraft_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.RAP_DO_MINECRAFT_KEY).stacksTo(1)));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}

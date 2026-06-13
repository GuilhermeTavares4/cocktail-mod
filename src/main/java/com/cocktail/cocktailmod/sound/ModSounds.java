package com.cocktail.cocktailmod.sound;

import com.cocktail.cocktailmod.CocktailMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, CocktailMod.MODID);

    public static final Supplier<SoundEvent> RAP_DO_MINECRAFT = registerSoundEvent("rap_do_minecraft");
    public static final ResourceKey<JukeboxSong> RAP_DO_MINECRAFT_KEY = createSong("rap_do_minecraft");

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(CocktailMod.MODID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
    ResourceLocation id = ResourceLocation.fromNamespaceAndPath(CocktailMod.MODID, name);
    return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent((id)));
    }
}

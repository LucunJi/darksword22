package io.github.lucunji.darksword22;

import io.github.lucunji.darksword22.client.render.SwordEntityRenderer;
import io.github.lucunji.darksword22.entity.SwordEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("darksword22")
public class Main {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "darksword22";
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final RegistryObject<EntityType<SwordEntity>> SWORD_ENTITY = ENTITY_TYPES.register("sword",
            () -> EntityType.Builder.<SwordEntity>create(SwordEntity::new, EntityClassification.MISC).build("sword"));

    public Main() {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MODID, value = Dist.CLIENT)
    public static class ModRegistries {
        @SubscribeEvent
        public static void doClientStuff(final FMLClientSetupEvent event) {
            LOGGER.debug("Register renderers");
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            RenderingRegistry.registerEntityRenderingHandler(SWORD_ENTITY.get(), manager -> new SwordEntityRenderer(manager, itemRenderer));
        }
    }
}

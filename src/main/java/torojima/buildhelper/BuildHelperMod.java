package torojima.buildhelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import torojima.buildhelper.common.BuildHelperConfig;
import torojima.buildhelper.init.ItemInit;

@Mod(BuildHelperMod.MODID)
public class BuildHelperMod
{
	public static BuildHelperMod instance;
	
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static final String MODID = "buildhelper";
    public static final String VERSION = "3.6.0.0";
    public static final String UPDATEJSON = "https://github.com/ArnoSaxena/buildhelper/blob/master/bin/update.json";

    public BuildHelperMod()
    {
    	instance = this;
    	
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

        ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Torojima's Buildhelper Items registered.");
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, BuildHelperConfig.sp﻿ec);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void clientRegistries(final FMLClientSetupEvent event)
    {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }
}

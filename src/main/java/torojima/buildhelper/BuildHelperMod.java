package torojima.buildhelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import torojima.buildhelper.common.BuildHelperConfig;
import torojima.buildhelper.common.item.ItemArrowWand;
import torojima.buildhelper.common.item.ItemCubeDiggerWand;
import torojima.buildhelper.common.item.ItemExchangeWand;
import torojima.buildhelper.common.item.ItemFillDownWand;
import torojima.buildhelper.common.item.ItemFillWand;
import torojima.buildhelper.common.item.ItemFillWandAir;
import torojima.buildhelper.common.item.ItemFillWandCobble;
import torojima.buildhelper.common.item.ItemFillWandDirt;
import torojima.buildhelper.common.item.ItemFillWandIronore;
import torojima.buildhelper.common.item.ItemFillWandStone;
import torojima.buildhelper.common.item.ItemGapFillWand;
import torojima.buildhelper.common.item.ItemGapFillWaterWand;
import torojima.buildhelper.common.item.ItemGrowWand;
import torojima.buildhelper.common.item.ItemRemoveWaterWand;
import torojima.buildhelper.common.item.ItemSandWaterWand;
import torojima.buildhelper.common.item.ItemTorchWand;
import torojima.buildhelper.lists.ItemList;

import net.minecraft.world.item.CreativeModeTab;

import torojima.buildhelper.common.item.ItemCopyPasteWand;

@Mod(BuildHelperMod.MODID)
public class BuildHelperMod
{
	public static BuildHelperMod instance;
	
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static final String MODID = "buildhelper";
    public static final String VERSION = "3.4.0.0";
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


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
    	@SubscribeEvent
    	public static void registerItems(final RegistryEvent.Register<Item> event)
    	{
    		event.getRegistry().registerAll(
    				ItemList.arrowwand_item = new ItemArrowWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemArrowWand.NAME)
    				,ItemList.cubediggerwand_item = new ItemCubeDiggerWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemCubeDiggerWand.NAME)
    				,ItemList.exchangewand_item = new ItemExchangeWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemExchangeWand.NAME)
    				,ItemList.sandwaterwand_item = new ItemSandWaterWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemSandWaterWand.NAME)
    				,ItemList.fillwanddirt_item = new ItemFillWandDirt(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemFillWandDirt.NAME)
    				,ItemList.fillwandironore_item = new ItemFillWandIronore(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemFillWandIronore.NAME)
    				,ItemList.fillwandcobble_item = new ItemFillWandCobble(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemFillWandCobble.NAME)
    				,ItemList.fillwandstone_item = new ItemFillWandStone(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemFillWandStone.NAME)
    				,ItemList.fillwandair_item = new ItemFillWandAir(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemFillWandAir.NAME)
    				,ItemList.gapfillwand_item = new ItemGapFillWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemGapFillWand.NAME)
    				,ItemList.gapfillwaterwand_item = new ItemGapFillWaterWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemGapFillWaterWand.NAME)
    				,ItemList.removewaterwand_item = new ItemRemoveWaterWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemRemoveWaterWand.NAME)
    				,ItemList.allfillwand_item = new ItemFillWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemFillWand.NAME)
    				,ItemList.growwand_item = new ItemGrowWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemGrowWand.NAME)
    				,ItemList.torchwand_item = new ItemTorchWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemTorchWand.NAME)
    				,ItemList.filldownwand_item = new ItemFillDownWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemFillDownWand.NAME)
    				,ItemList.copypastewand_item = new ItemCopyPasteWand(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(ItemCopyPasteWand.NAME)
    		);
    		LOGGER.info("Torojima's Buildhelper Items registered.");
    	}
    }
}

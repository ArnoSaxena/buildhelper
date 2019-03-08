package torojima.buildhelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

@Mod(BuildHelperMod.MODID)
public class BuildHelperMod
{
	public static BuildHelperMod instance;
	
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static final String MODID = "buildhelper";
    public static final String VERSION = "2.0.0.0";
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
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	//LOGGER.info("setup method registered.");
    }

    private void clientRegistries(final FMLClientSetupEvent event)
    {
        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        //InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        //LOGGER.info("Got IMC {}", event.getIMCStream().
        //        map(m->m.getMessageSupplier().get()).
        //        collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
    	@SubscribeEvent
    	public static void registerItems(final RegistryEvent.Register<Item> event)
    	{
    		event.getRegistry().registerAll(    		
    				ItemList.arrowwand_item = new ItemArrowWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemArrowWand.NAME)),
    				ItemList.cubediggerwand_item = new ItemCubeDiggerWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemCubeDiggerWand.NAME)),
    				ItemList.exchangewand_item = new ItemExchangeWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemExchangeWand.NAME)),
    				ItemList.sandwaterwand_item = new ItemSandWaterWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemSandWaterWand.NAME)),
    				ItemList.fillwanddirt_item = new ItemFillWandDirt(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemFillWandDirt.NAME)),
    				ItemList.fillwandironore_item = new ItemFillWandIronore(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemFillWandIronore.NAME)),
    				ItemList.fillwandcobble_item = new ItemFillWandCobble(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemFillWandCobble.NAME)),
    				ItemList.fillwandstone_item = new ItemFillWandStone(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemFillWandStone.NAME)),
    				ItemList.fillwandair_item = new ItemFillWandAir(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemFillWandAir.NAME)),
    				ItemList.gapfillwand_item = new ItemGapFillWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemGapFillWand.NAME)),
    				ItemList.gapfillwaterwand_item = new ItemGapFillWaterWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemGapFillWaterWand.NAME)),
    				ItemList.removewaterwand_item = new ItemRemoveWaterWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemRemoveWaterWand.NAME)),
    				ItemList.allfillwand_item = new ItemFillWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemFillWand.NAME)),
    				ItemList.growwand_item = new ItemGrowWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemGrowWand.NAME)),
    				ItemList.torchwand_item = new ItemTorchWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemTorchWand.NAME)),
    				ItemList.filldownwand_item = new ItemFillDownWand(new Item.Properties().maxStackSize(1).group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(MODID, ItemFillDownWand.NAME))
    		);
    		LOGGER.info("Torojima's Buildhelper Items registered.");
    	}
    }
}

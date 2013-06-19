package buildhelper;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=Constants.TBH_MODID, name=Constants.TBH_NAME, version=Constants.TBH_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModBuildHelper
{
	public static Configuration mainConfiguration;
	public static File configFolder;

	public static Property sandInWaterWandId;
	public static Item sandInWaterWandItem;
	
	public static Property allFillWandId;
	public static Item allFillWandItem;
	
	public static Property exchangeWandId;
	public static Item exchangeWandItem;
	
	public static Property dirtFillWandId;
	public static Item dirtFillWandItem;
	
	public static Property cobbleFillWandId;
	public static Item cobbleFillWandItem;
	
	public static Property stoneFillWandId;
	public static Item stoneFillWandItem;
	
	public static Property cubeDiggerWandId;
	public static Item cubeDiggerWandItem;
	
	public static Property airFillWandId;
	public static Item airFillWandItem;
	
	public static Property gapFillWandId;
	public static Item gapFillWandItem;
	
	public static Property gapFillWaterWandId;
	public static Item gapFillWaterWandItem;
	
	@Instance(Constants.TBH_MODID)
	public static ModBuildHelper instance;

	@Init
	public void loadBuildHelper(FMLInitializationEvent loadEvent)
	{
		this.createItemInstances();
		this.addRecipes();
	}

	@PreInit
	public void preLoadBuildHelper(FMLPreInitializationEvent preInitEvent)
	{
		mainConfiguration = new Configuration(preInitEvent.getSuggestedConfigurationFile());
		configFolder = preInitEvent.getModConfigurationDirectory();
		mainConfiguration.load();
		
		sandInWaterWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "sandWaterWand.id", Constants.SAND_WATER_WAND_ID);
		allFillWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "allFlatWandItem.id", Constants.ALL_FILL_WAND_ID);
		exchangeWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "exchangeWandItem.id", Constants.EXCHANGE_WAND_ID);
		dirtFillWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "dirtFlatWandItem.id", Constants.DIRT_FILL_WAND_ID);
		cobbleFillWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "cobbleFlatWandItem.id", Constants.COBBLE_FILL_WAND_ID);
		stoneFillWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "stoneFlatWandItem.id", Constants.STONE_FILL_WAND_ID);
		cubeDiggerWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "cubeDiggerWandItem.id", Constants.CUBE_DIGGER_WAND_ID);
		airFillWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "airFillWandItem.id", Constants.AIR_FILL_WAND_ID);
		gapFillWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "gapFillWandItem.id", Constants.GAP_FILL_WAND_ID);
		gapFillWaterWandId = mainConfiguration.getItem(Configuration.CATEGORY_ITEM, "gapFillWaterWandItem.id", Constants.GAP_FILL_WATER_WAND_ID);
		
		mainConfiguration.save();
	}
	
	@PostInit
	public void postLoadBuildHelper(FMLPostInitializationEvent postInitEvent) {}
	
	private void createItemInstances()
	{
		sandInWaterWandItem = (new ItemSandInWaterWand(sandInWaterWandId.getInt()));
		sandInWaterWandItem.setUnlocalizedName(Constants.KEY_SAND_WATER_WAND);
		LanguageRegistry.addName(sandInWaterWandItem, "Sand in Water Wand");

		allFillWandItem = (new ItemFillWand(allFillWandId.getInt()));
		allFillWandItem.setUnlocalizedName(Constants.KEY_ALL_FILL_WAND);
		LanguageRegistry.addName(allFillWandItem, "Universal Fill Wand");

		exchangeWandItem = (new ItemExchangeWand(exchangeWandId.getInt()));
		exchangeWandItem.setUnlocalizedName(Constants.KEY_EXCHANGE_WAND);
		LanguageRegistry.addName(exchangeWandItem, "Exchange Wand");

		dirtFillWandItem = (new ItemFillWandDirt(dirtFillWandId.getInt()));
		dirtFillWandItem.setUnlocalizedName(Constants.KEY_DIRT_FILL_WAND);
		LanguageRegistry.addName(dirtFillWandItem, "Dirt Fill Wand");

		cobbleFillWandItem = (new ItemFillWandCobble(cobbleFillWandId.getInt()));
		cobbleFillWandItem.setUnlocalizedName(Constants.KEY_COBBLE_FILL_WAND);
		LanguageRegistry.addName(cobbleFillWandItem, "Cobble Fill Wand");

		stoneFillWandItem = (new ItemFillWandStone(stoneFillWandId.getInt()));
		stoneFillWandItem.setUnlocalizedName(Constants.KEY_STONE_FILL_WAND);
		LanguageRegistry.addName(stoneFillWandItem, "Stone Fill Wand");

		cubeDiggerWandItem = (new ItemCubeDiggerWand(cubeDiggerWandId.getInt()));
		cubeDiggerWandItem.setUnlocalizedName(Constants.KEY_CUBE_DIGGER_WAND);
		LanguageRegistry.addName(cubeDiggerWandItem, "Cube Digger Wand");

		airFillWandItem = (new ItemFillWandAir(airFillWandId.getInt()));
		airFillWandItem.setUnlocalizedName(Constants.KEY_AIR_FILL_WAND);
		LanguageRegistry.addName(airFillWandItem, "Area Digger Wand");

		gapFillWandItem = (new ItemGapFillWand(gapFillWandId.getInt()));
		gapFillWandItem.setUnlocalizedName(Constants.KEY_GAPFILL_WAND);
		LanguageRegistry.addName(gapFillWandItem, "Gap Filler Wand");

		gapFillWaterWandItem = (new ItemGapFillWaterWand(gapFillWaterWandId.getInt()));
		gapFillWaterWandItem.setUnlocalizedName(Constants.KEY_GAPFILLWATER_WAND);
		LanguageRegistry.addName(gapFillWaterWandItem, "Water Filler Wand");
	}
	
	private void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(sandInWaterWandItem, 1), new Object[]
		{
			"  S", 
			" - ", 
			"N  ", 
			Character.valueOf('S'), Block.sand, 
			Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});
		
		GameRegistry.addRecipe(new ItemStack(allFillWandItem, 1), new Object[]
		{
			"  D", 
		    " - ", 
		    "N  ", 
		    Character.valueOf('D'), Item.diamond, 
		    Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});
		
		GameRegistry.addRecipe(new ItemStack(allFillWandItem, 1), new Object[]
		{
			"  D", 
			" - ", 
			"E  ", 
			Character.valueOf('D'), Item.diamond, 
			Character.valueOf('-'), Item.stick,
			Character.valueOf('E'), Item.emerald
		});


		GameRegistry.addRecipe(new ItemStack(cobbleFillWandItem, 1), new Object[]
		{
			"  C", 
			" - ", 
			"N  ", 
			Character.valueOf('C'), Block.cobblestone, 
			Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});

		GameRegistry.addRecipe(new ItemStack(stoneFillWandItem, 1), new Object[]
		{
			"  S", 
		    " - ", 
		    "N  ", 
		    Character.valueOf('S'), Block.stone, 
		    Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});
		
		GameRegistry.addRecipe(new ItemStack(dirtFillWandItem, 1), new Object[]
		{
			"  D", 
		    " - ", 
		    "N  ", 
		    Character.valueOf('D'), Block.dirt, 
		    Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});

		GameRegistry.addRecipe(new ItemStack(cubeDiggerWandItem, 1), new Object[]
		{
			"  S", 
		    " - ", 
		    "N  ", 
		    Character.valueOf('S'), Item.shovelWood, 
		    Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});
		
		GameRegistry.addRecipe(new ItemStack(airFillWandItem, 1), new Object[]
		{
			"  S", 
			" - ", 
			"N  ", 
			Character.valueOf('S'), Item.shovelIron, 
			Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});

		GameRegistry.addRecipe(new ItemStack(gapFillWandItem, 1), new Object[]
		{
			"  E", 
			" - ", 
			"N  ", 
			Character.valueOf('E'), Item.emerald, 
			Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});

		GameRegistry.addRecipe(new ItemStack(gapFillWaterWandItem, 1), new Object[]
		{
			"  B",
			" - ", 
			"N  ", 
			Character.valueOf('E'), Item.bucketWater, 
			Character.valueOf('-'), Item.stick,
			Character.valueOf('N'), Item.goldNugget
		});
	}
}

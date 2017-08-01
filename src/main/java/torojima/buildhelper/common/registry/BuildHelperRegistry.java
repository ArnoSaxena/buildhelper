package torojima.buildhelper.common.registry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.*;

@EventBusSubscriber(modid = BuildHelperMod.MODID)
public class BuildHelperRegistry
{
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> e)
	{
		BuildHelperMod.logger.info("registering items");
		
		BuildHelperMod.sandWaterWand = new ItemSandWaterWand();
		e.getRegistry().register(BuildHelperMod.sandWaterWand);

		BuildHelperMod.fillWandDirt = new ItemFillWandDirt();
		e.getRegistry().register(BuildHelperMod.fillWandDirt);

		BuildHelperMod.fillWandIronore = new ItemFillWandIronore();
		e.getRegistry().register(BuildHelperMod.fillWandIronore);

		BuildHelperMod.fillWandCobble = new ItemFillWandCobble();
		e.getRegistry().register(BuildHelperMod.fillWandCobble);

		BuildHelperMod.fillWandStone = new ItemFillWandStone();
		e.getRegistry().register(BuildHelperMod.fillWandStone);

		BuildHelperMod.exchangeWand = new ItemExchangeWand();
		e.getRegistry().register(BuildHelperMod.exchangeWand);

		BuildHelperMod.fillWandAir = new ItemFillWandAir();
		e.getRegistry().register(BuildHelperMod.fillWandAir);

		BuildHelperMod.gapFillWand = new ItemGapFillWand();
		e.getRegistry().register(BuildHelperMod.gapFillWand);

		BuildHelperMod.gapFillWaterWand = new ItemGapFillWaterWand();
		e.getRegistry().register(BuildHelperMod.gapFillWaterWand);

		BuildHelperMod.cubeDiggerWand = new ItemCubeDiggerWand();
		e.getRegistry().register(BuildHelperMod.cubeDiggerWand);

		BuildHelperMod.removeWaterWand = new ItemRemoveWaterWand();
		e.getRegistry().register(BuildHelperMod.removeWaterWand);

		BuildHelperMod.allFillWand = new ItemFillWand();
		e.getRegistry().register(BuildHelperMod.allFillWand);
	}
}
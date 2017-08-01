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
		e.getRegistry().register(BuildHelperMod.sandWaterWand);
		e.getRegistry().register(BuildHelperMod.fillWandDirt);
		e.getRegistry().register(BuildHelperMod.fillWandIronore);
		e.getRegistry().register(BuildHelperMod.fillWandCobble);
		e.getRegistry().register(BuildHelperMod.fillWandStone);
		e.getRegistry().register(BuildHelperMod.exchangeWand);
		e.getRegistry().register(BuildHelperMod.fillWandAir);
		e.getRegistry().register(BuildHelperMod.gapFillWand);
		e.getRegistry().register(BuildHelperMod.gapFillWaterWand);
		e.getRegistry().register(BuildHelperMod.cubeDiggerWand);
		e.getRegistry().register(BuildHelperMod.removeWaterWand);
		e.getRegistry().register(BuildHelperMod.allFillWand);
	}
}
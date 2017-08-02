package torojima.buildhelper.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.*;

@Mod.EventBusSubscriber(modid = BuildHelperMod.MODID, value = Side.CLIENT)
public class ClientEventHandler {
	
	@SubscribeEvent
	public void onRegisterModels(ModelRegistryEvent event)
	{
		BuildHelperMod.logger.info("registering models");
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.sandWaterWand, 0,
				new ModelResourceLocation(BuildHelperMod.sandWaterWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.fillWandDirt, 0,
				new ModelResourceLocation(BuildHelperMod.fillWandDirt.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.fillWandIronore, 0,
				new ModelResourceLocation(BuildHelperMod.fillWandIronore.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.fillWandCobble, 0,
				new ModelResourceLocation(BuildHelperMod.fillWandCobble.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.fillWandStone, 0,
				new ModelResourceLocation(BuildHelperMod.fillWandStone.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.exchangeWand, 0,
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.fillWandAir, 0,
				new ModelResourceLocation(BuildHelperMod.fillWandAir.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.gapFillWand, 0,
				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.gapFillWaterWand, 0,
				new ModelResourceLocation(BuildHelperMod.gapFillWaterWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.cubeDiggerWand, 0,
				new ModelResourceLocation(BuildHelperMod.cubeDiggerWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.removeWaterWand, 0,
				new ModelResourceLocation(BuildHelperMod.removeWaterWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.allFillWand, 0,
				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName(), "inventory"));
    }
}

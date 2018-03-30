package torojima.buildhelper.client;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.*;
import torojima.buildhelper.common.meshDefinitions.*;

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
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.arrowWand, 0,
				new ModelResourceLocation(BuildHelperMod.arrowWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.growWand, 0,
				new ModelResourceLocation(BuildHelperMod.growWand.getRegistryName(), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BuildHelperMod.torchWand, 0,
				new ModelResourceLocation(BuildHelperMod.torchWand.getRegistryName(), "inventory"));
		
		BuildHelperMod.logger.info("registering custom mesh defs");
		ModelBakery.registerItemVariants(BuildHelperMod.exchangeWand,
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c1", "inventory"),
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c2", "inventory"),
				new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c3", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.exchangeWand, new ExchangeWandMesh());

		ModelBakery.registerItemVariants(BuildHelperMod.gapFillWand,
				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName() + "_c1", "inventory"),
				new ModelResourceLocation(BuildHelperMod.gapFillWand.getRegistryName() + "_c2", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.gapFillWand, new GapFillWandMesh());

		ModelBakery.registerItemVariants(BuildHelperMod.gapFillWaterWand,
				new ModelResourceLocation(BuildHelperMod.gapFillWaterWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.gapFillWaterWand.getRegistryName() + "_c", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.gapFillWaterWand, new GapFillWaterWandMesh());		
		
		ModelBakery.registerItemVariants(BuildHelperMod.allFillWand,
				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName(), "inventory"),
				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName() + "_c1", "inventory"),
				new ModelResourceLocation(BuildHelperMod.allFillWand.getRegistryName() + "_c2", "inventory")
				);
		ModelLoader.setCustomMeshDefinition(BuildHelperMod.allFillWand, new UniversalFillWandMesh());
    }
}

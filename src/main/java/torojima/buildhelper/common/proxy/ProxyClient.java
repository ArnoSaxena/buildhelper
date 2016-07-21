package torojima.buildhelper.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import torojima.buildhelper.BuildHelperMod;

public class ProxyClient extends ProxyServer
{
	public void registerModels()
	{
		this.registerModel(BuildHelperMod.sandWaterWand);
		this.registerModel(BuildHelperMod.fillWandDirt);
		this.registerModel(BuildHelperMod.fillWandCobble);
		this.registerModel(BuildHelperMod.fillWandStone);
		this.registerModel(BuildHelperMod.exchangeWand);
		this.registerModel(BuildHelperMod.fillWandAir);
		this.registerModel(BuildHelperMod.gapFillWand);
		this.registerModel(BuildHelperMod.gapFillWaterWand);
		this.registerModel(BuildHelperMod.cubeDiggerWand);
	}
	
	private void registerModel(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				item, 
				0, 
	    		new ModelResourceLocation(BuildHelperMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));		
	}
}

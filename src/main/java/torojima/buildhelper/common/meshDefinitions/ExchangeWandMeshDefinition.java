package torojima.buildhelper.common.meshDefinitions;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.ItemExchangeWand;

public class ExchangeWandMeshDefinition implements ItemMeshDefinition
{

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack)
	{
		if(stack.getItem() == BuildHelperMod.exchangeWand)
		{
			ItemExchangeWand iew = (ItemExchangeWand)stack.getItem();
			ResourceLocation registryName = BuildHelperMod.exchangeWand.getRegistryName();
			switch (iew.getStatus())
			{
			case ItemExchangeWand.NONE:
				return new ModelResourceLocation(registryName, "inventory");
			case ItemExchangeWand.NAMED:
				return new ModelResourceLocation(registryName + "_c1", "inventory");
			case ItemExchangeWand.CHARGED:
				return new ModelResourceLocation(registryName + "_c2", "inventory");
			case ItemExchangeWand.FILL:
				return new ModelResourceLocation(registryName + "_c3", "inventory");
			default:
				return new ModelResourceLocation(registryName, "inventory");
			}
		}
		return null;		
	}
}

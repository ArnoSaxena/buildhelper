package torojima.buildhelper.common.itemMeshDefinitions;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
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
			switch (iew.getStatus())
			{
			case ItemExchangeWand.NONE:
				return new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName(), "inventory");
			case ItemExchangeWand.NAMED:
				return new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c1", "inventory");
			case ItemExchangeWand.CHARGED:
				return new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c2", "inventory");
			case ItemExchangeWand.FILL:
				return new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName() + "_c3", "inventory");
			default:
				return new ModelResourceLocation(BuildHelperMod.exchangeWand.getRegistryName(), "inventory");
			}
		}
		return null;		
	}
}

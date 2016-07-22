package torojima.buildhelper.common.itemMeshDefinitions;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.ItemExchangeWand;

public class ItemExchangeWandMeshDefinition implements ItemMeshDefinition
{

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack)
	{
		if(stack.getItem() == BuildHelperMod.exchangeWand)
		{
			switch (BuildHelperMod.exchangeWand.getStatus())
			{
			case ItemExchangeWand.NONE:
				return new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5), "inventory");
			case ItemExchangeWand.NAMED:
				return new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5) + "_c1", "inventory");
			case ItemExchangeWand.CHARGED:
				return new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5) + "_c2", "inventory");
			case ItemExchangeWand.FILL:
				return new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5) + "_c3", "inventory");
			default:
				return new ModelResourceLocation(BuildHelperMod.MODID + ":" + BuildHelperMod.exchangeWand.getUnlocalizedName().substring(5), "inventory");
			}
		}
		return null;		
	}
}

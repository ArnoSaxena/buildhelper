package torojima.buildhelper.common.meshDefinitions;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import torojima.buildhelper.BuildHelperMod;
import torojima.buildhelper.common.item.ItemGapFillWand;

public class GapFillWandMeshDefinition implements ItemMeshDefinition
{
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack)
	{
		if(stack.getItem() == BuildHelperMod.gapFillWand)
		{
			ItemGapFillWand igfw = (ItemGapFillWand)stack.getItem();
			ResourceLocation registryName = BuildHelperMod.gapFillWand.getRegistryName();
			switch (igfw.getStatus())
			{
			case ItemGapFillWand.NONE:
				return new ModelResourceLocation(registryName, "inventory");
			case ItemGapFillWand.NAMED:
				return new ModelResourceLocation(registryName, "inventory");
			case ItemGapFillWand.CHARGED:
				return new ModelResourceLocation(registryName + "_c1", "inventory");
			case ItemGapFillWand.FILL:
				return new ModelResourceLocation(registryName + "_c2", "inventory");
			default:
				return new ModelResourceLocation(registryName, "inventory");
			}
		}
		return null;		
	}
}

// <copyright file="ItemGapFillWaterWand.java">
// Copyright (c) 2016 All Right Reserved, http://buildhelper.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2016-07-21</date>
// <summary>Item class for the fill water in gap wand item</summary>

package torojima.buildhelper.common.item;


import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ItemGapFillWaterWand extends ItemGapFillWand
{
	public static final String NAME = "gapfillwaterwand_item";
	
	public ItemGapFillWaterWand(Properties properties)
	{
		super(properties);
		
//		this.addPropertyOverride(new ResourceLocation("buildhelper:status"), 
//				(_itemStack, _world, _livingBase) -> 
//			{
//				if(_itemStack.getItem() instanceof ItemGapFillWaterWand)
//				{
//					ItemGapFillWaterWand igfww = (ItemGapFillWaterWand)_itemStack.getItem();
//					if (igfww.getStatus() == ItemGapFillWaterWand.CHARGED)
//					{
//						return 0.1F;
//					}
//				}
//				return 0.0F;
//			}
//		);
	}

	@Override
    public InteractionResult useOn(UseOnContext iuc)
	{
		if(this.status == NONE)
		{
			this.fillBlocks.put(iuc.getPlayer().getName(), Blocks.AIR.defaultBlockState());
			this.usedBlocks.put(iuc.getPlayer().getName(), Blocks.WATER.defaultBlockState());
			this.status = FILL;
		}
		return super.useOn(iuc);
	}	
}

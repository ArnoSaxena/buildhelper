// <copyright file="ItemGapFillWand.java">
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
// <summary>Item class for the gap fill wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;

public class ItemGapFillWand extends ItemExchangeWand
{
	public static final String NAME = "gapfillwand_item";

	public ItemGapFillWand(Properties properties)
	{
		super(properties);
		
		this.addPropertyOverride(new ResourceLocation("buildhelper:status"), 
				(_itemStack, _world, _livingBase) -> 
			{
				if(_itemStack.getItem() instanceof ItemGapFillWand)
				{
					ItemGapFillWand igfw = (ItemGapFillWand)_itemStack.getItem();
					if (igfw.getStatus() == ItemGapFillWand.NAMED)
					{
						return 0.1F;
					}
					else if (igfw.getStatus() == ItemGapFillWand.CHARGED)
					{
						return 0.2F;
					}
				}
				return 0.0F;
			}
		);
	}
	
	@Override
    public ActionResultType onItemUse(ItemUseContext iuc)
	{		
		if(this.status == NONE)
		{
			this.fillBlocks.put(iuc.getPlayer().getName(), Blocks.AIR.getDefaultState());
			this.status = NAMED;
		}
		return super.onItemUse(iuc);
	}
}

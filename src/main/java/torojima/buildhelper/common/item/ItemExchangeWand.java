// <copyright file="ItemExchangeWand.java">
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
// <summary>Item class for the exchange wand item</summary>

package torojima.buildhelper.common.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemExchangeWand extends ItemFillWand
{
	public static final int FILL = 3;
	
	public static final String NAME = "exchangewand_item";

	protected Map<ITextComponent, IBlockState> fillBlocks;

	public ItemExchangeWand(Properties properties)
	{
		super(properties);
		this.fillBlocks = new HashMap<ITextComponent, IBlockState>();
		
		this.addPropertyOverride(new ResourceLocation("buildhelper:status"), 
				(_itemStack, _world, _livingBase) -> 
			{
				if(_itemStack.getItem() instanceof ItemExchangeWand)
				{
					ItemExchangeWand iew = (ItemExchangeWand)_itemStack.getItem();
					if (iew.getStatus() == ItemExchangeWand.NAMED)
					{
						return 0.1F;
					}
					else if (iew.getStatus() == ItemExchangeWand.FILL)
					{
						return 0.2F;
					}
					else if (iew.getStatus() == ItemExchangeWand.CHARGED)
					{
						return 0.3F;
					}
				}
				return 0.0F;
			}
		);
	}	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if (worldIn.isRemote)
		{
			this.resetWand(playerIn.getName());
			this.fillBlocks.remove(playerIn.getName());
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
    public EnumActionResult onItemUse(ItemUseContext iuc)
	{
		ITextComponent username = iuc.getPlayer().getName();
		
		EnumActionResult returnValue = EnumActionResult.FAIL;
		
		if (!iuc.getWorld().isRemote)
		{
			if(this.usedBlocks.containsKey(username))
			{
				if(this.fillBlocks.containsKey(username))
				{
					if(this.isStartPointPresent(username))
					{
						BlockPos startPos = this.popStartPos(username);
						BlockPos endPos = iuc.getPos();
						if(this.pointsInDistanceLimit(startPos, endPos))
						{
							BlockPos posA = this.getPosAllBig(startPos, endPos);
							BlockPos posB = this.getPosAllSmall(startPos, endPos);

							IBlockState usedBlock = this.usedBlocks.get(username);
							this.usedBlocks.remove(username);
							
							IBlockState fillBlock = this.fillBlocks.get(username);
							this.fillBlocks.remove(username);

							for(int x = posA.getX(); x <= posB.getX(); x++)
							{
								for(int y = posA.getY(); y <= posB.getY(); y++)
								{
									for(int z = posA.getZ(); z <= posB.getZ(); z++)
									{
										BlockPos changePos = new BlockPos(x,y,z);
										
										if(iuc.getWorld().getBlockState(changePos).getBlock() == fillBlock.getBlock()
												&& !this.isBedRock(iuc.getWorld(), changePos))
										{
											iuc.getWorld().setBlockState(changePos, usedBlock, 3);
										}
									}
								}
							}
							this.status = NONE;
							returnValue = EnumActionResult.SUCCESS;
						}
						else
						{
							this.resetWand(username);
						}
					}
					else
					{
						this.putStartPos(iuc.getPos(), username);
						this.status = CHARGED;
						returnValue = EnumActionResult.SUCCESS;
					}
				}
				else
				{
					this.fillBlocks.put(username, iuc.getWorld().getBlockState(iuc.getPos()));
					this.status = FILL;
					returnValue = EnumActionResult.SUCCESS;
				}
			}
			else
			{
				this.usedBlocks.put(username, iuc.getWorld().getBlockState(iuc.getPos()));
				this.status = NAMED;
				returnValue = EnumActionResult.SUCCESS;
			}
		}
		
		return returnValue;
	}
}

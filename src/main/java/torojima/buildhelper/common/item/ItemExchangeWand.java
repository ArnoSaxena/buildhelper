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

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
//import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ItemExchangeWand extends ItemFillWand
{
	public static final int FILL = 3;
	
	public static final String NAME = "exchangewand_item";

	protected Map<ITextComponent, BlockState> fillBlocks;

	public ItemExchangeWand(Properties properties)
	{
		super(properties);
		this.fillBlocks = new HashMap<ITextComponent, BlockState>();
		
//		this.addPropertyOverride(new ResourceLocation("buildhelper:status"), 
//				(_itemStack, _world, _livingBase) -> 
//			{
//				if(_itemStack.getItem() instanceof ItemExchangeWand)
//				{
//					ItemExchangeWand iew = (ItemExchangeWand)_itemStack.getItem();
//					if (iew.getStatus() == ItemExchangeWand.NAMED)
//					{
//						return 0.1F;
//					}
//					else if (iew.getStatus() == ItemExchangeWand.FILL)
//					{
//						return 0.2F;
//					}
//					else if (iew.getStatus() == ItemExchangeWand.CHARGED)
//					{
//						return 0.3F;
//					}
//				}
//				return 0.0F;
//			}
//		);
	}	
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		if (worldIn.isClientSide)
		{
			this.resetWand(playerIn.getName());
			this.fillBlocks.remove(playerIn.getName());
		}
		return super.use(worldIn, playerIn, handIn);
	}
	
	@Override
    public ActionResultType useOn(ItemUseContext iuc)
	{
		ITextComponent username = iuc.getPlayer().getName();
		
		ActionResultType returnValue = ActionResultType.FAIL;
		
		if (!iuc.getLevel().isClientSide)
		{
			if(this.usedBlocks.containsKey(username))
			{
				if(this.fillBlocks.containsKey(username))
				{
					if(this.isStartPointPresent(username))
					{
						BlockPos startPos = this.popStartPos(username);
						BlockPos endPos = iuc.getClickedPos();
						if(this.pointsInDistanceLimit(startPos, endPos))
						{
							BlockPos posA = this.getPosAllBig(startPos, endPos);
							BlockPos posB = this.getPosAllSmall(startPos, endPos);

							BlockState usedBlock = this.usedBlocks.get(username);
							this.usedBlocks.remove(username);
							
							BlockState fillBlock = this.fillBlocks.get(username);
							this.fillBlocks.remove(username);

							for(int x = posA.getX(); x <= posB.getX(); x++)
							{
								for(int y = posA.getY(); y <= posB.getY(); y++)
								{
									for(int z = posA.getZ(); z <= posB.getZ(); z++)
									{
										BlockPos changePos = new BlockPos(x,y,z);
										
										if(iuc.getLevel().getBlockState(changePos).getBlock() == fillBlock.getBlock()
												&& !this.isBedRock(iuc.getLevel(), changePos))
										{
											iuc.getLevel().setBlock(changePos, usedBlock, 3);
										}
									}
								}
							}
							this.status = NONE;
							returnValue = ActionResultType.SUCCESS;
						}
						else
						{
							this.resetWand(username);
						}
					}
					else
					{
						this.putStartPos(iuc.getClickedPos(), username);
						this.status = CHARGED;
						returnValue = ActionResultType.SUCCESS;
					}
				}
				else
				{
					this.fillBlocks.put(username, iuc.getLevel().getBlockState(iuc.getClickedPos()));
					this.status = FILL;
					returnValue = ActionResultType.SUCCESS;
				}
			}
			else
			{
				this.usedBlocks.put(username, iuc.getLevel().getBlockState(iuc.getClickedPos()));
				this.status = NAMED;
				returnValue = ActionResultType.SUCCESS;
			}
		}
		
		return returnValue;
	}
}

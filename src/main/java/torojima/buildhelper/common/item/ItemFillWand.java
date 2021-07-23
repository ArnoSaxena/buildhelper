// <copyright file="ItemFillWand.java">
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
// <summary>Item class for the basic fill wand item</summary>

package torojima.buildhelper.common.item;

import java.util.HashMap;
import java.util.Map;



import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;



public class ItemFillWand extends ItemPosWand
{
	public static final String NAME = "fillwanduniversal_item";
	
	public static final int NONE = 0;
	public static final int NAMED = 1;
	public static final int CHARGED = 2;

	protected Map<Component, BlockState> usedBlocks;
	protected int status;

	public ItemFillWand(Properties properties)
	{
		super(properties);
		this.usedBlocks = new HashMap<Component, BlockState>();
		this.status = NONE;
		
//		this.addPropertyOverride(new ResourceLocation("buildhelper:status"), 
//				(_itemStack, _world, _livingBase) -> 
//			{
//				if(_itemStack.getItem() instanceof ItemFillWand)
//				{
//					ItemFillWand ifw = (ItemFillWand)_itemStack.getItem();
//					if (ifw.getStatus() == ItemFillWand.NAMED)
//					{
//						return 0.1F;
//					}
//					else if (ifw.getStatus() == ItemFillWand.CHARGED)
//					{
//						return 0.2F;
//					}
//				}
//				return 0.0F;
//			}
//		);
	}
	
	public int getStatus()
	{
		return this.status;
	}
	
	@Override
	public boolean isFoil(ItemStack stack)
	{
		if(this.getStatus() == ItemFillWand.CHARGED)
		{
			return true;
		}
		return false;
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
	{
		if (worldIn.isClientSide)
		{
			this.resetWand(playerIn.getName());
			this.usedBlocks.remove(playerIn.getName());
		}
		return super.use(worldIn, playerIn, handIn);
	}

	@Override
    public InteractionResult useOn(UseOnContext iuc)
    {
		Component username = iuc.getPlayer().getName();
		Boolean blocksChanged = false;
		
		if(!iuc.getLevel().isClientSide)
		{
			if(this.usedBlocks.containsKey(username))
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

						for(int x = posA.getX(); x <= posB.getX(); x++)
						{
							for(int y = posA.getY(); y <= posB.getY(); y++)
							{
								for(int z = posA.getZ(); z <= posB.getZ(); z++)
								{
									BlockPos changePos = new BlockPos(x,y,z);
								
									if(!this.isBedRock(iuc.getLevel(), changePos))
									{
										iuc.getLevel().setBlock(changePos, usedBlock, 3);
										blocksChanged = true;
									}
								}
							}
						}
						this.status = NONE;
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
					return InteractionResult.SUCCESS;
				}
			}
			else
			{
				BlockState targetBlockState = iuc.getLevel().getBlockState(iuc.getClickedPos());
				this.usedBlocks.put(username, targetBlockState);
				this.status = NAMED;
				return InteractionResult.SUCCESS;
			}
		}
		return blocksChanged ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }
	
	@Override
	protected void resetWand(Component username)
	{
		super.resetWand(username);
		this.usedBlocks.remove(username);
		this.status = NONE;
	}
	
	protected boolean isBedRock(Level world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == Blocks.BEDROCK;
	}
}

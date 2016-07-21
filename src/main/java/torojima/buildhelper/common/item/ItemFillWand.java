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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFillWand extends ItemPosWand
{
	protected static final int NONE = 0;
	protected static final int NAMED = 1;
	protected static final int CHARGED = 2;

	protected Map<String, Block> usedBlock;
	protected int status;

	public ItemFillWand()
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.usedBlock = new HashMap<String, Block>();
		this.status = NONE;
	}
	
	public int getStatus()
	{
		return this.status;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack)
	{
		if(this.getStatus() == ItemFillWand.CHARGED)
		{
			return true;
		}
		return false;
	}

	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		String username = playerIn.getName();
		Boolean blocksChanged = false;
		
		if(!worldIn.isRemote)
		{
			if(this.usedBlock.containsKey(username))
			{
				if(this.isStartPointPresent(username))
				{
					BlockPos startPos = this.popStartPos(username);
					BlockPos endPos = pos;
					if(this.pointsInDistanceLimit(startPos, endPos))
					{
						BlockPos posA = this.getPosAllBig(startPos, endPos);
						BlockPos posB = this.getPosAllSmall(startPos, endPos);
					
						Block usedBlock = this.usedBlock.get(username);
						this.usedBlock.remove(username);

						for(int x = posA.getX(); x <= posB.getX(); x++)
						{
							for(int y = posA.getY(); y <= posB.getY(); y++)
							{
								for(int z = posA.getZ(); z <= posB.getZ(); z++)
								{
									BlockPos changePos = new BlockPos(x,y,z);
								
									if(!this.isBedRock(worldIn, changePos))
									{
										worldIn.setBlockState(changePos, usedBlock.getDefaultState(), 3);
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
					this.putStartPos(pos, username);
					this.status = CHARGED;
					return EnumActionResult.SUCCESS;
				}
			}
			else
			{
				Block targetBlockState = worldIn.getBlockState(pos).getBlock();
				this.usedBlock.put(username, targetBlockState);
				this.status = NAMED;
				return EnumActionResult.SUCCESS;
			}
		}
		return blocksChanged ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
    }
	
	@Override
	protected void resetWand(String username)
	{
		super.resetWand(username);
		this.usedBlock.remove(username);
		this.status = NONE;
	}
	
	protected boolean isBedRock(World world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == Blocks.BEDROCK;
	}
}

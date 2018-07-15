// <copyright file="ItemTorchWand.java">
// Copyright (c) 2018 All Right Reserved, http://buildhelper.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2018-03-30</date>
// <summary>Item class for the torch wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTorchWand extends Item
{
	public static final String NAME = "torchwand";
	
	public ItemTorchWand()
	{
		super();
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setRegistryName(ItemTorchWand.NAME);
		this.setUnlocalizedName(ItemTorchWand.NAME);
		this.setMaxStackSize(1);
	}
	
    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {    	
    	BlockPos torchPos;
    	
    	switch(facing)
    	{
    	case DOWN:
    		return EnumActionResult.FAIL;
    	case UP:
    		torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
    		break;
    	case NORTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
    		break;
    	case SOUTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
    		break;
    	case WEST:
    		torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
    		break;
    	case EAST:
    		torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
    		break;
    	default:
    		return EnumActionResult.FAIL;
    	}
    	if(worldIn.getBlockState(torchPos).getBlock() == Blocks.AIR)
    	{
    		worldIn.setBlockState(torchPos, Blocks.TORCH.getStateForPlacement(worldIn, torchPos, facing, hitX, hitY, hitZ, 0, playerIn, hand), 3);
    		return EnumActionResult.SUCCESS;
    	}
    	return EnumActionResult.FAIL;
    }
}

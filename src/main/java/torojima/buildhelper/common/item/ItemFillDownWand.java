// <copyright file="ItemFillDownWand.java">
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
// <date>2018-07-14</date>
// <summary>Item class for the fill down wand item</summary>


package torojima.buildhelper.common.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFillDownWand extends Item
{
	public static final String NAME = "filldownwand_item";
		
	public ItemFillDownWand(Properties properties)
	{
		super(properties);
	}
	
	@Override
    public ActionResultType onItemUse(ItemUseContext iuc)
	{
		BlockPos pos = iuc.getPos();
		
		BlockState fillBlock = iuc.getWorld().getBlockState(pos);
		
		if (fillBlock.getBlock() == Blocks.BEDROCK)
		{
			fillBlock = Blocks.SAND.getDefaultState();
		}
		
    	switch(iuc.getFace())
    	{
    		case UP:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() +1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()), fillBlock);
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() +1), fillBlock);
    			break;
    	
    		case NORTH:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), fillBlock);
    			break;
    		case SOUTH:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1), fillBlock);
    			break;
    		case WEST:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()), fillBlock);
    			break;
    		case EAST:
    			this.placeDownColumns(iuc.getWorld(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()), fillBlock);
    			break;
    		default:
    			break;
    	}
    	
		if(this.placeDownColumns(iuc.getWorld(), pos, fillBlock))
		{
			return ActionResultType.SUCCESS;
		}		
		return ActionResultType.FAIL;
	}
	
    private boolean placeDownColumns(World world, BlockPos pos, BlockState fillBlock)
    {
    	boolean putFillBlock = false;

    	if(this.blockPosIsTargetBlock(world, pos))
    	{
        	BlockPos posYIter = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        	while(this.blockPosIsTargetBlock(world, posYIter))
        	{
        		world.setBlockState(posYIter, fillBlock);
        		putFillBlock = true;
        		posYIter = new BlockPos(posYIter.getX(), posYIter.getY() - 1, posYIter.getZ());
        	}
    	}
    	return putFillBlock;    	
    }
    
    private boolean blockPosIsTargetBlock(World world, BlockPos pos)
    {
    	ArrayList<Material> targetMaterial = new ArrayList<Material>();
    	targetMaterial.add(Material.AIR);
    	targetMaterial.add(Material.WATER);
    	targetMaterial.add(Material.LAVA);
    	targetMaterial.add(Material.LEAVES);
    	targetMaterial.add(Material.PLANTS);
    	targetMaterial.add(Material.SNOW);
    	targetMaterial.add(Material.CACTUS);
    	targetMaterial.add(Material.CARPET);
    	targetMaterial.add(Material.CAKE);
    	targetMaterial.add(Material.WOOL);
    	targetMaterial.add(Material.ICE);
    	targetMaterial.add(Material.OCEAN_PLANT);
    	targetMaterial.add(Material.SEA_GRASS);
    	targetMaterial.add(Material.WEB);
    	
    	ArrayList<Block> targetBlocks = new ArrayList<Block>();
    	targetBlocks.add(Blocks.GRASS);
    	targetBlocks.add(Blocks.FERN);
    	targetBlocks.add(Blocks.TALL_GRASS);
    	targetBlocks.add(Blocks.TORCH);
    	
    	return targetMaterial.contains(world.getBlockState(pos).getMaterial())
    			|| targetBlocks.contains(world.getBlockState(pos).getBlock());
    }
}

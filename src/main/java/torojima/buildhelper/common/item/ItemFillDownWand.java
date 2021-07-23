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

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class ItemFillDownWand extends Item
{
	public static final String NAME = "filldownwand_item";
		
	public ItemFillDownWand(Properties properties)
	{
		super(properties);
	}
	
	@Override
    public InteractionResult useOn(UseOnContext iuc)
	{
		BlockPos pos = iuc.getClickedPos();
		
		BlockState fillBlock = iuc.getLevel().getBlockState(pos);
		
		if (fillBlock.getBlock() == Blocks.BEDROCK)
		{
			fillBlock = Blocks.SAND.defaultBlockState();
		}
		
    	switch(iuc.getClickedFace())
    	{
    		case UP:
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()), fillBlock);
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() +1), fillBlock);
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1), fillBlock);
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() -1), fillBlock);
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()), fillBlock);
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() +1), fillBlock);
    			break;
    	
    		case NORTH:
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1), fillBlock);
    			break;
    		case SOUTH:
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1), fillBlock);
    			break;
    		case WEST:
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()), fillBlock);
    			break;
    		case EAST:
    			this.placeDownColumns(iuc.getLevel(), new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()), fillBlock);
    			break;
    		default:
    			break;
    	}
    	
		if(this.placeDownColumns(iuc.getLevel(), pos, fillBlock))
		{
			return InteractionResult.SUCCESS;
		}		
		return InteractionResult.FAIL;
	}
	
    private boolean placeDownColumns(Level world, BlockPos pos, BlockState fillBlock)
    {
    	boolean putFillBlock = false;

    	if(this.blockPosIsTargetBlock(world, pos))
    	{
        	BlockPos posYIter = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        	while(this.blockPosIsTargetBlock(world, posYIter))
        	{
        		world.setBlockAndUpdate(posYIter, fillBlock);
        		putFillBlock = true;
        		posYIter = new BlockPos(posYIter.getX(), posYIter.getY() - 1, posYIter.getZ());
        	}
    	}
    	return putFillBlock;    	
    }
    
    private boolean blockPosIsTargetBlock(Level world, BlockPos pos)
    {
    	ArrayList<Material> targetMaterial = new ArrayList<Material>();
    	targetMaterial.add(Material.AIR);
    	targetMaterial.add(Material.WATER);
    	targetMaterial.add(Material.LAVA);
    	targetMaterial.add(Material.LEAVES);
    	targetMaterial.add(Material.PLANT);
    	targetMaterial.add(Material.SNOW);
    	targetMaterial.add(Material.CACTUS);
    	targetMaterial.add(Material.DECORATION);
    	targetMaterial.add(Material.CAKE);
    	targetMaterial.add(Material.WOOL);
    	targetMaterial.add(Material.ICE);
    	targetMaterial.add(Material.WATER_PLANT);
        targetMaterial.add(Material.WEB);
        targetMaterial.add(Material.WOOD);
    	
    	ArrayList<Block> targetBlocks = new ArrayList<Block>();
    	targetBlocks.add(Blocks.GRASS);
    	targetBlocks.add(Blocks.FERN);
    	targetBlocks.add(Blocks.TALL_GRASS);
        targetBlocks.add(Blocks.TORCH);
        targetBlocks.add(Blocks.SEAGRASS);
        targetBlocks.add(Blocks.TALL_SEAGRASS);
    	
    	return targetMaterial.contains(world.getBlockState(pos).getMaterial())
    			|| targetBlocks.contains(world.getBlockState(pos).getBlock());
    }
}

// <copyright file="ItemSandWaterWand.java">
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
// <summary>Item class for the fill water with sand wand item</summary>

package torojima.buildhelper.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import torojima.buildhelper.BuildHelperMod;

public class ItemSandWaterWand extends Item
{
	public static final String NAME = "sandwaterwand";
	
	public ItemSandWaterWand()
	{
		super();
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setRegistryName(ItemSandWaterWand.NAME);
		this.setUnlocalizedName(ItemSandWaterWand.NAME);
		this.setMaxStackSize(1);
	}
		
	@Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
    	switch(facing)
    	{
    		case UP:
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() -1));
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()));
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() -1, pos.getY(), pos.getZ() +1));
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1));
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1));
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() -1));
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()));
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() +1, pos.getY(), pos.getZ() +1));
    			break;
    	
    		case NORTH:
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1));
    			break;
    		case SOUTH:
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1));
    			break;
    		case WEST:
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() -1, pos.getY(), pos.getZ()));
    			break;
    		case EAST:
    			this.placeSandColumn(worldIn, new BlockPos(pos.getX() +1, pos.getY(), pos.getZ()));
    			break;
    		default:
    			break;
    	}
		
		if(this.placeSandColumn(worldIn, pos))
		{
			return EnumActionResult.SUCCESS;
		}		
		return EnumActionResult.FAIL;
	}
	
	
//	@Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
//    {
//		System.out.println("rightclick");
//		
//		//// different approach
//        if (!worldIn.isRemote)
//        {
//            ItemArrow itemarrow = (ItemArrow)Items.ARROW;
//            EntityArrow entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), playerIn);
//            entityarrow.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 60.0F, 1.0F);
//            entityarrow.setDamage(50);
//            worldIn.spawnEntity(entityarrow);
//        }
//		
//		//// diff app end
//				
//		/// debug
//    	if(null != Minecraft.getMinecraft().objectMouseOver)
//    	{
//    		System.out.println(Minecraft.getMinecraft().objectMouseOver.getClass().toString());
//    		Entity hitEntity = Minecraft.getMinecraft().objectMouseOver.entityHit;
//        	if (null != hitEntity)
//        	{
//        		if(hitEntity instanceof EntityLivingBase)
//        		{
//        			System.out.println(hitEntity.getClass().toString());
//        		
////        		Entity hitEntityInWorld = worldIn.getEntityByID(hitEntity.getEntityId());
////        		hitEntityInWorld.attackEntityFrom(DamageSource.MAGIC, 10);
////        		playerIn.attackTargetEntityWithCurrentItem(hitEntityInWorld);
//        		}
//        		else
//        		{
//        			System.out.println("entity is not livingBase");
//        		}
//        	}
//        	else
//        	{
//        		System.out.println("entity is null");
//        	}        		
//    	}
//    	else
//    	{
//    		System.out.println("objectMouseOver is null");
//    	}
//		/// debug end
//        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
//    }
	
    private boolean placeSandColumn(World world, BlockPos pos)
    {
    	boolean putSand = false;

    	if(this.blockPosIsWater(world, pos))
    	{
        	BlockPos posYIter = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        	while(this.blockPosIsWater(world, posYIter))
        	{
        		world.setBlockState(posYIter, Blocks.SAND.getDefaultState());
        		putSand = true;
        		posYIter = new BlockPos(posYIter.getX(), posYIter.getY() - 1, posYIter.getZ());
        	}
    	}
    	return putSand;    	
    }
    
    private boolean blockPosIsWater(World world, BlockPos pos)
    {
    	return world.getBlockState(pos).getMaterial() == Material.WATER;
    }
}

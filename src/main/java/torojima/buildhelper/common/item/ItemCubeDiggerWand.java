package torojima.buildhelper.common.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCubeDiggerWand extends Item
{
	public static final String NAME = "cubediggerwand";

	public ItemCubeDiggerWand()
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setRegistryName(ItemCubeDiggerWand.NAME);
		this.setUnlocalizedName(ItemCubeDiggerWand.NAME);
		this.setMaxStackSize(1);
		GameRegistry.register(this);
	}
	
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {    	
    	BlockPos startPos;
    	BlockPos endPos;
    	
    	// side: 0 => bottom;  1 => top;  2 => z-;  3 => z+;  4 => x-;  5 => x+
    	switch(facing)
    	{
    	case DOWN:
    		startPos = new BlockPos(pos.getX() -1, pos.getY(),    pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX() +1, pos.getY() +2, pos.getZ() +1);
    		break;
    	case UP:
    		startPos = new BlockPos(pos.getX() -1, pos.getY() -2, pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX() +1, pos.getY(),    pos.getZ() +1);
    		break;
    	case NORTH:
    		startPos = new BlockPos(pos.getX() -1, pos.getY() -1, pos.getZ());
    		endPos   = new BlockPos(pos.getX() +1, pos.getY() +1, pos.getZ() +2);
    		break;
    	case SOUTH:
    		startPos = new BlockPos(pos.getX() -1, pos.getY() -1, pos.getZ() -2);
    		endPos   = new BlockPos(pos.getX() +1, pos.getY() +1, pos.getZ());
    		break;
    	case WEST:
    		startPos = new BlockPos(pos.getX(),    pos.getY() -1, pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX() +2, pos.getY() +1, pos.getZ() +1);
    		break;
    	case EAST:
    		startPos = new BlockPos(pos.getX() -2, pos.getY() -1, pos.getZ() -1);
    		endPos   = new BlockPos(pos.getX(),    pos.getY() +1, pos.getZ() +1);
    		break;
    	default:
    		return EnumActionResult.FAIL;
    	}
    	    	
		for(int x = startPos.getX(); x <= endPos.getX(); x++)
		{
			for(int y = startPos.getY(); y <= endPos.getY(); y++)
			{
				for(int z = startPos.getZ(); z <= endPos.getZ(); z++)
				{
					BlockPos currentPos = new BlockPos(x,y,z);
					Block currentBlock = worldIn.getBlockState(currentPos).getBlock();
					
			    	if(this.isDiggableBlock(currentBlock)
			    			&& !this.isBedRock(worldIn, currentPos))
			    	{
			    		worldIn.setBlockToAir(currentPos);
			    	}
				}
			}
		}
		return EnumActionResult.SUCCESS;
    }
    
	protected boolean isBedRock(World world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == Blocks.BEDROCK;
	}
    
    private boolean isDiggableBlock(Block block)
    {
    	ArrayList<Block> diggableBlocks = new ArrayList<Block>();
    	diggableBlocks.add(Blocks.DIRT);
    	diggableBlocks.add(Blocks.GRAVEL);
    	diggableBlocks.add(Blocks.STONE);
    	diggableBlocks.add(Blocks.COBBLESTONE);
    	diggableBlocks.add(Blocks.SAND);
    	diggableBlocks.add(Blocks.SANDSTONE);
    	diggableBlocks.add(Blocks.GRASS);
    	diggableBlocks.add(Blocks.END_STONE);
    	diggableBlocks.add(Blocks.NETHERRACK);
    	diggableBlocks.add(Blocks.RED_SANDSTONE);
    	diggableBlocks.add(Blocks.STAINED_HARDENED_CLAY);
    	
    	return diggableBlocks.contains(block);
    }
}

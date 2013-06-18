package buildhelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCubeDiggerWand extends Item
{
	private class Pos
	{
		public final int x;
		public final int y;
		public final int z;

		public Pos(int x, int y, int z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public ItemCubeDiggerWand(int itemId)
	{
		super(itemId);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTools);		
	}
	
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
    {    	
    	Pos startPos;
    	Pos endPos;
    	
    	switch(side)
    	{
    	case 0:
    		startPos = new Pos(posX -1, posY,    posZ -1);
    		endPos   = new Pos(posX +1, posY +2, posZ +1);
    		break;
    	case 1:
    		startPos = new Pos(posX -1, posY -2, posZ -1);
    		endPos   = new Pos(posX +1, posY,    posZ +1);
    		break;
    	case 2:
    		startPos = new Pos(posX -1, posY -1, posZ);
    		endPos   = new Pos(posX +1, posY +1, posZ +2);
    		break;
    	case 3:
    		startPos = new Pos(posX -1, posY -1, posZ -2);
    		endPos   = new Pos(posX +1, posY +1, posZ);
    		break;
    	case 4:
    		startPos = new Pos(posX,    posY -1, posZ -1);
    		endPos   = new Pos(posX +2, posY +1, posZ +1);
    		break;
    	case 5:
    		startPos = new Pos(posX -2, posY -1, posZ -1);
    		endPos   = new Pos(posX,    posY +1, posZ +1);
    		break;
    	default:
    		return false;
    	}
    	    	
		for(int x = startPos.x; x <= endPos.x; x++)
		{
			for(int y = startPos.y; y <= endPos.y; y++)
			{
				for(int z = startPos.z; z <= endPos.z; z++)
				{
			    	int blockId = world.getBlockId(x, y, z);
			    	if(this.isDiggableBlock(blockId))
			    	{
			    		world.setBlockToAir(x, y, z);
			    	}
				}
			}
		}
		return true;
    }
    
    private boolean isDiggableBlock(int blockId)
    {
    
    	if(        blockId == Block.dirt.blockID  || blockId == Block.gravel.blockID
    			|| blockId == Block.stone.blockID || blockId == Block.cobblestone.blockID
    			|| blockId == Block.sand.blockID  || blockId == Block.sandStone.blockID
    			|| blockId == Block.grass.blockID)
    	{
    		return true;
    	}
    	return false;
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_CUBE_DIGGER_WAND);
	}

}

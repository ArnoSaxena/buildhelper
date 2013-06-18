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

public class ItemSandInWaterWand extends Item
{

	public ItemSandInWaterWand(int itemId)
	{
		super(itemId);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
    {
    	// side: 0 => bottom;  1 => top;  2 => z-;  3 => z+;  4 => x-;  5 => x+
    	
    	boolean putSand = false;
    	
    	switch(side)
    	{
    		case 1:
    			this.placeSandColumn(world, posX -1, posY, posZ -1);
    			this.placeSandColumn(world, posX -1, posY, posZ);
    			this.placeSandColumn(world, posX -1, posY, posZ +1);
    			this.placeSandColumn(world, posX, posY, posZ -1);
    			this.placeSandColumn(world, posX, posY, posZ +1);    			
    			this.placeSandColumn(world, posX +1, posY, posZ -1);
    			this.placeSandColumn(world, posX +1, posY, posZ);    			
    			this.placeSandColumn(world, posX +1, posY, posZ +1);
    			break;
    	
    		case 2:
    			this.placeSandColumn(world, posX, posY, posZ -1);
    			break;
    		case 3:
    			this.placeSandColumn(world, posX, posY, posZ +1);
    			break;
    		case 4:
    			this.placeSandColumn(world, posX -1, posY, posZ);
    			break;
    		case 5:
    			this.placeSandColumn(world, posX +1, posY, posZ);
    			break;
    		default:
    			break;
    	}
    	return putSand;
    }
    
    private boolean placeSandColumn(World world, int posX, int posY, int posZ)
    {
    	boolean putSand = false;
    	
    	if(this.blockPosIsWater(world, posX, posY, posZ))
    	{
        	int sandAmount = 0;
        	int posYIter = posY;
        	
        	while(this.blockPosIsWater(world, posX, posYIter, posZ))
        	{
        		sandAmount++;
        		posYIter--;
        	}
        	
        	for(int i = 0; i < sandAmount; i++)
        	{
        		world.setBlock(posX, posY + i, posZ, Block.sand.blockID);
        		putSand = true;
        	}
    	}
    	return putSand;    	
    }
    
    private boolean blockPosIsWater(World world, int posX, int posY, int posZ)
    {
    	int blockId = world.getBlockId(posX, posY, posZ);
    	if(blockId == Block.waterStill.blockID || blockId == Block.waterMoving.blockID)
    	{
    		return true;
    	}    	
    	return false;
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_SAND_WATER_WAND);
	}
}

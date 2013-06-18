package buildhelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemShiftUpWand extends ItemShiftWand
{
	public ItemShiftUpWand(int itemId)
	{
		super(itemId);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
    	this.icons.add(0, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_ALL_FILL_WAND));
    	this.icons.add(1, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_ALL_FILL_WAND_NAMED));
    	this.icons.add(2, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_ALL_FILL_WAND_CHARGED));
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int par1)
	{
    	return this.icons.get(this.status);
	}

	@Override
	protected void moveBlock(World world, Pos pos)
	{
		Pos newPos = new Pos(pos.x, pos.y + 1, pos.z);
		
		int blockId = world.getBlockId(pos.x, pos.y, pos.z);
		world.setBlockToAir(pos.x, pos.y, pos.z);
		world.setBlock(newPos.x, newPos.y, newPos.z, blockId);
		
		if(world.blockHasTileEntity(pos.x, pos.y, pos.z))
		{
			TileEntity tileEntity = world.getBlockTileEntity(pos.x, pos.y, pos.z);
			world.setBlockTileEntity(pos.x, pos.y, pos.z, null);
			world.setBlockTileEntity(newPos.x, newPos.y, newPos.z, tileEntity);
		}		
	}	
}

package buildhelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFillWandCobble extends ItemFillWand
{

	public ItemFillWandCobble(int itemId)
	{
		super(itemId);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
    	this.icons.add(0, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_COBBLE_FILL_WAND));
    	this.icons.add(1, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_COBBLE_FILL_WAND_CHARGED));
    	this.icons.add(2, this.icons.get(1));
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
	{
		BlockType usedBlockType = new BlockType(Block.cobblestone.blockID, 0);
		this.usedBlockId.put(player.username, usedBlockType);
		return super.onItemUse(itemStack, player, world, posX, posY, posZ, side, par8, par9, par10);
	}
}



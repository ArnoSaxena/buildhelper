package buildhelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGapFillWaterWand extends ItemGapFillWand
{
	public ItemGapFillWaterWand(int itemId)
	{
		super(itemId);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.icons.add(0, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILLWATER_WAND));
		this.icons.add(1, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILLWATER_WAND));
		this.icons.add(2, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILLWATER_WAND_CHARGED));
		this.icons.add(3, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILLWATER_WAND));
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
	{
		if(this.status == NONE)
		{
			BlockType clfBlockType = new BlockType(0, 0);
			this.fillBlockId.put(player.username, clfBlockType);
			
			BlockType cluBlockType = new BlockType(Block.waterStill.blockID, 0);
			this.usedBlockId.put(player.username, cluBlockType);
			this.status = FILL;
		}
		return super.onItemUse(itemStack, player, world, posX, posY, posZ, side, par8, par9, par10);
	}	
}

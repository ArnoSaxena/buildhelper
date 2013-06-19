package buildhelper;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGapFillWand extends ItemExchangeWand
{
	public ItemGapFillWand(int itemId)
	{
		super(itemId);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.icons.add(0, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILL_WAND));
		this.icons.add(1, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILL_WAND_FILL));
		this.icons.add(2, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILL_WAND_CHARGED));
		this.icons.add(3, iconRegister.registerIcon(Constants.TBH_MODID + ":" + Constants.KEY_GAPFILL_WAND_FILL));
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10)
	{		
		if(this.status == NONE)
		{
			BlockType clBlockType = new BlockType(0, 0);
			this.fillBlockId.put(player.username, clBlockType);
			this.status = NAMED;
		}
		return super.onItemUse(itemStack, player, world, posX, posY, posZ, side, par8, par9, par10);
	}
}

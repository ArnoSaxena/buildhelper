package torojima.buildhelper.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemGapFillWand extends ItemExchangeWand
{
	public static final String NAME = "gapfillwand";
	
	public ItemGapFillWand()
	{
		super(false);
		this.setRegistryName(ItemGapFillWand.NAME);
		this.setUnlocalizedName(ItemGapFillWand.NAME);
		this.setMaxStackSize(1);
		GameRegistry.register(this);
	}
	
	public ItemGapFillWand(boolean register)
	{
		super(register);
		if(register)
		{
			this.setRegistryName(ItemGapFillWand.NAME);
			this.setUnlocalizedName(ItemGapFillWand.NAME);
			this.setMaxStackSize(1);
			GameRegistry.register(this);
		}
	}

	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{		
		if(this.status == NONE)
		{
			this.fillBlock.put(playerIn.getName(), Blocks.AIR);
			this.status = NAMED;
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}

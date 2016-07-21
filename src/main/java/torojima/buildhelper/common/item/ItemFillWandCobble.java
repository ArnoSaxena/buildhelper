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

public class ItemFillWandCobble extends ItemFillWand
{
	public static final String NAME = "fillwandcobble";
	
	public ItemFillWandCobble()
	{
		super();
		this.setRegistryName(ItemFillWandCobble.NAME);
		this.setUnlocalizedName(ItemFillWandCobble.NAME);
		this.setMaxStackSize(1);
		GameRegistry.register(this);
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		this.usedBlock.put(playerIn.getName(), Blocks.COBBLESTONE);
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
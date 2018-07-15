package torojima.buildhelper.common.item;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemArrowWand extends Item
{
	public static final String NAME = "arrowwand";
	public static final float ATK_DMG = 50.0F;

	public ItemArrowWand()
	{
		super();
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setRegistryName(ItemArrowWand.NAME);
		this.setUnlocalizedName(ItemArrowWand.NAME);
		this.setMaxStackSize(1);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote)
        {
            ItemArrow itemarrow = (ItemArrow)Items.ARROW;
            EntityArrow entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), playerIn);
            float arrowVelocity = 60.0F;
            entityarrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, arrowVelocity, 1.0F);
            entityarrow.setDamage(ATK_DMG);
            worldIn.spawnEntity(entityarrow);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
	
    public float getAttackDamage()
    {
        return ATK_DMG;
    }
}

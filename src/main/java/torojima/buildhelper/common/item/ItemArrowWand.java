// <copyright file="ItemArrowWand.java">
// Copyright (c) 2018 All Right Reserved, http://buildhelper.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2018-03-09</date>
// <summary>Item class for the arrow wand item</summary>

package torojima.buildhelper.common.item;

//import java.util.function.BooleanSupplier;
//import com.google.gson.JsonObject;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
//import net.minecraftforge.common.crafting.IConditionSerializer;

public class ItemArrowWand extends Item
{
	public static final String NAME = "arrowwand_item";
	public static final float ATK_DMG = 50.0F;

	public ItemArrowWand(Properties properties)
	{
		super(properties);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if (!worldIn.isRemote)
        {
        	ArrowItem itemarrow = (ArrowItem)Items.ARROW;
            AbstractArrowEntity entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), playerIn);
            float arrowVelocity = 60.0F;
            entityarrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, arrowVelocity, 1.0F);
            entityarrow.setDamage(ATK_DMG);
            worldIn.addEntity(entityarrow);
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }
	
    public float getAttackDamage()
    {
        return ATK_DMG;
    }
    
//    public static class RecipeCondition implements IConditionSerializer
//   {
//		@Override
//		public BooleanSupplier parse(JsonObject json)
//		{
//			BooleanSupplier recipeContition = () -> (true);// implement the config entry here
//			return recipeContition;
//		}
//    }
}

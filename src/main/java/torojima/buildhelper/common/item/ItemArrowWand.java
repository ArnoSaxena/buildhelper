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

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;


//import net.minecraftforge.common.crafting.IConditionSerializer;

public class ItemArrowWand extends Item
{
	public static final String NAME = "arrowwand_item";
	public static final float ATK_DMG = 50.0F;

	public ItemArrowWand(Item.Properties properties)
	{
		super(properties);
	}

	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        if (!worldIn.isClientSide)
        {
        	ArrowItem itemarrow = (ArrowItem)Items.ARROW;
            AbstractArrow entityarrow = itemarrow.createArrow(worldIn, new ItemStack(Items.ARROW), playerIn);
            float arrowPower = 60.0F;
            float uncertainty = 0.0F;            
            entityarrow.shoot(
                    playerIn.getLookAngle().x,
                    playerIn.getLookAngle().y,
                    playerIn.getLookAngle().z,
                    arrowPower, 
                    uncertainty);
            
            //entityarrow.shoot(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, arrowVelocity, 1.0F);
            entityarrow.setBaseDamage(ATK_DMG);
            worldIn.addFreshEntity(entityarrow);
        }
        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
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

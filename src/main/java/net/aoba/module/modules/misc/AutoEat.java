/*
* Aoba Hacked Client
* Copyright (C) 2019-2023 coltonk9043
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * autoEat Module
 */
package net.aoba.module.modules.misc;

import org.lwjgl.glfw.GLFW;
import net.aoba.module.Module;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.network.packet.Packet;

public class AutoEat extends Module {

	private int hunger = 6;
	public AutoEat() {
		this.setName("AutoEat");
		this.setBind(new KeyBinding("key.autoeat", GLFW.GLFW_KEY_N, "key.categories.aoba"));
		this.setCategory(Category.Misc);
		this.setDescription("Automatically eats the best food in your inventory.");
	}

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
	
	}

	@Override
	public void onToggle() {

	}

	@Override
	public void onUpdate() {
		if(MC.player.getHungerManager().getFoodLevel() <= hunger) {
			int foodSlot= -1;
			FoodComponent bestFood = null;
			for(int i = 0; i< 9; i++) {
				Item item = MC.player.getInventory().getStack(i).getItem();
				
				if(!item.isFood()) {
					continue;
				}
				FoodComponent food = item.getFoodComponent();
				if(bestFood != null) {
					if(food.getHunger() > bestFood.getHunger()) {
						bestFood = food;
						foodSlot = i;
					}
				}else {
					bestFood = food;
					foodSlot = i;
				}
				
			}
			
		    if(bestFood != null) {
		    	MC.player.getInventory().selectedSlot = foodSlot;
		    	MC.options.useKey.setPressed(true);
		    }
		}
	}

	@Override
	public void onRender(MatrixStack matrixStack, float partialTicks) {
		
	}

	@Override
	public void onSendPacket(Packet<?> packet) {
		
	}

	@Override
	public void onReceivePacket(Packet<?> packet) {
		
	}
	
	public void setHunger(int hunger) {
		
	}
}
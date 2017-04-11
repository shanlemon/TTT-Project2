package com.tilegame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.gfx.Assets;
import com.gfx.Text;
import com.sun.glass.events.KeyEvent;
import com.tilegame.Handler;
import com.tilegame.items.Item;

public class Inventory {
	int invWidth, invHeight, invX, invY, invCenterX, invCenterY;
	
	private int invImageX = 465, invImageY = 190, invImageWidth = 100, invImageHeight = 100, invImageAmountX = 515, invImageAmountY = 355;

	private Handler handler;
	public boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int selectedItem = 0;
	private int invListSpacing = 65;

	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		
		invWidth = 500;
		invHeight = 500;
		invX = handler.getGame().getWidth() /2 - (invWidth /2);
		invY = handler.getGame().getHeight() /2 - (invHeight /2);
		invCenterX = (int) (invX + (invWidth / 3.4));
		invCenterY = (int) (invY + (invHeight / 1.95));
		
		
	}

	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
		}
		if (!active) {
			return;
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
			selectedItem++;

		if(selectedItem < 0 )
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
	}

	public void render(Graphics g) {
		if (!active) {
			return;
		}
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
		
		int len = inventoryItems.size();
		if(len == 0)
			return;
		
		for(int i = -3; i < 4; i++){
			if(selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			
			if(i == 0){
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invCenterX, invCenterY + i * invListSpacing, true, Color.DARK_GRAY, Assets.font28);
			}else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invCenterX, invCenterY + i * invListSpacing, true, Color.gray, Assets.font28);

			}
		}
		
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invImageAmountX, invImageAmountY, true, Color.BLACK, Assets.font56);

	}

	// Inventory methods
	public void addItem(Item item) {
		for (Item i : inventoryItems) {
			if (i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}

		inventoryItems.add(item);
	}

	// Getters and setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}

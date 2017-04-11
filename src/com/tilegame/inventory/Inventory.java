package com.tilegame.inventory;

import java.awt.Graphics;
import java.util.ArrayList;

import com.gfx.Assets;
import com.sun.glass.events.KeyEvent;
import com.tilegame.Handler;
import com.tilegame.items.Item;

public class Inventory {
	int invWidth, invHeight, invX, invY;

	private Handler handler;
	public boolean active = false;
	private ArrayList<Item> inventoryItems;

	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		
		invWidth = 500;
		invHeight = 500;
		invX = handler.getGame().getWidth() /2 - (invWidth /2);
		invY = handler.getGame().getHeight() /2 - (invHeight /2);
	
	}

	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
		}
		if (!active) {
			return;
		}

	}

	public void render(Graphics g) {
		if (!active) {
			return;
		}
		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

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

}

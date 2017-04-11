package com.entity.statics;

import java.awt.Color;
import java.awt.Graphics;

import com.gfx.Assets;
import com.tilegame.Handler;
import com.tilegame.items.Item;
import com.tilegame.tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2);

		bounds.width = 13;
		bounds.height = 50;
		bounds.x = ((Tile.TILEWIDTH * 2) / 2) - (bounds.width / 2);
		bounds.y = Tile.TILEHEIGHT + 10;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//g.setColor(Color.red);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
		//		(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x, (int)y));
		
	}

}

package com.tilegame.worlds;

import java.awt.Graphics;

import com.entity.EntityManager;
import com.entity.creatures.Player;
import com.entity.statics.Rock;
import com.entity.statics.Tree;
import com.tilegame.Handler;
import com.tilegame.items.ItemManager;
import com.tilegame.tiles.Tile;
import com.tilegame.utils.Utils;

public class World {

	Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;

	// Entities
	private EntityManager entityManager;
	private ItemManager itemManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);

		loadWorld(path);
		spawnX = handler.getWidth()/2;
		spawnY = handler.getHeight()/2;
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		generateTrees(100);
		
		entityManager.addEntity(new Rock(handler, (1 * Tile.TILEWIDTH) - (Tile.TILEWIDTH/2), (1 * Tile.TILEHEIGHT) - (Tile.TILEHEIGHT + 15)));


	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		;

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		itemManager.render(g);
		entityManager.render(g);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}

		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null){
			return Tile.dirtTile;
		}
		return t;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}

	}
	
	public void generateTrees(int amountOfTrees){
		for (int i = 0; i < amountOfTrees; i++) {
			int x = (int) (Math.random() * width);
			int y = (int) (Math.random() * height);
			if (getTile(x, y).isOrganic()) {
				entityManager.addEntity(new Tree(handler, (x * Tile.TILEWIDTH) - (Tile.TILEWIDTH/2), (y * Tile.TILEHEIGHT) - (Tile.TILEHEIGHT + 15)));
			}
		}
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}

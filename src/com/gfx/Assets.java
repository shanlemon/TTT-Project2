package com.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	private static final int playerWidth = 32, playerHeight = 48;

	public static Font font28, font56;
	
	public static BufferedImage grass, dirt, road, sand, stone, stoneDrop, rock, tree, woodDrop, inventoryScreen, menuScreen;
	
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	
	public static BufferedImage[] btn_start, btn_exit;
	
	
	public static void init(){
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font56 = FontLoader.loadFont("res/fonts/slkscr.ttf", 56);

		
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheetV2.png"));
		SpriteSheet charSheet = new SpriteSheet(ImageLoader.loadImage("/textures/CharacterSpriteSheet.png"));
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/MenuSheet.png"));

		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0, 0, width * 2, height);
		btn_start[1] = menuSheet.crop(0, height, width * 2, height);

		btn_exit = new BufferedImage[2];
		btn_exit[0] = menuSheet.crop(width * 2, 0, width * 2, height);
		btn_exit[1] = menuSheet.crop(width * 2, height,width * 2, height);
		
		player_down = new BufferedImage[4];
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
		player_down[0] = charSheet.crop(0, 0, playerWidth, playerHeight);
		player_down[1] = charSheet.crop(playerWidth, 0, playerWidth, playerHeight);
		player_down[2] = charSheet.crop(playerWidth * 2, 0, playerWidth, playerHeight);
		player_down[3] = charSheet.crop(playerWidth * 3, 0, playerWidth, playerHeight);
		
		player_up[0] = charSheet.crop(0, playerHeight, playerWidth, playerHeight);
		player_up[1] = charSheet.crop(playerWidth, playerHeight, playerWidth, playerHeight);
		player_up[2] = charSheet.crop(playerWidth * 2, playerHeight, playerWidth, playerHeight);
		player_up[3] = charSheet.crop(playerWidth * 3, playerHeight, playerWidth, playerHeight);
		
		player_left[0] = charSheet.crop(0, playerHeight * 2, playerWidth, playerHeight);
		player_left[1] = charSheet.crop(playerWidth, playerHeight * 2, playerWidth, playerHeight);
		player_left[2] = charSheet.crop(playerWidth * 2, playerHeight * 2, playerWidth, playerHeight);
		player_left[3] = charSheet.crop(playerWidth * 3, playerHeight * 2, playerWidth, playerHeight);
		
		player_right[0] = charSheet.crop(0, playerHeight * 3, playerWidth, playerHeight);
		player_right[1] = charSheet.crop(playerWidth, playerHeight*3, playerWidth, playerHeight);
		player_right[2] = charSheet.crop(playerWidth * 2, playerHeight*3, playerWidth, playerHeight);
		player_right[3] = charSheet.crop(playerWidth * 3, playerHeight*3, playerWidth, playerHeight);

		
		grass = sheet.crop(0, 0, width,	height );
		dirt = sheet.crop(width, 0, width, height);
		road = sheet.crop(width * 2, 0, width,	height );
		sand = sheet.crop(width * 3, 0, width,	height );
		stone = sheet.crop(width * 4, 0, width,	height );
		stoneDrop = sheet.crop(0, height * 4, width,height );
		rock = sheet.crop(width, height * 3, width *2,	height*2 );
		tree = sheet.crop(width *3, height * 3, width *2,	height*2 );
		woodDrop = sheet.crop(0,height * 3, width,	height );


		
		
		menuScreen = ImageLoader.loadImage("/textures/MenuScreen.png");
		inventoryScreen = ImageLoader.loadImage("/textures/Inventory.png");
		
//		p = playerSheet.crop(0, 0, width, height);
//		pN = playerSheet.crop(width, 0, width, height);
//		pS = playerSheet.crop(width*2, 0, width, height);
//		pE = playerSheet.crop(width*3, 0, width, height);
//		pW = playerSheet.crop(0, height, width, height);
//		pNE = playerSheet.crop(0, height, width, height);
//		pSE = playerSheet.crop(0, height, width, height);
//		pSW = playerSheet.crop(0, height, width, height);
//		pNW = playerSheet.crop(0, height, width, height);


		
		
	}
}

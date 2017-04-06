package com.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	private static final int playerWidth = 32, playerHeight = 48;

	
	public static BufferedImage grass, dirt, tree, chest, stone;
	
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	
	public static BufferedImage[] btn_start;
	
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));
		SpriteSheet charSheet = new SpriteSheet(ImageLoader.loadImage("/textures/CharacterSpriteSheet.png"));
		
		btn_start = new BufferedImage[2];
		btn_start[0] = charSheet.crop(0, playerHeight*4, 64, 28);
		btn_start[1] = charSheet.crop(0, playerHeight*4 + 26, 64, 28);

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
		chest = sheet.crop(width*3, 0, width, height);
		stone = sheet.crop(0, height, width, height);
		tree = sheet.crop(0, height * 2, width * 2, height * 2);
		
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

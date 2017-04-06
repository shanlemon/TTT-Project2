package com.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;

	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}

	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer = 0;
			index %= frames.length;
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public BufferedImage getImageAtIndex(int index) {
		try {
			return frames[index];
		} catch (Exception e) {
			return frames[0];
		}
	}
	
	public int getCurrentIndex(){
		return index;
	}

}

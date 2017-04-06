package com.entity.creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gfx.Animation;
import com.gfx.Assets;
import com.tilegame.Handler;

public class Player extends Creature {

	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	int animationSpeed;
	private Animation currentAnimation;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 20;
		bounds.y = 43;
		bounds.width = 25;
		bounds.height = 42;

		width = 64;
		height = 96;
		speed = 5;

		// Animations
		animationSpeed = (int) (speed * 30);
		animDown = new Animation(animationSpeed, Assets.player_down);
		animUp = new Animation(animationSpeed, Assets.player_up);
		animLeft = new Animation(animationSpeed, Assets.player_left);
		animRight = new Animation(animationSpeed, Assets.player_right);
		currentAnimation = new Animation(animationSpeed, Assets.player_down);
	}

	@Override
	public void tick() {
		// Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();

		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().up)
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		g.setFont(new Font("Roboto", 25, 20));
		g.setColor(Color.red);
		g.drawString("Margaret Lea", (int) (x - handler.getGameCamera().getxOffset()) - 25,
				(int) (y - handler.getGameCamera().getyOffset()));

		// g.setColor(Color.red);
		// g.fillRect((int)(x + bounds.x -
		// handler.getGameCamera().getxOffset()),
		// (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
		// bounds.width, bounds.height);
	}

	private BufferedImage getCurrentAnimationFrame() {
		int index = 0;
		if (xMove < 0) {
			currentAnimation = animLeft;
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			currentAnimation = animRight;
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			currentAnimation = animUp;
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			currentAnimation = animDown;
			return animDown.getCurrentFrame();
		} else {
			
			
				return currentAnimation.getImageAtIndex(0);
			
		}

	}

}

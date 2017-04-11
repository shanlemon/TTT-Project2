package com.entity.creatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.entity.Entity;
import com.gfx.Animation;
import com.gfx.Assets;
import com.tilegame.Handler;
import com.tilegame.inventory.Inventory;

public class Player extends Creature {

	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	int animationSpeed;
	private Animation currentAnimation;
	
	private long lastAttackTimer, attackCoolDown = 80, attackTimer = attackCoolDown;
	
	// Inventory
	private Inventory inventory;

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
		
		inventory = new Inventory(handler);
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
		
		//ATTACK
		checkAttacks();
		
		//Inventory
		inventory.tick();
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCoolDown){
			return;
		}
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp){
			ar.x = cb.x + cb.width/2  -arSize/2;
			ar.y = cb.y - arSize;
		}else if(handler.getKeyManager().aDown){
			ar.x = cb.x + cb.width/2  -arSize/2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft){
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize /2;
		}else if(handler.getKeyManager().aRight){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize /2;
		}else{
			return;
		}
		
		attackTimer = 0;

		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)){
				continue;
			}
			
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				e.hurt(1);
				return;
			}
		}
		
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
		
		//inventory.render(g);

		// g.setColor(Color.red);
		// g.fillRect((int)(x + bounds.x -
		// handler.getGameCamera().getxOffset()),
		// (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
		// bounds.width, bounds.height);
	}
	
	public void postRender(Graphics g){
		inventory.render(g);
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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

	@Override
	public void die() {
		System.out.println("you lose");
		
	}

}

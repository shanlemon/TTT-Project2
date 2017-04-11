package com.state;

import java.awt.Graphics;

import com.entity.creatures.Player;
import com.tilegame.Handler;
import com.tilegame.worlds.World;

public class GameState extends State{
	
	private World world;
	

	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/testWorld.txt");
		handler.setWorld(world);
		
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

	
	
}

package com.state;

import java.awt.Graphics;

import com.tilegame.Handler;

public abstract class State {

	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	public static State getState(){
		return currentState;
	}
	
	
	//Class
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	
	//---------abstract stuff----------------
	public abstract void tick();

	public abstract void render(Graphics g);
	
}

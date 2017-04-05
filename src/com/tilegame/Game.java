  package com.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.display.Display;
import com.gfx.Assets;
import com.gfx.GameCamera;
import com.input.KeyManager;
import com.input.MouseManager;
import com.state.GameState;
import com.state.MenuState;
import com.state.State;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;

	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	//states
	public State gameState;
	public State menuState;
	//private State settingsState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		

	}

	private void init() {
		start();
		keyManager = new KeyManager();
		mouseManager = new MouseManager();

		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		handler = new Handler(this);

		gameCamera = new GameCamera(handler, 0, 0);
		

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		//settingsState = new SettingsState(handler);
		
		State.setState(menuState);
	}

	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null){
			State.getState().tick();
		}
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();

		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		// clear screen
		g.clearRect(0, 0, width, height);
		// draw
		if(State.getState() != null){
			State.getState().render(g);
		}
		
		// stop draw
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		
	//	int frames = 0, ticks = 0;
		
		
		
//		init();
//		
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}
	
	
	

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

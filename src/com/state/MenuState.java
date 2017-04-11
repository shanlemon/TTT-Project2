package com.state;

import java.awt.Graphics;

import com.gfx.Assets;
import com.tilegame.Handler;
import com.tilegame.ui.ClickListener;
import com.tilegame.ui.UIImageButton;
import com.tilegame.ui.UIManager;

public class MenuState extends State{
	
	private UIManager uiManager;

	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton((handler.getWidth()/2) - 128, (handler.getHeight()/2) - 64, 256, 128, Assets.btn_start,new ClickListener(){
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
		//State.setState(handler.getGame().gameState);
	}

	
	
	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
	}

}

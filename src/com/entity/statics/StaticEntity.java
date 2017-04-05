package com.entity.statics;

import com.entity.Entity;
import com.tilegame.Handler;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}
}

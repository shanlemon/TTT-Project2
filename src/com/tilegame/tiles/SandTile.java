package com.tilegame.tiles;

import com.gfx.Assets;

public class SandTile extends Tile{

	public SandTile(int id) {
		super(Assets.sand, id);
	}
	
	@Override
	public boolean isOrganic(){
		return false;
	}

}

package com.tilegame.tiles;

import com.gfx.Assets;

public class RoadTile extends Tile{

	public RoadTile(int id) {
		super(Assets.road, id);
	}
	
	@Override
	public boolean isOrganic(){
		return false;
	}

}

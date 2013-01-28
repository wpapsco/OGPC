package com.papsco.GamePlayStateStuff.Mapping;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.GamePlayStateStuff.RunState;

public class LocationalEvent extends Event {

	Vector2f loc;
	
	public LocationalEvent(Vector2f location, boolean constant) {
		super(constant);
		loc = location;
	}

	@Override
	public boolean condition(GameContainer c, RunState s) {
		return s.getPlayer().getCollisionRect().intersects(new Rectangle(loc.x, loc.y, 2, 2));
	}

	@Override
	public void effect(GameContainer c, RunState s) {
		s.nextLevel();
	}

}

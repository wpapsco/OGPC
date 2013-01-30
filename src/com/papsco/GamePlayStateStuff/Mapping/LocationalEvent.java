package com.papsco.GamePlayStateStuff.Mapping;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.GamePlayStateStuff.RunState;

public class LocationalEvent extends Event {

	Vector2f loc;
	Shape area;
	private boolean isPoint;
	
	public LocationalEvent(Vector2f location, boolean constant) {
		super(constant);
		loc = location;
		isPoint = true;
	}

	public LocationalEvent(Shape area, boolean constant) {
		super(constant);
		this.area = area;
		isPoint = false;
	}
	
	@Override
	public boolean condition(GameContainer c, RunState s) {
		if (isPoint) {
			return s.getPlayer().getCollisionRect().intersects(new Rectangle(loc.x, loc.y, 2, 2));
		}else {
			return s.getPlayer().getCollisionRect().intersects(area);
		}
	}

	@Override
	public void effect(GameContainer c, RunState s) {
		this.executed = true;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (!isPoint) {
			g.setColor(Color.pink);
			g.draw(area);
		}
	}

}

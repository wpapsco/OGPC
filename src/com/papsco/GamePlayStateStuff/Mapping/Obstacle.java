package com.papsco.GamePlayStateStuff.Mapping;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public class Obstacle {

	Shape obstacleShape;
	
	public Obstacle(Shape s) {
		obstacleShape = s;
	}
	
	public void draw(Graphics g) {
		g.draw(obstacleShape);
	}

	public boolean collides(Shape s) {
		return obstacleShape.intersects(s);
	}
	
}

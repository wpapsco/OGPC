package com.papsco.GamePlayStateStuff;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Enemy {

	private final int MAX_HEALTH = 100;
	private int health = MAX_HEALTH;
	private Image image;
	private Vector2f loc;
	private Shape collisionRect;
	private boolean isDead;
	
	public Enemy(Image image, Vector2f location) {
		this.image = image;
		loc = location;
		collisionRect = new Rectangle(0, 0, image.getWidth(), image.getHeight());
		collisionRect.setCenterX(loc.x);
		collisionRect.setCenterY(loc.y);
		isDead = false;
	}

	public void update(GameContainer c, RunState s) {
		
	}

	public void takeDamage(int damage) {
		health-=damage;
		if (health <= 0) {onDeath();}
	}
	
	public void onDeath() {
		isDead = true;
	}
	
	public Shape getCollisionRect() {
		return collisionRect;
	}
	
	public void draw(Graphics g) {
		image.drawCentered(loc.x, loc.y);
		g.setColor(Color.green);
		g.draw(collisionRect);
	}

	public boolean isMarkedForDeletion() {
		// TODO Auto-generated method stub
		return isDead;
	}

	public Vector2f getLoc() {
		// TODO Auto-generated method stub
		return loc;
	}

	public boolean isDead() {
		// TODO Auto-generated method stub
		return isDead;
	}

	public void isDead(boolean b) {
		isDead = b;
	}
}

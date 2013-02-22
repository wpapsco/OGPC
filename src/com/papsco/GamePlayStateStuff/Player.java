package com.papsco.GamePlayStateStuff;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.GamePlayStateStuff.Mapping.Map;

public class Player {
	private Vector2f loc;
	private Image image;
	private Image bulletImage;
	private Shape collisionRect;
	private boolean isColliding = false;
	private ArrayList<Bullet> bullets;
	float rotation;
	float fireRecharge;
	
	public Player(Vector2f loc, Image image) throws SlickException {
		this.loc = loc;
		this.image = image;
		collisionRect = new Rectangle(0, 0, image.getHeight() - 2, image.getWidth() - 2);
		collisionRect.setCenterX(loc.x);
		collisionRect.setCenterY(loc.y);
		rotation = 0;
		bulletImage = new Image("pics/Bullet.png");
		bullets = new ArrayList<Bullet>();
	}
	public Vector2f getLoc() {
		return loc;
	}
	public void draw(Graphics g) {
		image.drawCentered(loc.x, loc.y);
		g.setColor(Color.green);
//		g.draw(collisionRect);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
	}
	
	public void update(int delta, Map m) {
		if (fireRecharge <= 1000) {
			fireRecharge+=delta;
		}
		ArrayList<Bullet> TList = new ArrayList<Bullet>();
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(delta);
			if (m.isColliding(bullets.get(i).getCollisionRect())) {
				bullets.get(i).setExplosionLocation(bullets.get(i).getPath().currentPoint);
				bullets.get(i).markDeleted();
			}
			if (!bullets.get(i).isFinishedExploding()) {
				TList.add(bullets.get(i));
			}
		}
		collisionRect = new Rectangle(0, 0, image.getHeight() - 2, image.getWidth() - 2);
		collisionRect.transform(Transform.createRotateTransform((float) Math.toRadians(45)));
		collisionRect.setCenterX(loc.x);
		collisionRect.setCenterY(loc.y);
		bullets = TList;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void reset(Vector2f vector2f) {
		loc = vector2f;
		collisionRect = new Rectangle(0, 0, image.getHeight() - 2, image.getWidth() - 2);
		collisionRect.setCenterX(loc.x);
		collisionRect.setCenterY(loc.y);
		rotation = 0;
		image.setRotation(0);
		bullets.clear();
		fireRecharge = 0;
	}
	public Shape getCollisionRect() {
		return collisionRect;
	}
	public void setColliding(boolean b) {
		// TODO Auto-generated method stub
		isColliding = b;
	}
	public void moveForeward(float distance) {
		float tfloat = rotation - 90;
		if (tfloat < 0) {
			tfloat+=360;
		}
		loc.x = (float) (loc.x + distance * Math.cos(Math.toRadians(tfloat)));
		loc.y = (float) (loc.y + distance * Math.sin(Math.toRadians(tfloat)));
	}
	public Vector2f getForewardPosition(float distance) {
		float tfloat = rotation - 90;
		if (tfloat < 0) {
			tfloat+=360;
		}
		return new Vector2f((float) (loc.x + distance * Math.cos(Math.toRadians(tfloat))), (float) (loc.y + distance * Math.sin(Math.toRadians(tfloat))));
	}
	public Vector2f getMovedPosition(float distance, float angle) {
		float tfloat = rotation - 90 + angle;
		if (tfloat < 0) {
			tfloat+=360;
		}
		if (tfloat > 360) {
			tfloat-=360;
		}
		return new Vector2f((float) (loc.x + distance * Math.cos(Math.toRadians(tfloat))), (float) (loc.y + distance * Math.sin(Math.toRadians(tfloat))));
	}
	public void setRotate(float r) {
		image.setRotation(r);
		if (r > 360) {r-=360;}
		rotation = r;
	}
	public void rotate(float r) {
		image.rotate(r);
		rotation = image.getRotation();
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public boolean isColliding() {
		return isColliding;
	}
	public void fire() throws SlickException {
		if (fireRecharge >= 1000) {
			bullets.add(new Bullet(loc, this.getForewardPosition(200), bulletImage, 100));
			fireRecharge = 0;
		}
	}
	public void dropBomb() {
		
	}
	public void setLoc(Vector2f vector2f) {
		// TODO Auto-generated method stub
		this.loc = vector2f;
	}
	
}

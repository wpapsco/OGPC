package com.papsco.GamePlayStateStuff;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	
	private Vector2f startPoint;
	private Vector2f endPoint;
	private Vector2f currentPoint;
	private Shape collisionRect;
	private Image image;
	private LimitedPath path;
	private boolean isMarkedForDeletion;
	private boolean isFinishedExploding = false;
	private float explosionRadius;
	private Shape explosionCircle;
	private int totalDelta = 0;
	
	public Bullet(Vector2f startPoint, Vector2f endPoint, Image image, float explosionRadius) {
		this.startPoint = startPoint;
		this.explosionRadius = explosionRadius;
		this.endPoint = endPoint;
		path = new LimitedPath(startPoint, endPoint);
		currentPoint = path.currentPoint;
		this.image = image;
		collisionRect = new Rectangle(currentPoint.x ,currentPoint.y, image.getWidth(), image.getHeight());
		collisionRect.setCenterX(currentPoint.x);
		collisionRect.setCenterY(currentPoint.y);
		this.explosionRadius = explosionRadius;
		explosionCircle = new Circle(endPoint.x, endPoint.y, explosionRadius);
	}
	
	public void update(int delta) {
		if (!isMarkedForDeletion) {
			path.update(delta);
			currentPoint = path.currentPoint;
			image.setRotation(path.angle);
		}
		collisionRect = new Rectangle(currentPoint.x ,currentPoint.y, image.getWidth(), image.getHeight());
		collisionRect.transform(Transform.createRotateTransform((float) Math.toRadians(path.angle))); 
		collisionRect.setCenterX(currentPoint.x);
		collisionRect.setCenterY(currentPoint.y);
		if (path.isDone()) {
			this.markDeleted();
		}
		if (isMarkedForDeletion && totalDelta < 100) {
			totalDelta+=delta;
		}
		if (totalDelta >= 100) {
			isFinishedExploding = true;
		}
	}
	
	public void markDeleted() {
		isMarkedForDeletion = true;
	}
	
	public boolean isMarkedForDeletion() {
		return isMarkedForDeletion;
	}
	
	public void setExplosionLocation(Vector2f location) {
		explosionCircle = new Circle(location.x, location.y, explosionRadius);
	}
	
	public LimitedPath getPath() {
		return path;
	}
	
	public Shape getCollisionRect() {
		return collisionRect;
	}
	
	public void draw(Graphics g) {
		if (!isMarkedForDeletion) {
			g.setColor(Color.green);
			image.drawCentered(currentPoint.x, currentPoint.y);
//			g.draw(collisionRect);
		}
		if (isMarkedForDeletion && !isFinishedExploding) {
			g.setColor(Color.red);
			g.draw(explosionCircle);
		}
	}

	public boolean isFinishedExploding() {
		// TODO Auto-generated method stub
		return isFinishedExploding;
	}

	public Shape getExplosionCircle() {
		// TODO Auto-generated method stub
		return explosionCircle;
	}
}

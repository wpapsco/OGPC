package com.papsco.GamePlayStateStuff;

import org.newdawn.slick.geom.Vector2f;

import com.papsco.OGPC2013.Functions;



public class LimitedPath {
	public Vector2f startPoint;
	public Vector2f endPoint;
	float slope;
	float angle;
	public Vector2f currentPoint;
	float distance;
	boolean canUpdate;
	public LimitedPath(float s_x, float s_y, float e_x, float e_y) {
		startPoint = new Vector2f(s_x, s_y);
		endPoint = new Vector2f(e_x, e_y);
		slope = (float) (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
		angle = (float) Math.atan(slope);
		currentPoint = new Vector2f(startPoint.getX(), startPoint.getY());
		distance = Functions.distance(startPoint, endPoint);
		canUpdate = true;
	}
	
	public LimitedPath(Vector2f startPoint, Vector2f endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		slope = (float) (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
		angle = (float) Math.atan(slope);
		currentPoint = new Vector2f(startPoint.getX(), startPoint.getY());
		distance = Functions.distance(startPoint, endPoint);
		canUpdate = true;
	}
	
	public boolean isDone() {
		return !canUpdate;
	}
	
	public void update(float delta) {
		slope = (float) (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
		angle = (float) Math.atan(slope);
		distance = Functions.distance(currentPoint, endPoint);
		if (canUpdate) {
			if (slope < 1 && slope > -1 && slope != Float.POSITIVE_INFINITY) {
				//quadrant 4
				if (startPoint.getX() - endPoint.getX() <= 0 && startPoint.getY() - endPoint.getY() <= 0) {
					currentPoint.y = currentPoint.getY() + slope*delta;
					currentPoint.x = currentPoint.getX() + delta;
				}
				//quadrant 2
				if (startPoint.getX() - endPoint.getX() > 0 && startPoint.getY() - endPoint.getY() > 0) {
					currentPoint.y = currentPoint.getY() - slope*delta;
					currentPoint.x = currentPoint.getX() - delta;
				}
				//quadrant 3
				if (startPoint.getX() - endPoint.getX() >= 0 && startPoint.getY() - endPoint.getY() <= 0) {
					currentPoint.y = currentPoint.getY() - slope*delta;
					currentPoint.x = currentPoint.getX() - delta;
				}
				//quadrant 1
				if (startPoint.getX() - endPoint.getX() < 0 && startPoint.getY() - endPoint.getY() > 0) {
					currentPoint.y = currentPoint.getY() + slope*delta;
					currentPoint.x = currentPoint.getX() + delta;
				}
			}
			
			if(slope >= 1 && slope != Float.POSITIVE_INFINITY || slope <= -1 && slope != Float.POSITIVE_INFINITY) {
				//quadrant 4
				if (startPoint.getX() - endPoint.getX() <= 0 && startPoint.getY() - endPoint.getY() <= 0) {
					currentPoint.y = currentPoint.getY() + delta;
					currentPoint.x = currentPoint.getX() + (1/slope)*delta;
				}
				//quadrant 2
				if (startPoint.getX() - endPoint.getX() > 0 && startPoint.getY() - endPoint.getY() > 0) {
					currentPoint.y = currentPoint.getY() - delta;
					currentPoint.x = currentPoint.getX() - (1/slope)*delta;
				}
				//quadrant 3
				if (startPoint.getX() - endPoint.getX() >= 0 && startPoint.getY() - endPoint.getY() <= 0) {
					currentPoint.y = currentPoint.getY() + delta;
					currentPoint.x = currentPoint.getX() + (1/slope)*delta;
				}
				//quadrant 1
				if (startPoint.getX() - endPoint.getX() < 0 && startPoint.getY() - endPoint.getY() > 0) {
					currentPoint.y = currentPoint.getY() - delta;
					currentPoint.x = currentPoint.getX() - (1/slope)*delta;
				}
			}
			if (slope == Float.POSITIVE_INFINITY) {
				currentPoint.y = currentPoint.getY() - 1;
			}
		}
		if (distance <= Functions.distance(currentPoint, endPoint) && canUpdate == true) {
			canUpdate = false;
		}
	}
}
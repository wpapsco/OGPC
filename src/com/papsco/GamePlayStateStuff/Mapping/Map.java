package com.papsco.GamePlayStateStuff.Mapping;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.GamePlayStateStuff.Bullet;
import com.papsco.GamePlayStateStuff.Enemy;
import com.papsco.GamePlayStateStuff.RunState;

public class Map {
	private Image image;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Event> events;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> currentEnemies;
	private Vector2f playerStartLoc;
	private String objectiveText;
	private boolean isCompleted;
	private boolean hasImage;
	
	public Map(Vector2f playerStartLoc) {
		obstacles = new ArrayList<Obstacle>();
		events = new ArrayList<Event>();
		enemies = new ArrayList<Enemy>();
		currentEnemies = new ArrayList<Enemy>();
		this.playerStartLoc = playerStartLoc;
		isCompleted = false;
		objectiveText = "";
		addObstacle(new Obstacle(new Line(new Vector2f(0, 0), new Vector2f(0, 600))));
		addObstacle(new Obstacle(new Line(new Vector2f(800, 600), new Vector2f(0, 600))));
		addObstacle(new Obstacle(new Line(new Vector2f(800, 600), new Vector2f(800, 0))));
		addObstacle(new Obstacle(new Line(new Vector2f(0, 0), new Vector2f(800, 0))));
		hasImage = false;
	}
	
	public Map(Vector2f playerStartLoc, Image image) {
		obstacles = new ArrayList<Obstacle>();
		events = new ArrayList<Event>();
		enemies = new ArrayList<Enemy>();
		currentEnemies = new ArrayList<Enemy>();
		this.playerStartLoc = playerStartLoc;
		isCompleted = false;
		objectiveText = "";
		this.image = image;
		hasImage = true;
	}
	
	public void setObjectiveText(String objectiveText) {
		this.objectiveText = objectiveText;
	}
	
	public void onDone() {
		objectiveText = objectiveText + " Done!~";
	}
	
	public boolean isColliding(Shape s) {
		boolean retVal = false;
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).collides(s)) {
				retVal = true;
			}
		}
		return retVal;
	}
	
	public Vector2f getPlayerStartLoc() {
		return playerStartLoc;
	}
	
	public void checkEnemyCollisions(ArrayList<Bullet> bullets) {
		for (int i = 0; i < currentEnemies.size(); i++) {
			for (int j = 0; j < bullets.size(); j++){
				if (currentEnemies.get(i).getCollisionRect().intersects(bullets.get(j).getCollisionRect())) {
					currentEnemies.get(i).takeDamage(100);
					bullets.get(i).setExplosionLocation(bullets.get(i).getPath().currentPoint);
					bullets.get(j).markDeleted();
				}
				if (currentEnemies.get(i).getCollisionRect().intersects(bullets.get(j).getExplosionCircle()) && bullets.get(i).isMarkedForDeletion() && !bullets.get(i).isFinishedExploding()) {
					currentEnemies.get(i).takeDamage(100);
				}
			}
		}
		for (int i = 0; i < currentEnemies.size(); i+=0) {
			boolean b = false;
			if (currentEnemies.get(i).isMarkedForDeletion()) {
				currentEnemies.remove(i) ;
				b = true;
			}
			if (!b) {
				i++;
			}
		}
	}
	
	public void update(GameContainer c, RunState s) {
		boolean doComplete = true;
		for (int i = 0; i < events.size(); i++) {
			events.get(i).update(c, s);
			if (!events.get(i).isExecuted()) {
				doComplete = false;
			}
		}
		if (doComplete) {
			Complete();
		}
		for (int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).update(c, s);
		}
		checkEnemyCollisions(s.getPlayer().getBullets());
	}
	
	public void addObstacle(Obstacle obstacle) {
		obstacles.add(obstacle);
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
		currentEnemies.add(enemy);
	}
	
	public ArrayList<Enemy> getEnemies() {
		return currentEnemies;
	}
	
	public ArrayList<Enemy> getBaseEnemies() {
		return enemies;
	}
	
	public void reset() {
		currentEnemies.clear();
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).isDead(false);
			currentEnemies.add(enemies.get(i));
		}
		for (int i = 0; i < events.size(); i++) {
			events.get(i).reset();
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fill(new Rectangle(0, 0, 800, 600));
		if (hasImage) {
			image.draw();
		}else {
			drawObstacles(g);
		}
		for (int i = 0; i < currentEnemies.size(); i++) {
			currentEnemies.get(i).draw(g);
		}
		for (int i = 0; i < events.size(); i++) {
			events.get(i).draw(g);
		}
		g.setColor(Color.green);
		g.drawString(objectiveText, 0, 0);
	}
	
	public void drawObstacles(Graphics g) {
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(g);
		}
	}

	public void Complete() {
		if (!isCompleted) {objectiveText = objectiveText + " - Done!";}
		isCompleted = true;
	}

	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return isCompleted;
	}
}

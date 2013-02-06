package com.papsco.FlowChartStateStuff;

import java.util.ArrayList;

import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.GamePlayStateStuff.RunState;
import com.papsco.OGPC2013.Functions;

import GuiItems.Button;

public abstract class Block {
	protected Vector2f loc;
	protected Image image;
	protected boolean isEnd = true;
	protected Button selectButton;
	protected int blockType;
	protected int deltaSum;
	protected boolean hasNextBlock;
	protected boolean hasPreviousBlock;
	protected ArrayList<Block> previousBlocks;
	protected ArrayList<Shape> connectingLines;
	protected ArrayList<Shape> arrowLines;
	public static final int COMMAND_BLOCK = 0;
	public static final int CONDITIONAL_BLOCK = 1;
	private boolean isBlackListed;
	//testing a git feature, don't mind this!
	
	public abstract Block incite();
	public abstract void init(GameContainer c, RunState s);
	
	public Block(Vector2f loc, String imageString) throws SlickException {
		this.loc = loc;
		image = new Image(imageString);
		selectButton = new Button(image, loc);
		deltaSum = 0;
		hasNextBlock = false;
		connectingLines = new ArrayList<Shape>();
		hasPreviousBlock = false;
		arrowLines = new ArrayList<Shape>();
		previousBlocks = new ArrayList<Block>();
	}
	
	public boolean hasNextBlock() {
		return hasNextBlock;
	}
	
	public void setImage(Image image) {
		this.image = image;
		selectButton.setImage(image);
	}
	
	public void setImage(String imageString) throws SlickException {
		this.image = new Image(imageString);
		selectButton.setImage(image);
	}
	
	public int getBlockType() {
		return blockType;
	}
	
	public void addPreviousBlock(Block b) {
		previousBlocks.add(b);
		hasPreviousBlock = true;
	}
	
	public boolean hasPreviousBlock() {
		return hasPreviousBlock;
	}
	
	public ArrayList<Block> getPreviousBlocks() {
		return previousBlocks;
	}
	
	public void addLine(Line line) {
		connectingLines.add(line);
		Vector2f startPoint = new Vector2f(line.getCenterX(), line.getCenterY());
		float angle = Functions.pointsToAngle(line.getX1(), line.getY1(), line.getX2(), line.getY2()) - 270;
		angle = angle - 45;
		if (angle < 0) {
			angle+=360;
		}
		float xval = (float) (.25 * Math.toDegrees(Math.cos(Math.toRadians(angle))) + startPoint.x);
		float yval = (float) (.25 * Math.toDegrees(Math.sin(Math.toRadians(angle))) + startPoint.y);
		arrowLines.add(new Line(startPoint, new Vector2f(xval, yval)));
		angle = angle + 90;
		if (angle > 360) {
			angle-=360;
		}
		xval = (float) (.25 * Math.toDegrees(Math.cos(Math.toRadians(angle))) + startPoint.x);
		yval = (float) (.25 * Math.toDegrees(Math.sin(Math.toRadians(angle))) + startPoint.y);
		arrowLines.add(new Line(startPoint, new Vector2f(xval, yval)));
	}
	
	public void removeLines() {
		connectingLines.clear();
		arrowLines.clear();
	}
	
	void drawLines(Graphics g) {
		for (int i = 0; i < connectingLines.size(); i++) {
			if (this.blockType == CONDITIONAL_BLOCK && i == 0) {
				g.setColor(Color.green);
				g.draw(connectingLines.get(i));
			} else if (this.blockType == CONDITIONAL_BLOCK && i == 1) {
				g.setColor(Color.red);
				g.draw(connectingLines.get(i));
			} else {
				g.setColor(Color.black);
				g.draw(connectingLines.get(i));
			}
		}
		for (int i = 0; i < arrowLines.size(); i++) {
			if (this.blockType == CONDITIONAL_BLOCK && i == 0 || this.blockType == CONDITIONAL_BLOCK && i == 1) {
				g.setColor(Color.green);
				g.draw(arrowLines.get(i));
			} else if (this.blockType == CONDITIONAL_BLOCK && i == 2 || this.blockType == CONDITIONAL_BLOCK && i == 3) {
				g.setColor(Color.red);
				g.draw(arrowLines.get(i));
			} else {
				g.setColor(Color.black);
				g.draw(arrowLines.get(i));
			}
		}
	}
	
	void draw(Graphics g) {
		selectButton.draw(g);
	}
	
	public void update(int delta, GameContainer c, RunState s) {
		deltaSum+=delta;
	}
}

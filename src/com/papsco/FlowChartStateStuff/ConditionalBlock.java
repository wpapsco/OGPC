package com.papsco.FlowChartStateStuff;

import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.GamePlayStateStuff.RunState;

public abstract class ConditionalBlock extends Block {

	Block trueBlock;
	Block falseBlock;
	boolean trueBlockSet = false;
	boolean falseBlockSet = false;
	protected int conditionalBlockType;
	public static final int BOOLEAN_BLOCK = 0;
	public static final int AT_WALL_BLOCK = 1;
	public static final int CHECK_FOREWARD_BLOCK = 2;
	public static final int CHECK_BACKWARD_BLOCK = 3;
	public static final int CHECK_LEFT_BLOCK = 4;
	public static final int CHECK_RIGHT_BLOCK = 5;
	public static final int ALL_ENEMIES_DEAD = 6;
	
	public ConditionalBlock(Vector2f loc, String imageString) throws SlickException {
		super(loc, imageString);
		blockType = Block.CONDITIONAL_BLOCK;
		// TODO Auto-generated constructor stub
	}
	
	public void setTrueBlock(Block block) {
		trueBlock = block;
		trueBlockSet = true;
		if (trueBlockSet && falseBlockSet) {
			hasNextBlock = true;
		}
		addLine(new Line(loc, block.loc));
	}
	
	public void setFalseBlock(Block block) {
		falseBlock = block;
		falseBlockSet = true;
		if (trueBlockSet && falseBlockSet) {
			hasNextBlock = true;
		}
		addLine(new Line(loc, block.loc));
	}
	
	public void update(int delta, GameContainer c, RunState s) {
		super.update(delta, c, s);
	}
	
	public int getConditionalBlockType() {
		return conditionalBlockType;
	}
	
	public abstract boolean condition();
	
	@Override
	public Block incite() {
		if (condition()) {
			return trueBlock;
		} else {
			return falseBlock;
		}
	}

}

package com.papsco.FlowChartStateStuff;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class CommandBlock extends Block {
	
	private Block nextBlock;
	public int commandBlockType;
	public static final int PRINTLN_BLOCK = 0;
	public static final int MOVE_BLOCK = 1;
	public static final int MOVE_UP_BLOCK = 2;
	public static final int MOVE_DOWN_BLOCK = 3;
	public static final int MOVE_LEFT_BLOCK = 4;
	public static final int MOVE_RIGHT_BLOCK = 5;
	public static final int START_BLOCK = 6;
	public static final int ROTATE_90_LEFT_BLOCK = 7;
	public static final int ROTATE_90_RIGHT_BLOCK = 8;
	public static final int MOVE_FOREWARD_BLOCK = 9;
	public static final int FIRE_BLOCK = 10;
	public static final int AIM_AT_NEAREST_ENEMY_BLOCK = 11;
	
	public abstract void command();
	
	public CommandBlock(Vector2f loc, String imageString) throws SlickException {
		super(loc, imageString);
		blockType = Block.COMMAND_BLOCK;
	}

	public Block incite() {
		command();
		if (hasNextBlock) {
			return nextBlock;
		}
		return null;
	}
	
	public int getCommandBlockType() {
		return commandBlockType;
	}
	
	public Block getNextBlock() {
		return nextBlock;
	}
	
	public void setNextBlock(Block block) {
		if (!hasNextBlock) {
			nextBlock = block;
			hasNextBlock = true;
			addLine(new Line(loc, block.loc));
		}
	}
}

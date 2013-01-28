package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.ConditionalBlock;
import com.papsco.GamePlayStateStuff.RunState;

public class BooleanBlock extends ConditionalBlock {

	boolean path;
	boolean curpath;
	public static String imageString = "pics/BooleanBlock.png";
	private GameContainer c;
	
	public BooleanBlock(Vector2f loc, boolean path)
			throws SlickException {
		super(loc, imageString);
		this.path = path;
		curpath = path;
		conditionalBlockType = ConditionalBlock.BOOLEAN_BLOCK;
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return curpath;
	}
	
	public void update(int delta, GameContainer c, RunState s) {
		super.update(delta, c, s);
		if (c.getInput().getMouseX() > 700) {
			curpath = !path;
		}else {
			curpath = path;
		}
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		
	}
}

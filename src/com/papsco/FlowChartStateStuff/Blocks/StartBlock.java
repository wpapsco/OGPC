package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.GamePlayStateStuff.RunState;

public class StartBlock extends CommandBlock {

	public static String imageString = "pics/StartBlock.png";
	
	public StartBlock(Vector2f loc) throws SlickException {
		super(loc, imageString);
		commandBlockType = CommandBlock.START_BLOCK;
	}

	@Override
	public void command() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		
	}
}

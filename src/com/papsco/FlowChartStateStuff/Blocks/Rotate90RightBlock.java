package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.GamePlayStateStuff.Player;
import com.papsco.GamePlayStateStuff.RunState;

public class Rotate90RightBlock extends CommandBlock {

	public static String imageString = "pics/RotateRightBlock.png";

	private Player p;
	
	public Rotate90RightBlock(Vector2f loc)
			throws SlickException {
		super(loc, imageString);
		// TODO Auto-generated constructor stub
		commandBlockType = CommandBlock.ROTATE_90_RIGHT_BLOCK;
	}

	@Override
	public void update(int delta, GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		super.update(delta, c, s);
		p = s.getPlayer();
	}
	
	@Override
	public void command() {
		p.rotate(90);
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		
	}

}

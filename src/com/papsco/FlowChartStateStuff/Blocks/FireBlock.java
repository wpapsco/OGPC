package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.Block;
import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.GamePlayStateStuff.Player;
import com.papsco.GamePlayStateStuff.RunState;

public class FireBlock extends CommandBlock {

	public static String imageString = "pics/FireBlock.png";
	
	private Player p;
	
	public FireBlock(Vector2f loc) throws SlickException {
		super(loc, imageString);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void update(int delta, GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		super.update(delta, c, s);
	}
	
	@Override
	public void command() {
		// TODO Auto-generated method stub
		try {
			p.fire();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		p = s.getPlayer();
	}
}

package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.GamePlayStateStuff.Player;
import com.papsco.GamePlayStateStuff.RunState;
import com.papsco.GamePlayStateStuff.Mapping.Map;

public class MoveForewardBlock extends CommandBlock {

	Player p;
	Map m;
	public static String imageString = "pics/MoveForeward.png";
	
	public MoveForewardBlock(Vector2f loc)
			throws SlickException {
		super(loc, imageString);
		commandBlockType = CommandBlock.MOVE_FOREWARD_BLOCK;
	}

	@Override
	public void update(int delta, GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		super.update(delta, c, s);
		p = s.getPlayer();
		m = s.getMap();
	}
	
	@Override
	public void command() {
		// TODO Auto-generated method stub
		Shape r = new Rectangle(0, 0, p.getImage().getWidth(), p.getImage().getHeight());
		r.setCenterX(p.getForewardPosition(2).x);
		r.setCenterY(p.getForewardPosition(2).y);
		if (!m.isColliding(r)) {
			p.moveForeward(2);
			System.out.println("Moved");
		}
	}

	@Override
	public void init(GameContainer c, RunState s) {
		p = s.getPlayer();
		m = s.getMap();
		
	}

}

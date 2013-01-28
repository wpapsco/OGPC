package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.Block;
import com.papsco.FlowChartStateStuff.ConditionalBlock;
import com.papsco.GamePlayStateStuff.RunState;

public class AtWallBlock extends ConditionalBlock {

	public static String imageString = "pics/AtWallBlock.png";
	public boolean b;
	
	public AtWallBlock(Vector2f loc, RunState s) throws SlickException {
		super(loc, imageString);
		// TODO Auto-generated constructor stub
		b = s.getPlayer().isColliding();
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return b;
	}
	
	@Override
	public void update(int delta, GameContainer c, RunState s) {
		super.update(delta, c, s);
		Shape r = s.getPlayer().getCollisionRect();
		Shape r2 = r;
		boolean b2 = true;
		r2.setCenterX(r2.getCenterX() + 2); //moving right two blocks
		if (s.getMap().isColliding(r2)) {
			s.getPlayer().setColliding(true);
			b2 = false;
		}
		r2.setCenterX(r2.getCenterX() - 4); //resetting and moving left two blocks
		if (s.getMap().isColliding(r2)) {
			s.getPlayer().setColliding(true);
			b2 = false;
		}
		r2.setCenterX(r2.getCenterX() + 2); //resetting x
		r2.setCenterY(r2.getCenterY() + 2); //moving down two blocks
		if (s.getMap().isColliding(r2)) {
			s.getPlayer().setColliding(true);
			b2 = false;
		}
		r2.setCenterY(r2.getCenterY() - 4); //resetting and moving up two blocks
		if (s.getMap().isColliding(r2)) {
			s.getPlayer().setColliding(true);
			b2 = false;
		}
		r2.setCenterY(r2.getCenterY() + 2); //resetting y
		if (b2) {
			s.getPlayer().setColliding(false);
		}
		b = s.getPlayer().isColliding();
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		
	}
}

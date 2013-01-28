package com.papsco.FlowChartStateStuff.Blocks;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.GamePlayStateStuff.Enemy;
import com.papsco.GamePlayStateStuff.Player;
import com.papsco.GamePlayStateStuff.RunState;
import com.papsco.OGPC2013.Functions;

public class AimAtNearestEnemyBlock extends CommandBlock {

	public static String imageString = "pics/AimAtEnemyBlock.png";
	private Player p;
	private ArrayList<Enemy> e;
	
	public AimAtNearestEnemyBlock(Vector2f loc)
			throws SlickException {
		super(loc, imageString);
		e = new ArrayList<Enemy>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void command() {
		// TODO Auto-generated method stub
		Vector2f v = p.getForewardPosition(2);
		float smallestDistance = -1;
		
		for (int i = 0; i < e.size(); i++) {
			float d = Functions.distance(p.getLoc(), e.get(i).getLoc());
			if (smallestDistance <= 0) {smallestDistance = d;}
			if (d < smallestDistance) {
				v = e.get(i).getLoc();
				smallestDistance = d;
			}
		}
		
		p.setRotate(Functions.pointsToAngle(loc.x, loc.y, v.x, v.y) + 180);
	}

	@Override
	public void init(GameContainer c, RunState s) {
		p = s.getPlayer();
		e = s.getMap().getEnemies();
	}

}

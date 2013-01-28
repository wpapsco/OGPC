package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.ConditionalBlock;
import com.papsco.GamePlayStateStuff.RunState;
import com.papsco.GamePlayStateStuff.Mapping.Map;

public class AllEnemiesDeadBlock extends ConditionalBlock {

	Map m;
	
	public AllEnemiesDeadBlock(Vector2f loc)
			throws SlickException {
		super(loc, "pics/DeadEnemies.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		boolean b = true;
		for (int i = 0; i < m.getEnemies().size(); i++) {
			if (!m.getEnemies().get(i).isDead()) {
				b = false;
			}
		}
		return b;
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		m = s.getMap();
	}

}

package com.papsco.GamePlayStateStuff.Mapping;

import org.newdawn.slick.GameContainer;

import com.papsco.GamePlayStateStuff.RunState;

public class EnemiesKilledEvent extends Event {

	public EnemiesKilledEvent(boolean constant) {
		// TODO Auto-generated constructor stub
		super(constant);
	}

	@Override
	public boolean condition(GameContainer c, RunState s) {
		if (s.getMap().getEnemies().size() <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public void effect(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		s.getMap().Complete();
	}

}

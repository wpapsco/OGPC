package com.papsco.GamePlayStateStuff.Mapping;

import org.newdawn.slick.GameContainer;

import com.papsco.GamePlayStateStuff.RunState;

public abstract class Event {
	
	private boolean constant, executed;
	
	public Event(boolean constant) {
		this.constant = constant;
		executed = false;
	}
	
	public void update(GameContainer c, RunState s) {
		if (condition(c, s)) {
			if (constant) {
				effect(c, s);
			}
			if (!constant && !executed) {
				effect(c, s);
				executed = true;
			}
		}
	}
	
	public void reset() {
		executed = false;
	}
	
	public abstract boolean condition(GameContainer c, RunState s);
	
	public abstract void effect(GameContainer c, RunState s);
}

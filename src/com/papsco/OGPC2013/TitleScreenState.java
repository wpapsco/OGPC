package com.papsco.OGPC2013;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import GuiItems.Button;

public class TitleScreenState extends BasicGameState {

	private int stateID;
	
	public TitleScreenState(int StateID) {
		// TODO Auto-generated constructor stub
		this.stateID = StateID;
	}
	
	private Image titleScreen;
	private Button startButton;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		titleScreen = new Image("pics/TITLE.png");
		//324, 548
		startButton = new Button(new Image("pics/Start.png"), new Vector2f(400, 425));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		titleScreen.draw();
		startButton.draw(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input in = container.getInput();
		if (startButton.isClicked(in)) {
			game.enterState(OGPC.FLOWCHARTSTATE);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}

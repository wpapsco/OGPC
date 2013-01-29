package com.papsco.OGPC2013;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import GuiItems.LevelSelectState;

import com.papsco.FlowChartStateStuff.FlowChartState;
import com.papsco.FlowChartStateStuff.Block;
import com.papsco.GamePlayStateStuff.RunState;


public class OGPC extends StateBasedGame {

	public static final int FLOWCHARTSTATE = 0;
	public static final int RUNSTATE = 1;
	public static final int TITLESTATE = 2;
	public static final int LEVELSELECTSTATE = 3;
	public ArrayList<Block> blockInfo;
	public static boolean changedLevel = false;
	public static boolean IsMuted = true;
	
	public OGPC(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new TitleScreenState(TITLESTATE));
		this.addState(new FlowChartState(FLOWCHARTSTATE));
		this.addState(new RunState(RUNSTATE));
		this.addState(new LevelSelectState(LEVELSELECTSTATE));
		this.enterState(TITLESTATE);
	}
	
	public ArrayList<Block> getCurrentBlockInfo() {
		return blockInfo;
	}
	
	public void setCurrentBlockInfo(ArrayList<Block> b) {
		blockInfo = b;
	}

	/**
	 * @param args
	 * @throws SlickException 
	 */
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer app = new AppGameContainer(new OGPC("GAEM"));
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.start();
	}

}

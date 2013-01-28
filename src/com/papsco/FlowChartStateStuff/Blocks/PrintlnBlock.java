package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.GamePlayStateStuff.RunState;

public class PrintlnBlock extends CommandBlock {

	String print;
	public static String imageString = "./pics/PrintlnBlock.png";
	
	public PrintlnBlock(String print, Vector2f loc) throws SlickException {
		super(loc, imageString);
		this.print = print;
		this.commandBlockType = CommandBlock.PRINTLN_BLOCK;
	}

	@Override
	public void command() {
		System.out.println(print);
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		
	}
}

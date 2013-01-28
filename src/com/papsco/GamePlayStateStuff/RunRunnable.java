package com.papsco.GamePlayStateStuff;

import java.util.ArrayList;

import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.GameContainer;

import com.papsco.FlowChartStateStuff.Block;
import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.FlowChartStateStuff.ConditionalBlock;

public class RunRunnable implements Runnable {
	
	private Block currentBlock;
	private Block startBlock;
	ArrayList<Block> blocks;
	GameContainer c;
	RunState s;
	boolean running = true;
	
	public RunRunnable(ArrayList<Block> blocks, GameContainer c, RunState s) {
		this.blocks = blocks;
		currentBlock = blocks.get(0);
		startBlock = blocks.get(0);
		this.c = c;
		this.s = s;
	}
	
	public Block getCurrentBlock() {
		return currentBlock;
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000); //initial delay
			while (currentBlock.hasNextBlock() && running) {
				boolean runCurBlock = true;
				if (runCurBlock) {
					currentBlock.init(c, s);
					currentBlock.update(0, c, s);
					currentBlock = currentBlock.incite();
				} else {
					currentBlock = ((CommandBlock) currentBlock).getNextBlock();
				}
				Thread.sleep(5);
			}
			if (!currentBlock.hasNextBlock()) {
				currentBlock.init(c, s);
				currentBlock.incite();
			}
			if (!running) {
				currentBlock = startBlock;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

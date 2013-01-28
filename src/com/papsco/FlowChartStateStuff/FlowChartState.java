package com.papsco.FlowChartStateStuff;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.papsco.FlowChartStateStuff.Blocks.AimAtNearestEnemyBlock;
import com.papsco.FlowChartStateStuff.Blocks.AllEnemiesDeadBlock;
import com.papsco.FlowChartStateStuff.Blocks.AtWallBlock;
import com.papsco.FlowChartStateStuff.Blocks.BooleanBlock;
import com.papsco.FlowChartStateStuff.Blocks.CheckCollisionDirectionally;
import com.papsco.FlowChartStateStuff.Blocks.FireBlock;
import com.papsco.FlowChartStateStuff.Blocks.MoveBlock;
import com.papsco.FlowChartStateStuff.Blocks.MoveForewardBlock;
import com.papsco.FlowChartStateStuff.Blocks.PrintlnBlock;
import com.papsco.FlowChartStateStuff.Blocks.Rotate90LeftBlock;
import com.papsco.FlowChartStateStuff.Blocks.Rotate90RightBlock;
import com.papsco.FlowChartStateStuff.Blocks.StartBlock;
import com.papsco.GamePlayStateStuff.RunState;
import com.papsco.OGPC2013.OGPC;

import GuiItems.Button;
import GuiItems.ButtonPanel;

/**
 * @author william
 */
public class FlowChartState extends BasicGameState {

	int stateID;
	
	private ArrayList<Block> blocks;
	private Image mouseImage;
	private boolean settingBlock = false;
	private Sound menuMusic;
	
//	private Button printlnButton;
//	private Button booleanButton;
	private Button fireButton;
	private Button atWallButton;
	
	private Button moveUpButton;
	private Button moveDownButton;
	private Button moveLeftButton;
	private Button moveRightButton;
	
	private Button checkForewardButton;
	private Button checkBackButton;
	
	private Button checkEnemiesDeadButton;
	
	private Button moveForewardButton;
	
	private Button rotate90LeftButton;
	private Button rotate90RightButton;
	
	private Button openMovePanelButton;
	private Button openRotatePanelButton;
	private Button openCollisionPanelButton;
	
	private Button deleteAllButton;
	private Button muteButton;
	
	private Button massiveBackgroundButton;
	
	private ButtonPanel MoveBlocksPanel;
	private ButtonPanel RotateBlocksPanel;
	private ButtonPanel CollisionBlocksPanel;
	
	private int selectedBlockType;
	private int selectedBlockSubType;
	private Button runButton;
	private int selectedBlockId;
	
	public FlowChartState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		blocks = new ArrayList<Block>();
		menuMusic = new Sound("sounds/menu.ogg");
//		printlnButton =            new Button(new Image(PrintlnBlock.imageString), new Vector2f(50, 25));
//		booleanButton =            new Button(new Image(BooleanBlock.imageString), new Vector2f(50, 75));
		fireButton =               new Button(new Image(FireBlock.imageString), new Vector2f(50, 75));
		atWallButton =             new Button(new Image(AtWallBlock.imageString), new Vector2f(50, 25));
		
		moveUpButton =             new Button(new Image("pics/UpBlock.png"), new Vector2f(50, 575));
		moveDownButton =           new Button(new Image("pics/DownBlock.png"), new Vector2f(50, 525));
		moveLeftButton =           new Button(new Image("pics/LeftBlock.png"), new Vector2f(50, 475));
		moveRightButton =          new Button(new Image("pics/RightBlock.png"), new Vector2f(50, 425));
		
		checkForewardButton =      new Button(new Image("pics/FrontTouchingWall.png"), new Vector2f(0, 0));
		checkBackButton =          new Button(new Image("pics/BackTouchingWall.png"), new Vector2f(0, 0));
		
		checkEnemiesDeadButton =   new Button(new Image("pics/DeadEnemies.png"), new Vector2f(50, 125));
		
		moveForewardButton =       new Button(new Image(MoveForewardBlock.imageString), new Vector2f(50, 475));
		
		rotate90LeftButton =       new Button(new Image("pics/RotateLeftBlock.png"), new Vector2f(0, 0));
		rotate90RightButton =      new Button(new Image("pics/RotateRightBlock.png"), new Vector2f(0, 0));
		
		openMovePanelButton =      new Button(new Image("pics/MoveBlocks.png"), new Vector2f(50, 575));
		openRotatePanelButton =    new Button(new Image("pics/Rotate.png"), new Vector2f(50, 525));
		openCollisionPanelButton = new Button(new Image("pics/checkCollision.png"), new Vector2f(50, 425));
		
		deleteAllButton =          new Button(new Image("pics/DeleteAll.png"), new Vector2f(750, 25));
		muteButton =               new Button(new Image("pics/Mute.png"), new Vector2f(750, 75));
		
		massiveBackgroundButton =  new Button(new Image("pics/testMap.png"),       new Vector2f(400, 300)); //button is not displayed because it is only used for detecting clicks on the screen in general. It uses a background image for the size
		runButton =                new Button(new Image("pics/runButton.png"),     new Vector2f(750, 575));
		
		MoveBlocksPanel = new ButtonPanel(2, 2, 100, 50, new Vector2f(115, 485));
		MoveBlocksPanel.addButton(moveUpButton);
		MoveBlocksPanel.addButton(moveDownButton);
		MoveBlocksPanel.addButton(moveLeftButton);
		MoveBlocksPanel.addButton(moveRightButton);
		
		RotateBlocksPanel = new ButtonPanel(2, 1, 100, 50, new Vector2f(115, 500));
		RotateBlocksPanel.addButton(rotate90LeftButton);
		RotateBlocksPanel.addButton(rotate90RightButton);
		
		CollisionBlocksPanel = new ButtonPanel(3, 2, 100, 50, new Vector2f(115, 375));
		CollisionBlocksPanel.addButton(checkBackButton);
		CollisionBlocksPanel.addButton(checkForewardButton);
//		CollisionBlocksPanel.addButton
//		CollisionBlocksPanel.addButton
//		CollisionBlocksPanel.addButton
		
		blocks.add(new StartBlock(new Vector2f(175, 50)));
		selectedBlockId = -1;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.lightGray);
		g.fill(new Rectangle(0, 0, 800, 600));
		
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).drawLines(g);
		}
		
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).draw(g);
		}
		
		moveForewardButton.draw(g);
		
//		printlnButton.draw(g);
//		booleanButton.draw(g);
		fireButton.draw(g);
		atWallButton.draw(g);
		
		checkEnemiesDeadButton.draw(g);
		
		MoveBlocksPanel.draw(g);
		RotateBlocksPanel.draw(g);
		CollisionBlocksPanel.draw(g);
		
		openMovePanelButton.draw(g);
		openRotatePanelButton.draw(g);
		openCollisionPanelButton.draw(g);
		
		deleteAllButton.draw(g);
		muteButton.draw(g);
		runButton.draw(g);
		
		g.draw(new Line(new Vector2f(100, 0), new Vector2f(100, 600)));
		
		if (settingBlock) {
			mouseImage.drawCentered(container.getInput().getMouseX(), container.getInput().getMouseY());
		}
		if (selectedBlockId >= 0) {
			g.setColor(Color.green);
			g.draw(blocks.get(selectedBlockId).selectButton.getRect());
		}
	}

	public void removeBlock(int id) {
		boolean doDelete = true;
		if (blocks.get(id).getBlockType() == Block.COMMAND_BLOCK) {
			if (((CommandBlock) blocks.get(id)).getCommandBlockType() == CommandBlock.START_BLOCK) {
				doDelete = false;
				blocks.get(id).removeLines();
			}
		}
		if (doDelete) {
			if (blocks.get(id).hasPreviousBlock()) {
				for (int i = 0; i < blocks.get(id).getPreviousBlocks().size(); i++) {
					blocks.get(id).getPreviousBlocks().get(i).hasNextBlock = false;
					blocks.get(id).getPreviousBlocks().get(i).removeLines();
				}
			}
			if (blocks.get(id).hasNextBlock) {
				if (blocks.get(id).getBlockType() == Block.COMMAND_BLOCK) {
					((CommandBlock)blocks.get(id)).getNextBlock().hasPreviousBlock = false;
				}
				if (blocks.get(id).getBlockType() == Block.CONDITIONAL_BLOCK) {
					if (((ConditionalBlock)blocks.get(id)).trueBlockSet) {
						((ConditionalBlock)blocks.get(id)).trueBlock.hasPreviousBlock = false;
					}
					if (((ConditionalBlock)blocks.get(id)).falseBlockSet) {
						((ConditionalBlock)blocks.get(id)).falseBlock.hasPreviousBlock = false;
					}
				}
			}
			if (blocks.get(id).hasPreviousBlock) {
				for (int i = 0; i < blocks.get(id).getPreviousBlocks().size(); i++) {
					if (blocks.get(id).getPreviousBlocks().get(i).getBlockType() == Block.COMMAND_BLOCK) {
						((CommandBlock) blocks.get(id).getPreviousBlocks().get(i)).hasNextBlock = false;
					}
					if (blocks.get(id).getPreviousBlocks().get(i).getBlockType() == Block.CONDITIONAL_BLOCK) {
						if (((ConditionalBlock) blocks.get(id).getPreviousBlocks().get(i)).falseBlockSet && ((ConditionalBlock) blocks.get(id).getPreviousBlocks().get(i)).falseBlock == blocks.get(id)) {
							((ConditionalBlock) blocks.get(id).getPreviousBlocks().get(i)).falseBlockSet = false;
						}
						if (((ConditionalBlock) blocks.get(id).getPreviousBlocks().get(i)).trueBlockSet && ((ConditionalBlock) blocks.get(id).getPreviousBlocks().get(i)).trueBlock == blocks.get(id)) {
							((ConditionalBlock) blocks.get(id).getPreviousBlocks().get(i)).trueBlockSet = false;
						}
					}
				}
			}
			blocks.get(id).removeLines();
			blocks.remove(id);
			selectedBlockId = -1;
		}
	}
	
	public void toggleExpandPanel(ButtonPanel panel) {
		boolean b = panel.isExpanded();
		MoveBlocksPanel.contract();
		RotateBlocksPanel.contract();
		CollisionBlocksPanel.contract();
		if (!b) {panel.expand();}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		((RunState) game.getState(OGPC.RUNSTATE)).getPlayer();
		Input in = container.getInput();
		
		if (settingBlock) {
			MoveBlocksPanel.contract();
			RotateBlocksPanel.contract();
			CollisionBlocksPanel.contract();
		}
		
		if(OGPC.IsMuted) {
			menuMusic.stop();
		}
		
		if(!OGPC.IsMuted && !menuMusic.playing()) {
			menuMusic.play();
		}
		
		if (massiveBackgroundButton.isClicked(in)) {
			int x = in.getMouseX();
			int y = in.getMouseY();
			if (settingBlock) {
				try {
					if (selectedBlockType == Block.COMMAND_BLOCK) {
//						if (selectedBlockSubType == CommandBlock.PRINTLN_BLOCK) {
//							blocks.add(new PrintlnBlock(Float.toString((float) Math.random()), new Vector2f(x, y)));
//						}
						if (selectedBlockSubType == CommandBlock.MOVE_UP_BLOCK) {
							blocks.add(new MoveBlock(new Vector2f(x, y), container, (RunState) game.getState(OGPC.RUNSTATE), 0, -2, CommandBlock.MOVE_UP_BLOCK));
						}
						if (selectedBlockSubType == CommandBlock.MOVE_DOWN_BLOCK) {
							blocks.add(new MoveBlock(new Vector2f(x, y), container, (RunState) game.getState(OGPC.RUNSTATE), 0, 2, CommandBlock.MOVE_DOWN_BLOCK));
						}
						if (selectedBlockSubType == CommandBlock.MOVE_LEFT_BLOCK) {
							blocks.add(new MoveBlock(new Vector2f(x, y), container, (RunState) game.getState(OGPC.RUNSTATE), -2, 0, CommandBlock.MOVE_LEFT_BLOCK));
						}
						if (selectedBlockSubType == CommandBlock.MOVE_RIGHT_BLOCK) {
							blocks.add(new MoveBlock(new Vector2f(x, y), container, (RunState) game.getState(OGPC.RUNSTATE), 2, 0, CommandBlock.MOVE_RIGHT_BLOCK));
						}
						if (selectedBlockSubType == CommandBlock.ROTATE_90_LEFT_BLOCK) {
							blocks.add(new Rotate90LeftBlock(new Vector2f(x, y)));
						}
						if (selectedBlockSubType == CommandBlock.ROTATE_90_RIGHT_BLOCK) {
							blocks.add(new Rotate90RightBlock(new Vector2f(x, y)));
						}
						if (selectedBlockSubType == CommandBlock.MOVE_FOREWARD_BLOCK) {
							blocks.add(new MoveForewardBlock(new Vector2f(x, y)));
						}
						if (selectedBlockSubType == CommandBlock.FIRE_BLOCK) {
							blocks.add(new FireBlock(new Vector2f(x, y)));
						}
						if (selectedBlockSubType == CommandBlock.AIM_AT_NEAREST_ENEMY_BLOCK) {
							blocks.add(new AimAtNearestEnemyBlock(new Vector2f(x, y)));
						}
					}
					if (selectedBlockType == Block.CONDITIONAL_BLOCK){
//						if (selectedBlockSubType == ConditionalBlock.BOOLEAN_BLOCK) {
//							blocks.add(new BooleanBlock(new Vector2f(x, y), true));
//						}
						if (selectedBlockSubType == ConditionalBlock.AT_WALL_BLOCK) {
							blocks.add(new AtWallBlock(new Vector2f(x, y), (RunState) game.getState(OGPC.RUNSTATE)));
						}
						if (selectedBlockSubType == ConditionalBlock.CHECK_FOREWARD_BLOCK) {
							blocks.add(new CheckCollisionDirectionally(new Vector2f(x, y), ConditionalBlock.CHECK_FOREWARD_BLOCK));
						}
						if (selectedBlockSubType == ConditionalBlock.CHECK_BACKWARD_BLOCK) {
							blocks.add(new CheckCollisionDirectionally(new Vector2f(x, y), ConditionalBlock.CHECK_BACKWARD_BLOCK));
						}
						if (selectedBlockSubType == ConditionalBlock.ALL_ENEMIES_DEAD) {
							blocks.add(new AllEnemiesDeadBlock(new Vector2f(x, y)));
						}
					}
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				settingBlock = false;
			}
			boolean bl = true;
			for (int i = 0; i < blocks.size(); i++) {
				if (blocks.get(i).selectButton.isClicked(x, y)){
					bl = false;
				}
			}
			if (bl) {
				selectedBlockId = -1;
			}
		}
		
//		if (printlnButton.isClicked(in)) {
//			mouseImage = new Image(PrintlnBlock.imageString);
//			selectedBlockType = Block.COMMAND_BLOCK;
//			selectedBlockSubType = CommandBlock.PRINTLN_BLOCK;
//			mouseImage.setAlpha(.75f);
//			settingBlock = true;
//		}
//		
//		if (booleanButton.isClicked(in)) {
//			mouseImage = new Image(BooleanBlock.imageString);
//			selectedBlockType = Block.CONDITIONAL_BLOCK;
//			selectedBlockSubType = ConditionalBlock.BOOLEAN_BLOCK;
//			mouseImage.setAlpha(.75f);
//			settingBlock = true;
//		}
		
		if (atWallButton.isClicked(in)) {
			mouseImage = new Image(AtWallBlock.imageString);
			selectedBlockType = Block.CONDITIONAL_BLOCK;
			selectedBlockSubType = ConditionalBlock.AT_WALL_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (fireButton.isClicked(in)) {
			mouseImage = new Image(FireBlock.imageString);
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.FIRE_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (checkEnemiesDeadButton.isClicked(in)) {
			mouseImage = new Image("pics/DeadEnemies.png");
			selectedBlockType = Block.CONDITIONAL_BLOCK;
			selectedBlockSubType = ConditionalBlock.ALL_ENEMIES_DEAD;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (MoveBlocksPanel.isClicked(0, in)) {
			mouseImage = new Image("pics/UpBlock.png");
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.MOVE_UP_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (MoveBlocksPanel.isClicked(1, in)) {
			mouseImage = new Image("pics/DownBlock.png");
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.MOVE_DOWN_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (MoveBlocksPanel.isClicked(2, in)) {
			mouseImage = new Image("pics/LeftBlock.png");
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.MOVE_LEFT_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (MoveBlocksPanel.isClicked(3, in)) {
			mouseImage = new Image("pics/RightBlock.png");
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.MOVE_RIGHT_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (moveForewardButton.isClicked(in)) {
			mouseImage = new Image(MoveForewardBlock.imageString);
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.MOVE_FOREWARD_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (RotateBlocksPanel.isClicked(0, in)) {
			mouseImage = new Image(Rotate90LeftBlock.imageString);
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.ROTATE_90_LEFT_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (RotateBlocksPanel.isClicked(1, in)) {
			mouseImage = new Image(Rotate90RightBlock.imageString);
			selectedBlockType = Block.COMMAND_BLOCK;
			selectedBlockSubType = CommandBlock.ROTATE_90_RIGHT_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (CollisionBlocksPanel.isClicked(0, in)) {
			mouseImage = new Image("pics/BackTouchingWall.png");
			selectedBlockType = Block.CONDITIONAL_BLOCK;
			selectedBlockSubType = ConditionalBlock.CHECK_BACKWARD_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (CollisionBlocksPanel.isClicked(1, in)) {
			mouseImage = new Image("pics/FrontTouchingWall.png");
			selectedBlockType = Block.CONDITIONAL_BLOCK;
			selectedBlockSubType = ConditionalBlock.CHECK_FOREWARD_BLOCK;
			mouseImage.setAlpha(.75f);
			settingBlock = true;
		}
		
		if (openMovePanelButton.isClicked(in)) {
			toggleExpandPanel(MoveBlocksPanel);
		}
		
		if (openRotatePanelButton.isClicked(in)) {
			toggleExpandPanel(RotateBlocksPanel);
		}
		if (openCollisionPanelButton.isClicked(in)) {
			toggleExpandPanel(CollisionBlocksPanel);
		}
		
		if (deleteAllButton.isClicked(in)) {
			for (int i = blocks.size() - 1; i >= 0; i--) {
				removeBlock(i);
			}
		}
		
		if (muteButton.isClicked(in)) {
			OGPC.IsMuted = !OGPC.IsMuted;
		}
		
		if (selectedBlockId >= 0) {
			for (int i = 0; i < blocks.size(); i++) {
				if (blocks.get(i).selectButton.isClicked(in)){
					if (blocks.get(selectedBlockId).getBlockType() == Block.COMMAND_BLOCK) { //how to treat a command block
						((CommandBlock) blocks.get(selectedBlockId)).setNextBlock(blocks.get(i)); //setting the next block
						blocks.get(i).addPreviousBlock(blocks.get(selectedBlockId));
						selectedBlockId = i; //changing the current selection
						break;
					}
					if (blocks.get(selectedBlockId).getBlockType() == Block.CONDITIONAL_BLOCK) {
						boolean skip = false;
						if (!((ConditionalBlock) blocks.get(selectedBlockId)).trueBlockSet) {
							((ConditionalBlock) blocks.get(selectedBlockId)).setTrueBlock(blocks.get(i));
							skip = true;
						}
						if (!((ConditionalBlock) blocks.get(selectedBlockId)).falseBlockSet && !skip) {
							((ConditionalBlock) blocks.get(selectedBlockId)).setFalseBlock(blocks.get(i));
						}
						blocks.get(i).addPreviousBlock(blocks.get(selectedBlockId));
						selectedBlockId = i; //changing the current selection
						break;
					}
					
				}
			}
		} else {
			for (int i = 0; i < blocks.size(); i++) {
				if (blocks.get(i).selectButton.isClicked(in)){
					selectedBlockId = i;
				}
			}
		}
		if (runButton.isClicked(in) && blocks.size() > 0) {
			((OGPC) game).setCurrentBlockInfo(blocks);
			game.enterState(OGPC.RUNSTATE);
		}
		
		if (in.isKeyPressed(Input.KEY_DELETE)) {
			if (selectedBlockId >= 0) {
				removeBlock(selectedBlockId);
			}
		}
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		super.enter(container, game);
		menuMusic.loop();
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		super.leave(container, game);
		menuMusic.stop();
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}
}

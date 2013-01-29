package com.papsco.GamePlayStateStuff;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import GuiItems.Button;

import com.papsco.OGPC2013.OGPC;

import com.papsco.FlowChartStateStuff.Block;
import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.FlowChartStateStuff.ConditionalBlock;
import com.papsco.GamePlayStateStuff.Mapping.EnemiesKilledEvent;
import com.papsco.GamePlayStateStuff.Mapping.LocationalEvent;
import com.papsco.GamePlayStateStuff.Mapping.Map;
import com.papsco.GamePlayStateStuff.Mapping.Obstacle;

public class RunState extends BasicGameState {

	private int stateID;

	public RunState(int a) {
		stateID = a;
	}

	private ArrayList<Block> blocks;
	private Thread t;
	private RunRunnable runnable;
	private Image background;
	private Player player;
	private Image playerImage;
	private Map map;
	private Sound gameMusic;
	private ArrayList<Map> maps;
	private int levelNum;
	private Button returnButton;
	private boolean onToNextLevel = false;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		levelNum = 0;
		// TODO Auto-generated method stub
		playerImage = new Image("pics/player.png");
		background = new Image("pics/testMap.png");
		gameMusic = new Sound("sounds/gameplay.ogg");
		returnButton = new Button(new Image("pics/returnButton.png"), new Vector2f(50, 575));
		maps = new ArrayList<Map>();
		map = new Map(new Vector2f(375, 575));
		
		map.addObstacle(new Obstacle(new Rectangle(0, 400, 350, 200)));
		map.addObstacle(new Obstacle(new Rectangle(0, 300, 700, 100)));
		map.addObstacle(new Obstacle(new Rectangle(100, 100, 700, 100)));
		
		map.addEvent(new LocationalEvent(new Vector2f(775, 25), false));
		map.addEvent(new EnemiesKilledEvent(false));
		
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(650, 550)));
		
		map.setObjectiveText("Level 1: Kill the enemy or get to the end to win!");
		maps.add(map);
		
		map = new Map(new Vector2f(25, 25));
		map.addObstacle(new Obstacle(new Rectangle(400, 300, 100, 100)));
		map.setObjectiveText("Level 2: Levels totally work now! (You can't win this one!)");
		maps.add(map);
		
		player = new Player(new Vector2f(maps.get(levelNum).getPlayerStartLoc().x, maps.get(levelNum).getPlayerStartLoc().y), playerImage);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		//background.draw();
		player.draw(g);
		g.setColor(Color.blue);
		maps.get(levelNum).draw(g);
		g.setColor(Color.blue);
		maps.get(levelNum).drawObstacles(g);
		returnButton.draw(g);
		g.draw(new Rectangle(775, 25, 2, 2));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

		Input in = container.getInput();
		maps.get(levelNum).update(container, this);
		maps.get(levelNum).checkEnemyCollisions(player.getBullets());
		
		if (returnButton.isClicked(in)) {
			onRunComplete();
			game.enterState(OGPC.FLOWCHARTSTATE);
		}
		
		if(OGPC.IsMuted) {
			gameMusic.stop();
		}
		
		if(!OGPC.IsMuted && !gameMusic.playing()) {
			gameMusic.play();
		}
		
		player.update(delta, maps.get(levelNum));
		
		CheckForAtWall();
		
		runnable.getCurrentBlock().update(delta, container, this);
	}

	private void CheckForAtWall() {
		
	}

	public Map getMap() {
		return maps.get(levelNum);
	}
	
	public void onRunComplete() {
		System.out.println("Complete");
		reset();
	}

	public void nextLevel() {
		onToNextLevel = true;
		maps.get(levelNum).onDone();
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return player;
	}

	public void reset() {
		if (onToNextLevel == true && maps.size() > levelNum + 1) {
			levelNum++;
			onToNextLevel = false;
			OGPC.changedLevel = true;
		}
		player.reset(new Vector2f(maps.get(levelNum).getPlayerStartLoc().x, maps.get(levelNum).getPlayerStartLoc().y));
		maps.get(levelNum).reset();
		runnable.stop();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		super.enter(container, game);
		blocks = ((OGPC) game).getCurrentBlockInfo();
		runnable = new RunRunnable(blocks, container, this);
		t = new Thread(runnable);
		t.start();
		gameMusic.loop();
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		super.leave(container, game);
		gameMusic.stop();
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}

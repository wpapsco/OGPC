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
	private Button returnButton;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		playerImage = new Image("pics/player.png");
		background = new Image("pics/testMap.png");
		gameMusic = new Sound("sounds/gameplay.ogg");
		returnButton = new Button(new Image("pics/returnButton.png"), new Vector2f(50, 575));
		maps = new ArrayList<Map>();
		map = new Map(new Vector2f(400, 300));
		
//		map.addObstacle(new Obstacle(new Rectangle(0, 400, 350, 200)));
//		map.addObstacle(new Obstacle(new Rectangle(0, 300, 700, 100)));
//		map.addObstacle(new Obstacle(new Rectangle(100, 100, 700, 100)));
//		
//		map.addEvent(new LocationalEvent(new Vector2f(775, 25), false));
//		map.addEvent(new EnemiesKilledEvent(false));
//		
//		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(650, 550)));
//		
//		map.setObjectiveText("Level 1: Kill the enemy or get to the end to win!");
//		maps.add(map);
//		
//		map = new Map(new Vector2f(25, 25));
//		map.addObstacle(new Obstacle(new Rectangle(400, 300, 100, 100)));
//		map.setObjectiveText("Level 2: Levels totally work now! (You can't win this one!)");
//		maps.add(map);
		
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(400, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Level one");
		maps.add(map);
		map = new Map(new Vector2f(400, 300));
		
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(400, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Level two");
		maps.add(map);
		map = new Map(new Vector2f(400, 300));
		
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(400, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Level three");
		maps.add(map);
		map = new Map(new Vector2f(400, 300));
		
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(400, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Level four");
		maps.add(map);
		map = new Map(new Vector2f(400, 300));
		
		player = new Player(new Vector2f(maps.get(OGPC.level).getPlayerStartLoc().x, maps.get(OGPC.level).getPlayerStartLoc().y), playerImage);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		//background.draw();
		player.draw(g);
		g.setColor(Color.blue);
		maps.get(OGPC.level).draw(g);
		g.setColor(Color.blue);
		maps.get(OGPC.level).drawObstacles(g);
		returnButton.draw(g);
		g.draw(new Rectangle(775, 25, 2, 2));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

		Input in = container.getInput();
		maps.get(OGPC.level).update(container, this);
		maps.get(OGPC.level).checkEnemyCollisions(player.getBullets());
		
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
		
		player.update(delta, maps.get(OGPC.level));
		
		CheckForAtWall();
		
		runnable.getCurrentBlock().update(delta, container, this);
	}

	private void CheckForAtWall() {
		
	}

	public Map getMap() {
		return maps.get(OGPC.level);
	}
	
	public void onRunComplete() {
		System.out.println("Complete");
		reset();
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return player;
	}

	public void reset() {
		player.reset(new Vector2f(maps.get(OGPC.level).getPlayerStartLoc().x, maps.get(OGPC.level).getPlayerStartLoc().y));
		maps.get(OGPC.level).reset();
		runnable.stop();
		for (int i = 0; i < maps.size(); i++) {
			if (maps.get(i).isCompleted()) {
				OGPC.completedLevels[i] = true;
			}
		}
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
		player.setLoc(new Vector2f(maps.get(OGPC.level).getPlayerStartLoc().x, maps.get(OGPC.level).getPlayerStartLoc().y));
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

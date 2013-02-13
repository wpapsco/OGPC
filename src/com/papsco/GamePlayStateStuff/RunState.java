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
		
		//tutorial levels
		map = new Map(new Vector2f(400, 350));
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(400, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Medicate the enemy!");
		maps.add(map);
		
		map = new Map(new Vector2f(300, 350));
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(450, 200)));
		map.addEvent(new EnemiesKilledEvent(false));
		map.setObjectiveText("Can you get this one?");
		maps.add(map);
		
		map = new Map(new Vector2f(400, 575));
		map.addEvent(new LocationalEvent(new Vector2f(25, 25), false));
		map.setObjectiveText("How about getting around this corner?");
		map.addObstacle(new Obstacle(new Rectangle(0, 100, 350, 500)));
		map.addObstacle(new Obstacle(new Rectangle(450, 0, 350, 600)));
		maps.add(map);
		
		map = new Map(new Vector2f(400, 575));
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(25, 25)));
		map.setObjectiveText("Now put it all together!");
		map.addObstacle(new Obstacle(new Rectangle(0, 100, 350, 500)));
		map.addObstacle(new Obstacle(new Rectangle(450, 0, 350, 600)));
		maps.add(map);
		
		//spine levels
		map = new Map(new Vector2f(45, 45), new Image("pics/LevelSpine1.png"));
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEvent(new LocationalEvent(new Rectangle(420, 500, 360, 80), false));
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(650, 45)));
		map.setObjectiveText("Spine - Level one");
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(170, 20, 20, 350)));
		map.addObstacle(new Obstacle(new Rectangle(400, 130, 20, 450)));
		map.addObstacle(new Obstacle(new Rectangle(610, 80, 20, 90)));
		map.addObstacle(new Obstacle(new Rectangle(630, 150, 150, 20)));
		maps.add(map);
		
		//Alan, please add spine level 2
		//sure thing, boss! Spine level 2:
		map = new Map(new Vector2f(105, 530), new Image("pics/LevelSpine2.png"));
		map.addEvent(new LocationalEvent(new Rectangle(640, 160, 40, 140), false));
		map.setObjectiveText("Spine - Level two");
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(640, 140, 60, 20)));
		map.addObstacle(new Obstacle(new Rectangle(680, 160, 20, 160)));
		map.addObstacle(new Obstacle(new Rectangle(180, 300, 520, 20)));
		map.addObstacle(new Obstacle(new Rectangle(180, 320, 20, 260)));
		maps.add(map);
		
		map = new Map(new Vector2f(50, 145), new Image("pics/LevelSpine3.png"));
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEvent(new LocationalEvent(new Rectangle(620, 150, 160, 100), false));
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(400, 290)));
		map.setObjectiveText("Spine - Level three");
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(200, 280, 20, 300)));
		map.addObstacle(new Obstacle(new Rectangle(220, 280, 120, 20)));
		map.addObstacle(new Obstacle(new Rectangle(340, 210, 20, 160)));
		map.addObstacle(new Obstacle(new Rectangle(340, 210, 180, 20)));
		map.addObstacle(new Obstacle(new Rectangle(340, 350, 180, 20)));
		map.addObstacle(new Obstacle(new Rectangle(600, 130, 20, 330)));
		map.addObstacle(new Obstacle(new Rectangle(600, 130, 180, 20)));
		maps.add(map);
		
		//brain levels
		map = new Map(new Vector2f(300, 145), new Image("pics/LevelBrain1.png"));
		map.addEvent(new EnemiesKilledEvent(false));
		map.addEvent(new LocationalEvent(new Rectangle(310, 290, 293, 100), false));
		map.addEnemy(new Enemy(new Image("pics/Virus.png"), new Vector2f(475, 315)));
		map.setObjectiveText("Brain - Level one");
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(0, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(0, 580, 800, 20)));
		map.addObstacle(new Obstacle(new Rectangle(780, 0, 20, 600)));
		map.addObstacle(new Obstacle(new Rectangle(600, 290, 20, 130)));
		map.addObstacle(new Obstacle(new Rectangle(470, 140, 20, 130)));
		map.addObstacle(new Obstacle(new Rectangle(290, 290, 20, 130)));
		map.addObstacle(new Obstacle(new Rectangle(110, 270, 510, 20)));
		map.addObstacle(new Obstacle(new Rectangle(110, 20, 270, 20)));
		maps.add(map);
		
		player = new Player(new Vector2f(maps.get(OGPC.level).getPlayerStartLoc().x, maps.get(OGPC.level).getPlayerStartLoc().y), playerImage);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		//background.draw();
		g.setColor(Color.blue);
		maps.get(OGPC.level).draw(g);
//		g.setColor(Color.blue);
//		maps.get(OGPC.level).drawObstacles(g);
		player.draw(g);
		returnButton.draw(g);
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

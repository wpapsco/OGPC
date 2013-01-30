package GuiItems;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.papsco.OGPC2013.OGPC;

public class LevelSelectState extends BasicGameState {

	public int stateID;

	public LevelSelectState(int stateID) {
		this.stateID = stateID;
	}
	
	public static final int TUTORIALWORLD = 0;
	public static final int SPINEWORLD = 1;
	
	private Image bgSpineImage;
	private Image bgTutorialImage;
	private Animation selectAnim;
	private ArrayList<Button> tutorialButtons;
	private ArrayList<Vector2f> tutorialLevelLocations;
	private ArrayList<Button> spineButtons;
	private ArrayList<Vector2f> spineLevelLocations;
	private int curWorld;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		spineButtons = new ArrayList<Button>();
		spineLevelLocations = new ArrayList<Vector2f>();
		tutorialButtons = new ArrayList<Button>();
		tutorialLevelLocations = new ArrayList<Vector2f>();
		bgSpineImage = new Image("pics/vertibre.png");
		bgTutorialImage = new Image("pics/TutorialLevelSelect.png");
		selectAnim = new Animation(new SpriteSheet(new Image("pics/SelectAnimation.png"), 50, 50), 100);
		
		tutorialButtons.add(new Button(new Rectangle(100, 200, 50, 50), false));
		tutorialButtons.add(new Button(new Rectangle(525, 300, 50, 50), false));
		tutorialButtons.add(new Button(new Rectangle(100, 100, 50, 50), false));
		tutorialButtons.add(new Button(new Rectangle(550, 200, 50, 50), false));
		tutorialLevelLocations.add(new Vector2f(100, 200));
		tutorialLevelLocations.add(new Vector2f(525, 300));
		tutorialLevelLocations.add(new Vector2f(100, 100));
		tutorialLevelLocations.add(new Vector2f(550, 200));
		
		spineButtons.add(new Button(new Rectangle(100, 200, 50, 50), false));
		spineButtons.add(new Button(new Rectangle(525, 300, 50, 50), false));
		spineButtons.add(new Button(new Rectangle(100, 100, 50, 50), false));
		spineButtons.add(new Button(new Rectangle(550, 200, 50, 50), false));
		spineLevelLocations.add(new Vector2f(100, 200));
		spineLevelLocations.add(new Vector2f(525, 300));
		spineLevelLocations.add(new Vector2f(100, 100));
		spineLevelLocations.add(new Vector2f(550, 200));
		
		curWorld = TUTORIALWORLD;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		if (curWorld == TUTORIALWORLD) {
			bgTutorialImage.draw();
			selectAnim.draw(tutorialLevelLocations.get(0).x, tutorialLevelLocations.get(0).y);
			for (int i = 1; i < tutorialLevelLocations.size(); i++) {
				if (OGPC.completedLevels[i - 1]) {
					selectAnim.draw(tutorialLevelLocations.get(i).x, tutorialLevelLocations.get(i).y);
				}
			}
			g.setColor(Color.white);
			g.drawString("Welcome to the School of Technological Medicine!", 0, 0);
		}
		
		if (curWorld == SPINEWORLD) {
			bgSpineImage.draw();
			selectAnim.draw(spineLevelLocations.get(0).x, spineLevelLocations.get(0).y);
			for (int i = 1; i < spineLevelLocations.size(); i++) {
				if (OGPC.completedLevels[i - 1 + 2]) {
					selectAnim.draw(spineLevelLocations.get(i).x, spineLevelLocations.get(i).y);
				}
			}
			g.setColor(Color.black);
			g.drawString("Surgery One - The Spine", 0, 0);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
		if (OGPC.completedLevels[3]) {
			curWorld = SPINEWORLD;
		}
		
		if (curWorld == TUTORIALWORLD) {
			if (tutorialButtons.get(0).isClicked(arg0.getInput())) {
				OGPC.level = 0;
				arg1.enterState(OGPC.FLOWCHARTSTATE);
			}
			for (int i = 1; i < tutorialButtons.size(); i++) {
				if (tutorialButtons.get(i).isClicked(arg0.getInput()) && OGPC.completedLevels[i - 1]) {
					OGPC.level = i;
					arg1.enterState(OGPC.FLOWCHARTSTATE);
				}
			}
		}
		
		if (curWorld == SPINEWORLD) {
			for (int i = 0; i < spineButtons.size(); i++) {
				if (spineButtons.get(i).isClicked(arg0.getInput()) && OGPC.completedLevels[i + 1]) {
					OGPC.level = i + 2;
					arg1.enterState(OGPC.FLOWCHARTSTATE);
				}
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}
	
	public void setWorld(int world) {
		this.curWorld = world;
	}
	
	public void nextWorld() {
		curWorld++;
	}
}

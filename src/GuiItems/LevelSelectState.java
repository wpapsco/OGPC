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
	public static final int BRAINWORLD = 2;
	
	private Image bgSpineImage;
	private Image bgTutorialImage;
	private Image bgBrainImage;
	private Animation selectAnim;
	private Animation finishedAnim;
	private ArrayList<Button> tutorialButtons;
	private ArrayList<Vector2f> tutorialLevelLocations;
	private ArrayList<Button> spineButtons;
	private ArrayList<Vector2f> spineLevelLocations;
	private ArrayList<Button> brainButtons;
	private ArrayList<Vector2f> brainLevelLocations;
	private int curWorld;
	
	
	
	int x = 0;
	int y = 0;
	
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		spineButtons = new ArrayList<Button>();
		spineLevelLocations = new ArrayList<Vector2f>();
		tutorialButtons = new ArrayList<Button>();
		tutorialLevelLocations = new ArrayList<Vector2f>();
		brainButtons = new ArrayList<Button>();
		brainLevelLocations = new ArrayList<Vector2f>();
		bgSpineImage = new Image("pics/vertibre.png");
		bgTutorialImage = new Image("pics/TutorialLevelSelect.png");
		bgBrainImage = new Image("pics/brain.png");
		selectAnim = new Animation(new SpriteSheet(new Image("pics/SelectAnimation.png"), 50, 50), 100);
		finishedAnim = new Animation(new SpriteSheet(new Image("pics/SelectAnimationGreen.png"),50 ,50), 100);
		
		tutorialButtons.add(new Button(new Rectangle(133 - 25, 418 - 25, 50, 50), false));
		tutorialButtons.add(new Button(new Rectangle(663 - 25, 256 - 25, 50, 50), false));
		tutorialButtons.add(new Button(new Rectangle(527 - 25, 453 - 25, 50, 50), false));
		tutorialButtons.add(new Button(new Rectangle(400 - 25, 140 - 25, 50, 50), false));
		tutorialLevelLocations.add(new Vector2f(133 - 25, 418 - 25));
		tutorialLevelLocations.add(new Vector2f(663 - 25, 256 - 25));
		tutorialLevelLocations.add(new Vector2f(527 - 25, 453 - 25));
		tutorialLevelLocations.add(new Vector2f(400 - 25, 140 - 25));
		
		spineButtons.add(new Button(new Rectangle(100, 200, 50, 50), false));
		spineButtons.add(new Button(new Rectangle(525, 300, 50, 50), false));
		spineButtons.add(new Button(new Rectangle(516 - 25, 104 - 25, 50, 50), false));
		spineLevelLocations.add(new Vector2f(100, 200));
		spineLevelLocations.add(new Vector2f(525, 300));
		spineLevelLocations.add(new Vector2f(516 - 25, 104 - 25));
		
		brainButtons.add(new Button(new Rectangle(100, 100, 50, 50), false));
		brainLevelLocations.add(new Vector2f(100, 100));
		brainLevelLocations.add(new Vector2f(200, 200));
		brainLevelLocations.add(new Vector2f(300, 300));
		
		curWorld = TUTORIALWORLD;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		if (curWorld == TUTORIALWORLD) {
			boolean animd = false;
			bgTutorialImage.draw();
			if (!OGPC.completedLevels[0]) {
				selectAnim.draw(tutorialLevelLocations.get(0).x, tutorialLevelLocations.get(0).y);
			} else {
				finishedAnim.draw(tutorialLevelLocations.get(0).x, tutorialLevelLocations.get(0).y);
			}
			for (int i = 1; i < tutorialLevelLocations.size(); i++) {
				if (OGPC.completedLevels[i]) {
					finishedAnim.draw(tutorialLevelLocations.get(i).x, tutorialLevelLocations.get(i).y);
				}
			}
			for (int i = 0; i < tutorialLevelLocations.size(); i++) {
				if (!OGPC.completedLevels[i] && !animd) {
					selectAnim.draw(tutorialLevelLocations.get(i).x, tutorialLevelLocations.get(i).y);
					animd = true;
				}
			}
			g.setColor(Color.white);
			g.drawString("Welcome to the School of Technological Medicine!", 0, 0);
		}
		
		if (curWorld == SPINEWORLD) {
			bgSpineImage.draw();
			if (!OGPC.completedLevels[4]) {
				selectAnim.draw(spineLevelLocations.get(0).x, spineLevelLocations.get(0).y);
			} else {
				finishedAnim.draw(spineLevelLocations.get(0).x, spineLevelLocations.get(0).y);
			}
			boolean animd = false;
			for (int i = 1; i < spineLevelLocations.size(); i++) {
				if (OGPC.completedLevels[i + 4]) {
					finishedAnim.draw(spineLevelLocations.get(i).x, spineLevelLocations.get(i).y);
				}
			}
			for (int i = 0; i < spineLevelLocations.size(); i++) {
				if (!OGPC.completedLevels[i + 4] && !animd) {
					selectAnim.draw(spineLevelLocations.get(i).x, spineLevelLocations.get(i).y);
					animd = true;
				}
			}
			g.setColor(Color.black);
			g.drawString("Surgery One - The Spine", 0, 0);
		}
		
		if (curWorld == BRAINWORLD) {
			bgBrainImage.draw();
			if (!OGPC.completedLevels[7]) {
				selectAnim.draw(brainLevelLocations.get(0).x, brainLevelLocations.get(0).y);
			} else {
				finishedAnim.draw(brainLevelLocations.get(0).x, brainLevelLocations.get(0).y);
			}
			boolean animd = false;
			for (int i = 1; i < brainLevelLocations.size(); i++) {
				if (OGPC.completedLevels[i + 7]) {
					finishedAnim.draw(brainLevelLocations.get(i).x, brainLevelLocations.get(i).y);
				}
			}
			for (int i = 0; i < spineLevelLocations.size(); i++) {
				if (!OGPC.completedLevels[i + 7] && !animd) {
					selectAnim.draw(brainLevelLocations.get(i).x, brainLevelLocations.get(i).y);
					animd = true;
				}
			}
			g.setColor(Color.black);
			g.drawString("Surgery Two - The brain", 0, 0);
		}
//		g.setColor(Color.black);
//		g.drawString(x + ", " + y, x + 20, y);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
		if (OGPC.completedLevels[3]) {
			curWorld = SPINEWORLD;
		}
		if (OGPC.completedLevels[6]) {
			curWorld = BRAINWORLD;
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
				if (spineButtons.get(i).isClicked(arg0.getInput()) && OGPC.completedLevels[i + 3]) {
					OGPC.level = i + 4;
					arg1.enterState(OGPC.FLOWCHARTSTATE);
				}
			}
		}
		
		if (curWorld == BRAINWORLD) {
			for (int i = 0; i < brainButtons.size(); i++) {
				if (brainButtons.get(i).isClicked(arg0.getInput()) 
						&& OGPC.completedLevels[i + 6]) {
					OGPC.level = i + 7;
					arg1.enterState(OGPC.FLOWCHARTSTATE);
				}
			}
		}
//		x = arg0.getInput().getMouseX();
//		y = arg0.getInput().getMouseY();
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

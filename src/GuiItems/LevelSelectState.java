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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.papsco.OGPC2013.OGPC;

public class LevelSelectState extends BasicGameState {

	public int stateID;

	public LevelSelectState(int stateID) {
		this.stateID = stateID;
	}
	
	private Image bgImage;
	private Animation selectAnim;
	private ArrayList<Button> Buttons;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		Buttons = new ArrayList<Button>();
		bgImage = new Image("pics/vertibre.png");
		selectAnim = new Animation(new SpriteSheet(new Image("pics/SelectAnimation.png"), 50, 50), 100);
		Buttons.add(new Button(new Rectangle(100, 200, 50, 50), false));
		Buttons.add(new Button(new Rectangle(525, 300, 50, 50), false));
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		bgImage.draw();
		selectAnim.draw(100, 200);
		if (OGPC.completedLevels[0]) {
			selectAnim.draw(525, 300);
		}
		g.setColor(Color.black);
		g.drawString("Surgery One - The Spine", 0, 0);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		if (Buttons.get(0).isClicked(arg0.getInput())) {
			OGPC.level = 0;
			arg1.enterState(OGPC.FLOWCHARTSTATE);
		}
		if (Buttons.get(1).isClicked(arg0.getInput())) {
			OGPC.level = 1;
			arg1.enterState(OGPC.FLOWCHARTSTATE);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}

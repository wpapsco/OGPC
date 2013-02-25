package GuiItems;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsState extends BasicGameState {

	int id;
	private String[] credits;
	private Color[] colors;
	private int[] randx;
	private int[] randy;
	UnicodeFont tfont;
	
	public CreditsState(int StateID) {
		id = StateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		credits = new String[4];
		credits[0] = "Credits line one";
		credits[1] = "Credits line two";
		credits[2] = "Credits line three";
		credits[3] = "Credits line four";
		
		colors = new Color[4];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Color((int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255));
		}
		
		randy = new int[4];
		randx = new int[4];
		for (int i = 0; i < randy.length; i++) {
			randy[i] = Math.round(Math.random() * 7;
		}
		
		tfont = new UnicodeFont(new Font("Arial", Font.PLAIN, 40));
		tfont.getEffects().add(new ColorEffect(java.awt.Color.white));
	    tfont.addAsciiGlyphs();
	    tfont.loadGlyphs();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		for (int i = 0; i < colors.length; i++) {
			tfont.drawString(0, i * tfont.getHeight(credits[i]), credits[i], colors[i]);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Color((int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255), (int) Math.round(Math.random() * 255));
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}

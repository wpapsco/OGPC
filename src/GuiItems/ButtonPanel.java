package GuiItems;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class ButtonPanel {
	
	private ArrayList<Button> buttons;
	private boolean isExpanded;
	Vector2f location;
	int curLocationX;
	int curLocationY;
	int buttonWidth;
	int buttonHeight;
	int width, height;
	Shape rect;
	
	public ButtonPanel(int width, int height, int buttonWidth, int buttonHeight, Vector2f location) {
		this.location = location;
		curLocationX = 0;
		curLocationY = 0;
		this.width = width;
		this.height = height;
		this.buttonHeight = buttonHeight;
		this.buttonWidth = buttonWidth;
		isExpanded = false;
		buttons = new ArrayList<Button>();
		rect = new Rectangle(location.x - 15, location.y - 15, (buttonWidth * width) + 30, (buttonHeight * height) + 30);
	}
	
	public void addButton(Button button) {
		if (curLocationX >= width) {
			curLocationX = 0;
			curLocationY++;
		}
		if (curLocationY >= height) {
			return;
		}
		button.setTopLeft(new Vector2f((curLocationX * buttonWidth) + location.x, (curLocationY * buttonHeight) + location.y)); //button.getRect().getCenterX() - (button.getRect().getWidth() / 2), button.getRect().getCenterY() - (button.getRect().getHeight() / 2))
		buttons.add(button);
		curLocationX++;
	}
	
	public void expand() {
		isExpanded = true;
	}
	
	public void contract() {
		isExpanded = false;
	}
	
	public void toggleExpansion() {
		isExpanded = !isExpanded;
	}
	
	public boolean isExpanded() {
		return isExpanded;
	}
	
	public void draw(Graphics g) {
		if (isExpanded) {
			Color c = g.getColor();
			g.setColor(new Color(153, 160, 247));
			g.fill(rect);
			g.setColor(c);
			for (int i = 0; i < buttons.size(); i++){
				buttons.get(i).draw(g);
			}
		}
	}

	public boolean isClicked(int id, Input in) {
		return buttons.get(id).isClicked(in) && isExpanded;
	}
}

package GuiItems;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Button {

	private Image image;
	private Vector2f loc;
	private Shape rect;
	private boolean prevCl;
	private boolean drawRect;
	private boolean hasImage;
	
	public Button(Image image, Vector2f loc) {
		this.loc = loc;
		this.image = image;
		rect = new Rectangle(0, 0, image.getWidth(), image.getHeight());
		rect.setCenterX(loc.x);
		rect.setCenterY(loc.y);
		drawRect = true;
		hasImage = true;
	}
	
	public Button(Rectangle rect, boolean drawRect) {
		this.rect = rect;
		this.drawRect = drawRect;
		hasImage = false;
	}
	
	public void draw(Graphics g) {
		if (hasImage) {
			image.drawCentered(loc.x, loc.y);
		}
		g.setColor(Color.blue);
		if (drawRect) {
			g.draw(rect);
		}
	}
	
	public Shape getRect() {
		return rect;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setLocation(Vector2f loc) {
		this.loc = loc;
		rect.setCenterX(loc.x);
		rect.setCenterY(loc.y);
	}
	
	public Vector2f getTopLeft() {
		return new Vector2f(loc.x - (rect.getWidth() / 2), loc.y - (rect.getHeight() / 2));
	}
	
	public void setTopLeft(Vector2f loc) {
		this.loc.x = loc.x + image.getWidth()  / 2;
		this.loc.y = loc.y + image.getHeight() / 2;
		rect.setX(loc.x);
		rect.setY(loc.y);
	}
	
	public boolean isClicked(Input in) {
		boolean a = in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		boolean b = rect.contains(in.getMouseX(), in.getMouseY());
		if (a && !prevCl && b) {
			prevCl = a;
			return true;
		}
		prevCl = a;
		return false;
	}
	public boolean isClicked(int x, int y) {
		boolean b = rect.contains(x, y);
		if (b) {
			return true;
		}
		return false;
	}
}

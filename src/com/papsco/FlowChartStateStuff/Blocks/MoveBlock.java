package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.GamePlayStateStuff.Player;
import com.papsco.GamePlayStateStuff.RunState;
import com.papsco.GamePlayStateStuff.Mapping.Map;

public class MoveBlock extends CommandBlock {
	
	private Player p;
	public static String imageString = "pics/MoveBlock.png";
	
	public static String moveUpImageString = "pics/UpBlock.png";
	public static String moveDownImageString = "pics/DownBlock.png";
	public static String moveLeftImageString = "pics/LeftBlock.png";
	public static String moveRightImageString = "pics/RightBlock.png";
	
	private Map m;
	private int dx;
	private int dy;

	public MoveBlock(Vector2f loc, GameContainer c, RunState s, int dx, int dy, int moveBlockType) throws SlickException {
		super(loc, imageString);
		// TODO Auto-generated constructor stub	
		p = s.getPlayer();
		m = s.getMap();
		this.dx = dx;
		this.dy = dy;
		commandBlockType = moveBlockType;
		if (moveBlockType == CommandBlock.MOVE_UP_BLOCK) {
			this.setImage(moveUpImageString);
		}
		if (moveBlockType == CommandBlock.MOVE_DOWN_BLOCK) {
			this.setImage(moveDownImageString);
		}
		if (moveBlockType == CommandBlock.MOVE_LEFT_BLOCK) {
			this.setImage(moveLeftImageString);
		}
		if (moveBlockType == CommandBlock.MOVE_RIGHT_BLOCK) {
			this.setImage(moveRightImageString);
		}
	}

	public void update(int delta, RunState s, GameContainer c) {
		super.update(delta, c, s);
		m = s.getMap();
	}
	
	@Override
	public void command() {
		// TODO Auto-generated method stub
		Shape r = new Rectangle(0, 0, p.getImage().getWidth(), p.getImage().getHeight());
		r.setCenterX(p.getLoc().x + dx);
		r.setCenterY(p.getLoc().y + dy);
		System.out.println(commandBlockType);
		if (!m.isColliding(r)) {
			p.setColliding(false);
			p.getLoc().x+=dx;
			p.getLoc().y+=dy;
		} else {
			p.setColliding(true);
		}
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		
	}

}

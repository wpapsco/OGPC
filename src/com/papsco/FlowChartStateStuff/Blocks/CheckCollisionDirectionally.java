package com.papsco.FlowChartStateStuff.Blocks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.papsco.FlowChartStateStuff.CommandBlock;
import com.papsco.FlowChartStateStuff.ConditionalBlock;
import com.papsco.GamePlayStateStuff.Player;
import com.papsco.GamePlayStateStuff.RunState;
import com.papsco.GamePlayStateStuff.Mapping.Map;

public class CheckCollisionDirectionally extends ConditionalBlock {

	public static String checkForewardImageString = "pics/FrontTouchingWall.png";
	public static String checkBackwardImageString = "pics/BackTouchingWall.png";
	public static String checkLeftImageString = "pics/LeftTouchingWall.png";
	public static String checkRightImageString = "pics/RightTouchingWall.png";
	public static String checkAllImageString = "pics/AtWallBlock.png";
	
	Player p;
	Map m;
	
	public CheckCollisionDirectionally(Vector2f loc, int conditionalBlockType)
			throws SlickException {
		super(loc, checkAllImageString);
		this.conditionalBlockType = conditionalBlockType;
		if (conditionalBlockType == ConditionalBlock.CHECK_FOREWARD_BLOCK) {
			this.image = new Image(checkForewardImageString); 
		}
		if (conditionalBlockType == ConditionalBlock.CHECK_BACKWARD_BLOCK) {
			this.image = new Image(checkBackwardImageString);
		}
		if (conditionalBlockType == ConditionalBlock.CHECK_LEFT_BLOCK) {
			this.image = new Image(checkLeftImageString);
		}
		if (conditionalBlockType == ConditionalBlock.CHECK_RIGHT_BLOCK) {
			this.image = new Image(checkRightImageString);
		}
		this.setImage(image);
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		if (conditionalBlockType == ConditionalBlock.CHECK_FOREWARD_BLOCK) {
			Shape r = new Rectangle(0, 0, p.getImage().getWidth(), p.getImage().getHeight());
			r.setCenterX(p.getForewardPosition(2).x);
			r.setCenterY(p.getForewardPosition(2).y);
			return m.isColliding(r);
		}
		return false;
	}
	
	@Override
	public void update(int delta, GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		super.update(delta, c, s);
		p = s.getPlayer();
		m = s.getMap();
	}

	@Override
	public void init(GameContainer c, RunState s) {
		// TODO Auto-generated method stub
		
	}

}

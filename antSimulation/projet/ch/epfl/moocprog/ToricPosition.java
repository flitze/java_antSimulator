package ch.epfl.moocprog;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;
import ch.epfl.moocprog.utils.Vec2d;

public final class ToricPosition {
	
	private Vec2d position;
	// private int worldHeight = getConfig().getInt(WORLD_HEIGHT);
	// private int worldWidth = getConfig().getInt(WORLD_WIDTH);
	

	public ToricPosition() {
		position = new Vec2d(0.0, 0.0);
	}
	
	public ToricPosition(double x, double y) {
		position = clampedPostion(x, y);
	}
	
	public ToricPosition(Vec2d vecteur) {
		position = clampedPostion(vecteur.getX(), vecteur.getY());
	}
	
	private static Vec2d clampedPostion(double x, double y) {
		if (x < 0) { 
			x += getConfig().getInt(WORLD_WIDTH);
		} else if (x >= getConfig().getInt(WORLD_WIDTH)) {
			x -= getConfig().getInt(WORLD_WIDTH);
		}
		
		if (y < 0) {
			y += getConfig().getInt(WORLD_HEIGHT);
		} else if (y >= getConfig().getInt(WORLD_HEIGHT)) {
			y -= getConfig().getInt(WORLD_HEIGHT);
		}
		return new Vec2d(x, y);
	}
	
	public ToricPosition add(ToricPosition that) {
		return new ToricPosition(position.add(that.toVec2d()));
	}
	
	public ToricPosition add(Vec2d vec) {
		return new ToricPosition(position.add(vec));
	}
	
	public Vec2d toVec2d() {
		return clampedPostion(position.getX(), position.getY());
	}
	
	public Vec2d toricVector(ToricPosition that) {
		Vec2d minVector = null;
		int[] heightArray = {-getConfig().getInt(WORLD_HEIGHT), 0, getConfig().getInt(WORLD_HEIGHT)};
		int[] widthArray = {-getConfig().getInt(WORLD_WIDTH), 0, getConfig().getInt(WORLD_WIDTH)};
		for (int heigth: heightArray) {
			for (int width: widthArray) {
				Vec2d positionVector = new Vec2d(that.toVec2d().getX() + width, that.toVec2d().getY() + heigth);
				if (minVector == null || 
					this.position.distance(positionVector) < minVector.length()) {
					minVector = positionVector.minus(this.position);
				}
					
			}
		}
		return minVector;
		
	}
	
	public double toricDistance(ToricPosition that) {
		return toricVector(that).length();
	}

	public void setPosition(Vec2d position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return position.toString();
	}

}

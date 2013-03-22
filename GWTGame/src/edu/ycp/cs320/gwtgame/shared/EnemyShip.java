package edu.ycp.cs320.gwtgame.shared;

public class EnemyShip extends Ship {
	public static final int WIDTH = 30;
	public static final int HEIGHT = 24;
	private Integer motion;
	
	public EnemyShip() {
		
	}
	
	public void setMotion(Integer motion) {
		this.motion = motion;
	}

	public Integer getMotion() {
		return motion;
	}
}

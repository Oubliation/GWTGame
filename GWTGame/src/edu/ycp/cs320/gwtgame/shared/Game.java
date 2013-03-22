package edu.ycp.cs320.gwtgame.shared;

import java.util.ArrayList;
import java.util.List;

public class Game {
	/** Width of playing field. */
	public static final int WIDTH = 640;
	
	/** Height of playing field. */
	public static final int HEIGHT = 480;
	
	private List<EnemyShip> enemyList;
	private PlayerShip player;

	public static final int EDGE = 8;
	
	public Game() {
		this.enemyList = new ArrayList<EnemyShip>();
		EnemyShip enemy = new EnemyShip();
		enemy.setX(310);
		enemy.setY(10);
		
		enemyList.add(enemy);
		
		this.player = new PlayerShip();
		player.setX(310);
		player.setY(400);
	}
	
	public List<EnemyShip> getEnemyList() {
		return enemyList;
	}
	
	public PlayerShip getPlayer() {
		return player;
	}
}

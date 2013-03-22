package edu.ycp.cs320.gwtgame.shared;

/**
 * An AI controller for {@link EnemyShip}.
 * 
 * @author David Hovemeyer
 */
public class EnemyShipController {
	
	public void update(EnemyShip enemy, Game game) {
		// Currently, we just have the enemy ship move back
		// and forth on the playing field.
		
		// See which direction the enemy is moving in
		Integer motion = enemy.getMotion();
		if (motion == null) {
			motion = 2;
			enemy.setMotion(motion);
		}

		// See if enemy ship can keep moving in its current direction
		// without reaching the edge.
		int nextX = enemy.getX() + motion;
		if (nextX < Game.EDGE || nextX + EnemyShip.WIDTH >= Game.WIDTH - Game.EDGE) {
			motion *= -1;
			enemy.setMotion(motion);
		}
		
		// Move enemy
		enemy.setX(enemy.getX() + motion);
	}
}

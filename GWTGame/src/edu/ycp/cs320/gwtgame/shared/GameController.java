package edu.ycp.cs320.gwtgame.shared;

/**
 * Controller for {@link Game} model object.
 */
public class GameController {
	private boolean movePlayerLeft, movePlayerRight;
	private EnemyShipController enemyShipAI;

	public GameController() {
		enemyShipAI = new EnemyShipController();
	}
	
	/**
	 * Update the state of the {@link Game} object.
	 * 
	 * @param game the game object
	 */
	public void updateGame(Game game) {
		// Update player ship
		if (movePlayerLeft) {
			game.getPlayer().setX(game.getPlayer().getX() - 2);
		}
		if (movePlayerRight) {
			game.getPlayer().setX(game.getPlayer().getX() + 2);
		}
		
		// Update enemy ship(s)
		for (EnemyShip enemy : game.getEnemyList()) {
			enemyShipAI.update(enemy, game);
		}
	}

	/**
	 * Called to indicate whether the player is moving left.
	 * 
	 * @param b true if player is moving left, false otherwise
	 */
	public void playerLeft(boolean b) {
		movePlayerLeft = b;
	}

	/**
	 * Called to indicate whether the player is moving right.
	 * 
	 * @param b true if player is moving right, false otherwise
	 */
	public void playerRight(boolean b) {
		movePlayerRight = b;
	}
}

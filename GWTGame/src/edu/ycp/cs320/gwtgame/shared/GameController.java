package edu.ycp.cs320.gwtgame.shared;

public class GameController {
	private boolean movePlayerLeft, movePlayerRight;
	
	public void updateGame(Game game) {
		if (movePlayerLeft) {
			game.getPlayer().setX(game.getPlayer().getX() - 2);
		}
		if (movePlayerRight) {
			game.getPlayer().setX(game.getPlayer().getX() + 2);
		}
	}

	public void playerLeft(boolean b) {
		movePlayerLeft = b;
	}

	public void playerRight(boolean b) {
		movePlayerRight = b;
	}
}

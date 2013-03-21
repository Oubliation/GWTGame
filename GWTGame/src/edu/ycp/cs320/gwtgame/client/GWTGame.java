package edu.ycp.cs320.gwtgame.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import edu.ycp.cs320.gwtgame.shared.Game;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTGame implements EntryPoint {
	private Image playerShipSprite;
	private Image enemyShipSprite;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Load sprite images
		String playerSpriteUrl = GWT.getModuleBaseForStaticFiles() + "playerShipSprite.png";
		String enemySpriteUrl = GWT.getModuleBaseForStaticFiles() + "enemyShipSprite.png";
		GWT.log("Player sprite: " + playerSpriteUrl);
		GWT.log("Enemy sprite: " + enemySpriteUrl);
		playerShipSprite = new Image(playerSpriteUrl);
		enemyShipSprite = new Image(enemySpriteUrl);

		Game game = new Game();
		GameView view = new GameView();
		view.setModel(game);
		
		RootLayoutPanel.get().add(playerShipSprite);
		RootLayoutPanel.get().add(enemyShipSprite);
		RootLayoutPanel.get().add(view);
		
		view.setPlayerShipSprite(playerShipSprite);
		view.setEnemyShipSprite(enemyShipSprite);

		view.activate();
	}
}

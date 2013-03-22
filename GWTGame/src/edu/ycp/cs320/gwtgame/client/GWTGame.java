package edu.ycp.cs320.gwtgame.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
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

		// Create Game model and view objects.
		Game game = new Game();
		GameView view = new GameView();
		view.setModel(game);
		
		// Note that the Image widgets actually need to be part of the
		// DOM tree in order to draw them on the canvas.
		// Put them in a FlowPanel (div element) that has size 0x0
		// so they will not be directly visible.
		FlowPanel imagePanel = new FlowPanel();
		imagePanel.add(playerShipSprite);
		imagePanel.add(enemyShipSprite);
		imagePanel.setSize("0px", "0px");
		RootLayoutPanel.get().add(imagePanel);

		// Add the view
		RootLayoutPanel.get().add(view);
		
		// Give the view references to the Images it will need to do its drawing.
		view.setPlayerShipSprite(playerShipSprite);
		view.setEnemyShipSprite(enemyShipSprite);

		view.activate();
	}
}

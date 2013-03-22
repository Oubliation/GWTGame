package edu.ycp.cs320.gwtgame.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;

import edu.ycp.cs320.gwtgame.shared.EnemyShip;
import edu.ycp.cs320.gwtgame.shared.Game;
import edu.ycp.cs320.gwtgame.shared.GameController;

/**
 * View for {@link Game} model class.
 */
public class GameView extends Composite {
	private Game model;
	private GameController controller;
	private Canvas buffer;
	private Context2d bufCtx;
	private Canvas canvas;
	private Context2d ctx;
	private Timer timer;
	private Image playerShipSprite;
	private Image enemyShipSprite;
	
	public GameView() {
		controller = new GameController();
		
		// A canvas needs to be in a FocusPanel if it will handle keyboard input.
		FocusPanel panel = new FocusPanel();
		panel.setSize(Game.WIDTH + "px", 480 + "px");
		
		// We use the "buffer" canvas as an off-screen drawing surface where
		// each frame is rendered.
		this.buffer = Canvas.createIfSupported();
		buffer.setSize(Game.WIDTH + "px", 480 + "px");
		buffer.setCoordinateSpaceWidth(Game.WIDTH);
		buffer.setCoordinateSpaceHeight(480);
		this.bufCtx = buffer.getContext2d();
		
		// The visible canvas: contents of buffer are copied here once
		// a frame has been rendered.
		this.canvas = Canvas.createIfSupported();
		canvas.setSize(Game.WIDTH + "px", 480 + "px");
		canvas.setCoordinateSpaceWidth(Game.WIDTH);
		canvas.setCoordinateSpaceHeight(480);
		this.ctx = canvas.getContext2d();
		panel.add(canvas);
		
		// Key handlers
		canvas.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				handleKeyDown(event);
			}
		});
		canvas.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				handleKeyUp(event);
			}
		});
		
		initWidget(panel);

		// Animation timer
		this.timer = new Timer() {
			@Override
			public void run() {
				if (model != null) {
					controller.updateGame(model);
					paint();
				}
			}
		};
	}
	
	public void setPlayerShipSprite(Image playerShipSprite) {
		this.playerShipSprite = playerShipSprite;
	}
	
	public void setEnemyShipSprite(Image enemyShipSprite) {
		this.enemyShipSprite = enemyShipSprite;
	}

	protected void handleKeyDown(KeyDownEvent event) {
		if (event.isLeftArrow()) {
			controller.playerLeft(true);
		}
		if (event.isRightArrow()) {
			controller.playerRight(true);
		}
	}

	protected void handleKeyUp(KeyUpEvent event) {
		if (event.isLeftArrow()) {
			controller.playerLeft(false);
		}
		if (event.isRightArrow()) {
			controller.playerRight(false);
		}
	}

	public void setModel(Game model) {
		this.model = model;
	}
	
	public void activate() {
		timer.scheduleRepeating(1000 / 30);
	}
	
	// Render one frame of animation.
	protected void paint() {
		// Draw onto buffer
		bufCtx.setFillStyle("black");
		bufCtx.fillRect(0, 0, Game.WIDTH, 480);
		
		bufCtx.drawImage(
				(ImageElement) playerShipSprite.getElement().cast(),
				model.getPlayer().getX(),
				model.getPlayer().getY());
		
		for (EnemyShip enemy : model.getEnemyList()) {
			bufCtx.drawImage(
					(ImageElement) enemyShipSprite.getElement().cast(),
					enemy.getX(),
					enemy.getY());
		}
		
		// Copy buffer onto main canvas
		ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);
	}
}

package edu.ycp.cs320.gwtgame.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
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
		
		FocusPanel panel = new FocusPanel();
		panel.setSize("640px", "480px");
		
		this.buffer = Canvas.createIfSupported();
		buffer.setSize("640px", "480px");
		this.bufCtx = buffer.getContext2d();
		
		this.canvas = Canvas.createIfSupported();
		canvas.setSize("640px", "480px");
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
		timer.scheduleRepeating(500);
	}
	
	protected void paint() {
		/*
		// Draw onto buffer
		bufCtx.setFillStyle("black");
		bufCtx.fillRect(0, 0, 640, 480);
		
		//bufCtx.drawImage((ImageElement) playerShipSprite.getElement().cast(), model.getPlayer().getX(), model.getPlayer().getY());
		bufCtx.setFillStyle("green");
		bufCtx.fillRect(model.getPlayer().getX(), model.getPlayer().getY(), 30, 15);
		
		for (EnemyShip enemy : model.getEnemyList()) {
			//bufCtx.drawImage((ImageElement) enemyShipSprite.getElement().cast(), enemy.getX(), enemy.getY());
			bufCtx.setFillStyle("red");
			bufCtx.fillRect(enemy.getX(), enemy.getY(), 30, 15);
		}
		
		// Copy buffer onto main canvas
		ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);
		*/
		
		//GWT.log("paint!");
		
		ctx.setFillStyle("blue");
		ctx.fillRect(0, 0, 640, 480);
		
//		ctx.setFillStyle("red");
//		ctx.fillRect(40, 40, 100, 50);

		ctx.setFillStyle("green");
		int playerX = model.getPlayer().getX();
		int playerY = model.getPlayer().getY();
		//GWT.log("Player: x=" + playerX + ", y=" + playerY);
		ctx.fillRect(playerX, playerY, 100, 50);
		/*
		//bufCtx.drawImage((ImageElement) playerShipSprite.getElement().cast(), model.getPlayer().getX(), model.getPlayer().getY());
		ctx.setFillStyle("green");
		ctx.fillRect(model.getPlayer().getX(), model.getPlayer().getY(), 30, 15);
		
		for (EnemyShip enemy : model.getEnemyList()) {
			//bufCtx.drawImage((ImageElement) enemyShipSprite.getElement().cast(), enemy.getX(), enemy.getY());
			ctx.setFillStyle("red");
			ctx.fillRect(enemy.getX(), enemy.getY(), 30, 15);
		}
		*/
	}
}

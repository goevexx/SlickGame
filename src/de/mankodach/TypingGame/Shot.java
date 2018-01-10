package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Shot {
	private float fromX;
	private float fromY;
	private float toX;
	private float toY;
	private Color color;
	private Sound shootSound;
	private boolean playShootSound;

	public Shot(float fromX, float fromY, float toX, float toY,Color color) {
		super();
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.color = color;
		this.playShootSound = true;
		try {
			shootSound = new Sound("res/shoot.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		Color gColor = g.getColor();
		g.setColor(color);
		g.drawLine(fromX, fromY, toX, toY);
		g.setColor(gColor);
	}

	public void playSound() {
		if (playShootSound) {
			shootSound.play(1, 0.1f);
			playShootSound = false;
		}
	}
}

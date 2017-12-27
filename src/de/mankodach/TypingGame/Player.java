package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Player {
	private Color color;
	private Score score;
	private int lifepoints;
	private float x;
	private float y;
	private Shoot shot;
	public Circle circ;

	public Player(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.score = new Score(0, "Score: ");
		this.lifepoints = 3;
		this.circ = new Circle(x, y, 50);
		this.shot = null;
	}

	public void draw(Graphics g) {
		Color gColor = g.getColor();
		g.setColor(color);
		g.fill(this.circ);
		g.setColor(gColor);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public int getLifepoints() {
		return lifepoints;
	}

	public void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints;
	}

	public void addLifepoints(int lifepoints) {
		this.lifepoints += lifepoints;
	}

	public void subLifepoints(int lifepoints) {
		this.lifepoints -= lifepoints;
	}

	public Shoot getShot() {
		return shot;
	}

	public void setShot(Shoot shot) {
		this.shot = shot;
	}

}

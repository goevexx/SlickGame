package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Player {
	private Color color;
	private int score;
	private int lifepoints;
	private int x;
	private int y;
	public Circle circ;

	public Player(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.score = 0;
		this.lifepoints = 3;
		this.circ = new Circle(x, y, 50);
	}

	public void draw(Graphics g) {
		Color gColor = g.getColor();
		g.setColor(color);
		g.fill(this.circ);
		g.setColor(gColor);
	}
	
	public void kill(){
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void subScore(int score) {
		this.score -= score;
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
}

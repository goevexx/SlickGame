package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Player {
	private Color color;
	private IntAttribute score;
	private IntAttribute lifepoints;
	private IntAttribute destroyedEnemies;
	private IntAttribute missed;
	private IntAttribute wpm;
	private float x;
	private float y;
	private Shot shot;
	public Circle circ;

	public Player(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.score = new IntAttribute("Score", 0);
		this.lifepoints = new IntAttribute("Lifepoints", 1);
		this.circ = new Circle(x, y, 50);
		this.shot = null;
		this.missed = new IntAttribute("Missed", 0);
		this.destroyedEnemies = new IntAttribute("Words", 0);
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

	public IntAttribute getScore() {
		return score;
	}

	public void setScore(IntAttribute score) {
		this.score = score;
	}

	public IntAttribute getLifepoints() {
		return lifepoints;
	}

	public void setLifepoints(IntAttribute lifepoints) {
		this.lifepoints = lifepoints;
	}

	public void addLifepoints(int lifepoints) {
		this.lifepoints.setInt(this.lifepoints.getInt() + lifepoints);
	}

	public void subLifepoints(int lifepoints) {
		this.lifepoints.setInt(this.lifepoints.getInt() - lifepoints);
	}

	public Shot getShot() {
		return shot;
	}

	public void setShot(Shot shot) {
		this.shot = shot;
	}

	public IntAttribute getDestroyedWords() {
		return destroyedEnemies;
	}

	public void setDestroyedWords(IntAttribute destroyedWords) {
		this.destroyedEnemies = destroyedWords;
	}

	public IntAttribute getMissed() {
		return missed;
	}

	public void setMissed(IntAttribute missed) {
		this.missed = missed;
	}
	
	public void incMissed() {
		missed.setInt(missed.getInt()+1);
	}
	
	public void incDestroyedEnemies() {
		destroyedEnemies.setInt(destroyedEnemies.getInt()+1);
	}

	public IntAttribute getWpm() {
		return wpm;
	}

	public void setWpm(IntAttribute wpm) {
		this.wpm = wpm;
	}
	
	
}

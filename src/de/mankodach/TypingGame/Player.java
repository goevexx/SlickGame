package de.mankodach.TypingGame;

import org.newdawn.slick.Color;

public class Player {

	private Color color;
	private int score;
	private int lifepoints;

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

package de.mankodach.TypingGame;

import org.newdawn.slick.Graphics;

public class Score {
	
	private int score;
	private String name;

	public Score(int score, String name) {
		super();
		this.score = score;
		this.name = name;
	}
	
	public void draw(Graphics g, float x, float y) {
		g.drawString(this.name + this.score, x, y);
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void subScore(int score) {
		this.score -= score;
	}
}

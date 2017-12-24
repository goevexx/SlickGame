package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Enemy {
	private Color color;
	private Word word;
	private int width;
	private int height;
	private Graphics g;
	public Rectangle rec;

	public Enemy(Graphics g, int x, int y, Color color, String word, int wordWidth) {
		super();
		this.color = color;
		this.g = g;
		this.word = new Word(g, word, wordWidth);
		this.width = this.word.getWidth() + 5;
		this.height = g.getFont().getHeight(this.word.getName()) + 5;
		this.rec = new Rectangle(x, y, this.width, this.height);
	}

	public void draw(float x, float y) {
		Color gColor = g.getColor();
		g.setColor(color);
		rec.setX(x);
		rec.setY(y);
		g.fill(this.rec);
		g.setColor(Color.red);
		word.draw(x + width / 2 - word.getWidth() / 2, y + height / 2 - g.getFont().getHeight(this.word.getName()) / 2);
		g.setColor(gColor);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public float getX() {
		return rec.getX();
	}

	public void setX(float x) {
		this.rec.setX(x);
	}

	public float getY() {
		return this.rec.getY();
	}

	public void setY(float f) {
		this.rec.setY(f);
	}
}

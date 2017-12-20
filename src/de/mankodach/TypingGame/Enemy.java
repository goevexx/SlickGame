package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Enemy {
	private Color color;
	private Word word;
	private int width;
	private int height;
	private int x;
	private int y;
	public Rectangle rec;

	public Enemy(int x, int y, Color color, Word word) {
		super();
		this.color = color;
		this.word = word;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g, int x, int y) {
		Color gColor = g.getColor();
		g.setColor(Color.white);
		g.fill(this.rec);
		g.setColor(Color.black);
		if (this.rec == null) {
			this.width = g.getFont().getWidth(this.word.getName()) + 10;
			this.height = g.getFont().getHeight(this.word.getName()) + 10;
			this.rec = new Rectangle(x, y, this.width, this.height);
		} else {
			rec.setX(x);
			rec.setY(y);
		}
		this.word.draw(g,
				this.x + width / 2 - g.getFont().getWidth(this.word.getName())
						/ 2,
				this.y + height / 2
						- g.getFont().getHeight(this.word.getName()) / 2);
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
}

package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Enemy {
	private Color color;
	private Word word;
	public Rectangle rec;
	private int width;
	private int height;

	public Enemy(Color color, Word word) {
		super();
		this.color = color;
		this.word = word;
		this.width = 100;
		this.height = 100;
		this.rec = new Rectangle(0,0,this.width, this.height);
	}
	
	public void draw(Graphics g){
		g.fill(this.rec);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}
}

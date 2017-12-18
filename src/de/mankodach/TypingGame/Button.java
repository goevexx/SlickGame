package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Button {
	private String text;
	private float x;
	private float y;
	private float width;
	private float height;

	private Rectangle rec;

	public Button(String text, float x, float y, float width, float height) {
		super();
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rec = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		Color gColor = g.getColor();
		g.setColor(Color.white);
		g.fill(this.rec);
		g.setColor(Color.black);
		g.drawString(this.text, this.x + width / 2 - g.getFont().getWidth(this.text) / 2,
				this.y + height / 2 - g.getFont().getHeight(this.text) / 2);
		g.setColor(gColor);
	}

	public boolean contains(float x, float y) {
		return rec.contains(x, y);
	}
}

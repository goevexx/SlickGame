package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Word {
	private String name;
	private String typed;
	private Graphics g;
	private int width;
	private int height;

	public Word(Graphics g, String name, int width, int height) {
		this.name = name;
		typed = "";
		this.g = g;
		this.width = width;
		this.height = height;
	}

	public void draw(float x, float y) {
		int stringLength = 0;
		for (int i = 0; i < this.name.length(); i++) {
			Color gColor = g.getColor();
			if (typed.length() >= i + 1)
				if (name.charAt(i) == typed.charAt(i))
					g.setColor(Color.green);
			String drawChar = Character.toString(this.name.charAt(i));
			g.drawString(drawChar, x + stringLength, y);
			stringLength += g.getFont().getWidth(drawChar);
			g.setColor(gColor);
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public String getName() {
		return name;
	}

	public String getTyped() {
		return typed;
	}

	public void setTyped(String typed) {
		this.typed = typed;
	}

	public void addTypedLetter(char letter) {
		typed += letter;
	}
}

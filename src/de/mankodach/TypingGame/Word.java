package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Word {
	private String name;
	private String typed;

	public Word(String name) {
		this.name = name;
		typed = null;
	}

	public void draw(Graphics g, int x, int y) {
		Color lColor = g.getColor();
		int lastStrinLength = 0;
		for (int i = 0; i < this.name.length(); i++) {
			g.setColor(Color.white);
			if (typed.length() >= i + 1)
				if (name.charAt(i) == typed.charAt(i))
					g.setColor(Color.green);
			String drawChar = Character.toString(this.name.charAt(i));
			g.drawString(drawChar, x + lastStrinLength, y);
			lastStrinLength = g.getFont().getWidth(drawChar) + 2;
		}
		g.setColor(lColor);
	}

	public String getName() {
		return name;
	}

	public String getTyped() {
		return typed;
	}

	public void addTypedLetter(char letter) {
		typed += letter;
	}
}

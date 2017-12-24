package de.mankodach.TypingGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Word {
	private String name;
	private String typed;
	private Graphics g;
	private int width;

	public Word(Graphics g, String name, int width) {
		this.name = name;
		typed = "";
		this.g = g;
		this.width = width;
	}

	public void draw(float f, float g2) {
		int stringLength = 0;
		for (int i = 0; i < this.name.length(); i++) {
			g.setColor(g.getColor());
			if (typed.length() >= i + 1)
				if (name.charAt(i) == typed.charAt(i))
					g.setColor(Color.green);
			String drawChar = Character.toString(this.name.charAt(i));
			g.drawString(drawChar, f + stringLength, g2);
			stringLength += g.getFont().getWidth(drawChar);
		}
	}

	public int getWidth() {
		return this.width;
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

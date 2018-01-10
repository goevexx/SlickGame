package de.mankodach.TypingGame;

import org.newdawn.slick.Graphics;

public class IntAttribute {
	private String name;
	private int intAttr;

	public IntAttribute(String name, int intAttr) {
		super();
		this.intAttr = intAttr;
		this.name = name;
	}

	public void draw(Graphics g, float x, float y) {
		g.drawString(createString(), x, y);
	}

	public String createString() {
		return this.name + ": " + this.intAttr;
	}

	public String createString(int add) {
		return this.name + ": " + (this.intAttr + add);
	}
	

	public int getInt() {
		return intAttr;
	}

	public void setInt(int intAttr) {
		this.intAttr = intAttr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void add(int add) {
		this.intAttr += add;
	}

	public void sub(int sub) {
		this.intAttr -= sub;
	}
}

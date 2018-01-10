package de.mankodach.TypingGame;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class BackgroundSound extends Sound {
	private String name;

	public BackgroundSound(String name, String ref) throws SlickException {
		super(ref);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

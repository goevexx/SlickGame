package de.mankodach.TypingGame;

public class Word {
	private String name;
	private String typed;
	
	public Word(String name) {
		this.name = name; 
		typed = null;
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

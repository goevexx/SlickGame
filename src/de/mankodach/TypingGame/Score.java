package de.mankodach.TypingGame;

public class Score {
	
	private int score;
	private String name;

	public Score(int score, String name) {
		super();
		this.score = score;
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
}

package de.mankodach.TypingGame;

public class Level {
	private BackgroundSound backgroundSound;
	private float difficulty;
	private int scoreLimit;

	public Level(BackgroundSound backgroundSound, float difficulty, int scoreLimit) {
		super();
		this.backgroundSound = backgroundSound;
		this.difficulty = difficulty;
		this.scoreLimit = scoreLimit;
	}

	public BackgroundSound getBackgroundSound() {
		return backgroundSound;
	}

	public void setBackgroundSound(BackgroundSound backgroundSound) {
		this.backgroundSound = backgroundSound;
	}

	public float getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(float difficulty) {
		this.difficulty = difficulty;
	}

	public int getScoreLimit() {
		return scoreLimit;
	}

	public void setScoreLimit(int scoreLimit) {
		this.scoreLimit = scoreLimit;
	}
}

package de.mankodach.TypingGame;

import java.util.ArrayList;

public class Scoreboard {

	private ArrayList<Score> scores;

	public void add(int score, String name) {
		Score newScore = new Score();
		newScore.score = score;
		newScore.name = name;
		this.scores.add(newScore);
	}

	public ArrayList<Score> getScores(){
		return this.scores;
	}
	
	public void loadFromFile(){
		//TODO
	}

	public void saveToFile(){
		//TODO
	}
}
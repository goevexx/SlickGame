package de.mankodach.TypingGame;

import java.util.ArrayList;

public class Scoreboard {

	public Scoreboard() {
	//public Scoreboard(String scoreBoardUrl) {
		super();
		//this.loadFromFile(scoreBoardUrl);
		this.scores = new ArrayList<Score>();
	}

	private ArrayList<Score> scores;

	public void add(int score, String name) {
		Score newScore = new Score(score,name);
		this.scores.add(newScore);
	}

	public ArrayList<Score> getScores(){
		return this.scores;
	}
	
	public void loadFromFile(String scoreBoardUrl){
		//TODO
	}

	public void saveToFile(){
		String jsonString = this.convertArrayListToString(this.scores);
		//save JSON Array to text file
	}
	
	private String convertArrayListToString(ArrayList<Score> scoresList){
		
		return "TODO";
	}
	
	private ArrayList<Score> convertStringToArrayList(String scoreString){
		return new ArrayList<Score>();
	}
	
}
package de.mankodach.TypingGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
//import org.json.*;

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
	
	public void loadFromFile(){
		//TODO
		try {
			String content = new String(Files.readAllBytes(Paths.get("Scoreboard.txt")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void saveToFile(){
		String jsonString = this.convertArrayListToString(this.scores);

		
		try {
			File file = new File("Scoreboard.txt");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(jsonString);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String convertArrayListToString(ArrayList<Score> scoresList){
		
		return "TODO";
	}
	
	private ArrayList<Score> convertStringToArrayList(String scoreString){
		return new ArrayList<Score>();
	}
	
}
package de.mankodach.TypingGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.*;

public class Scoreboard {

	private ArrayList<Score> scores;
	
	public Scoreboard() {
	//public Scoreboard(String scoreBoardUrl) {
		super();
		//this.loadFromFile(scoreBoardUrl);
		this.scores = new ArrayList<Score>();
	}

	public void add(int score, String name) {
		Score newScore = new Score(score,name);
		this.scores.add(newScore);
	}

	public ArrayList<Score> getScores(){
		return this.scores;
	}
	
	public void loadFromFile(){
		try {
			String content = new String(Files.readAllBytes(Paths.get("Scoreboard.txt")));
			
			JSONObject root = new JSONObject(content);
			
			JSONArray scores = root.getJSONArray("scores");
			
			this.scores.clear();
			this.scores = this.convertStringToArrayList(scores);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
		try 
		{	
			JSONObject root = new JSONObject();
		
			JSONArray scores = new JSONArray();
		
			for(int i = 0;i <this.scores.size(); i++)
			{
				Score aktScore = this.scores.get(i);
				JSONObject newScore = new JSONObject();
				newScore.put("score", aktScore.getScore());
				newScore.put("name", aktScore.getName());
				scores.put(newScore);
			}
			root.put("scores",scores);
		
			return root.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private ArrayList<Score> convertStringToArrayList(JSONArray scoreJSONArray){
		ArrayList<Score> scores =new ArrayList<Score>();
		
		try {
			for (int i = 0; i < scoreJSONArray.length(); i++) {
				JSONObject newScoreJSONObject = (JSONObject) scoreJSONArray.get(i);
				int scorescore = (Integer) newScoreJSONObject.get("score");
				String scorename = newScoreJSONObject.get("name").toString();
				Score newScore = new Score(scorescore,scorename);
				scores.add(newScore);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return scores;
	}
	
}
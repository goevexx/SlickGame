package de.mankodach.TypingGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.*;
import org.apache.commons.io.FileUtils;

import org.newdawn.slick.Color;

public class Settings {
	private ArrayList<Word> words;
	private Color gameColor;
	private Color enemyColor;
	private Color shotColor;
	private String jsonFileName = "typingGame.json";

	public Settings() {
		this.gameColor = Color.black;
		this.enemyColor = Color.white;
		this.shotColor = Color.cyan;

		words = new ArrayList<Word>();
		words.add(new Word("Wort"));
		words.add(new Word("AnderesWort"));
	}

	public void SaveToFile() {
		try {

			JSONObject root = new JSONObject();

			JSONArray gameColor = new JSONArray();
			gameColor.put(this.gameColor.getRed());
			gameColor.put(this.gameColor.getGreen());
			gameColor.put(this.gameColor.getBlue());
			root.put("gameColor", gameColor);

			JSONArray enemyColor = new JSONArray();
			enemyColor.put(this.enemyColor.getRed());
			enemyColor.put(this.enemyColor.getGreen());
			enemyColor.put(this.enemyColor.getBlue());
			root.put("enemyColor", enemyColor);

			JSONArray shotColor = new JSONArray();
			shotColor.put(this.shotColor.getRed());
			shotColor.put(this.shotColor.getGreen());
			shotColor.put(this.shotColor.getBlue());
			root.put("shotColor", shotColor);

			JSONArray words = new JSONArray();
			
			for (Word word : this.words)
			{
				JSONObject obj = new JSONObject();
				obj.put("name", word.getName());
				words.put(obj);
			}
			
			root.put("words", words);

			String jsonString = root.toString();

			FileWriter fileWriter = new FileWriter(System.getProperty("user.home")+ "\\" + jsonFileName);
			fileWriter.write(jsonString);
			fileWriter.flush();
			fileWriter.close();

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile() {
		
		File file = new File(System.getProperty("user.home")+ "\\" + jsonFileName);
		String content;
		try {
			content = FileUtils.readFileToString(file, "utf-8");

			JSONObject root = new JSONObject(content);

			JSONArray gameColorInts = root.getJSONArray("gameColor");
			this.gameColor = new Color(gameColorInts.getInt(0), gameColorInts.getInt(1), gameColorInts.getInt(2));

			JSONArray enemyColorInts = root.getJSONArray("enemyColor");
			this.enemyColor = new Color(enemyColorInts.getInt(0), enemyColorInts.getInt(1), enemyColorInts.getInt(2));

			JSONArray shotColorInts = root.getJSONArray("shotColor");
			this.shotColor = new Color(shotColorInts.getInt(0), shotColorInts.getInt(1), shotColorInts.getInt(2));

			JSONArray wordStrings = root.getJSONArray("words");
			
			words.clear();

			for (int i = 0; i < wordStrings.length(); i++)
			{
				JSONObject object = (JSONObject) wordStrings.get(i);
				String word = object.getString("name");
				words.add(new Word(word));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	public Color getEnemyColor() {
		return enemyColor;
	}

	public void setEnemyColor(Color enemyColor) {
		this.enemyColor = enemyColor;
	}

	public Color getShotColor() {
		return shotColor;
	}

	public void setShotColor(Color shotColor) {
		this.shotColor = shotColor;
	}

	public Color getGameColor() {
		return gameColor;
	}

	public void setGameColor(Color gameColor) {
		this.gameColor = gameColor;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}
}

package de.mankodach.TypingGame;

import java.util.ArrayList;

import org.newdawn.slick.Color;

public class Game {
	private Scoreboard score;
	private Player player;
	private Enemy activeEnemy;
	private ArrayList<Enemy> enemies;
	private Color backgroundColor;
	private ArrayList<String> words;

	public Game(Scoreboard score, Player player, Enemy activeEnemy,
			ArrayList<Enemy> enemies, Color backgroundColor,
			ArrayList<String> words) {
		super();
		this.activeEnemy = null;
		this.score = score;
		this.player = player;
		this.enemies = enemies;
		this.backgroundColor = backgroundColor;
		this.words = words;
	}

	public void start() {
		
	}

	public void end() {

	}

	public void pause() {

	}

	public void destroyEnemy(int i) {
		enemies.remove(i);
	}

	public void destroyEnemy(Enemy e) {
		enemies.remove(e);
	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public Scoreboard getScore() {
		return score;
	}

	public void setScore(Scoreboard score) {
		this.score = score;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getActiveEnemy() {
		return activeEnemy;
	}

	public void setActiveEnemy(Enemy activeEnemy) {
		this.activeEnemy = activeEnemy;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

}

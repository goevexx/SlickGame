package de.mankodach.TypingGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	private final int state;

	private Settings settings;
	private Scoreboard score;
	private Player player;
	private Enemy activeEnemy;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> words;
	private Date timestamp_spawn;
	private Date timestamp_move;
	private Random random;

	public Game(Settings settings, int state) {
		super();
		this.settings = settings;
		this.activeEnemy = null;
		this.words = settings.getWords();
		this.state = state;
		this.random = new Random();
		this.enemies=new ArrayList<Enemy>();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setMinimumLogicUpdateInterval(5);
		container.setMaximumLogicUpdateInterval(5);

		timestamp_spawn = new Date();
		timestamp_move = new Date();
		player = new Player(container.getWidth() / 2 - 25, container.getHeight(), Color.white);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		player.draw(g);
		int incHeight = 0;
		if (timestamp_move.compareTo(new Date()) < 500) {
			incHeight = 2;
			timestamp_move = new Date();
		}
		for(Enemy enemy: enemies) {
			enemy.draw(enemy.getX(), enemy.getY()+ incHeight);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (timestamp_spawn.compareTo(new Date()) < 5000) {
			enemies.add(new Enemy(container.getGraphics(), random.nextInt(container.getWidth()), -50, settings.getEnemyColor(), settings.getWords().get(random.nextInt(500))));
			timestamp_spawn = new Date();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		Word activeWord = activeEnemy.getWord();
		if (c > 64 && c < 91) {
			if (activeEnemy == null) {
				ArrayList<Enemy> possibleEnemies = new ArrayList<Enemy>();
				for (Enemy enemy : enemies) {
					Word word = enemy.getWord();
					if (word.getName().charAt(0) == c) {
						possibleEnemies.add(enemy);

						for (Enemy possibleEnemy : possibleEnemies) {

						}
					}
				}
			}
			activeWord.addTypedLetter(c);

			if (activeWord.getName() == activeWord.getTyped()) {
				destroyEnemy(activeEnemy);
				activeEnemy = null;
				// animation einfügen
			}
		}
	}

	@Override
	public int getID() {
		return this.state;
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

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

}

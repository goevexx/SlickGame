package de.mankodach.TypingGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.*;

public class TypingGame extends BasicGame {
	public static final String gamename = "Crazy typing Game!";
	private GameContainer container;
	private Scoreboard scoreboard;
	private Player player;
	private Enemy activeEnemy;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> words;
	private Date timestamp_spawn;
	private Date timestamp_move;
	private Random random;

	public Game game;
	public Settings settings;

	public TypingGame() {
		super(gamename);
		this.settings = new Settings();
		this.activeEnemy = null;
		this.words = settings.getWords();
		this.random = new Random();
		this.enemies = new ArrayList<Enemy>();
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new TypingGame());
		try {
			container.setDisplayMode(1280, 720, false);
			container.setAlwaysRender(true);

			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setMinimumLogicUpdateInterval(5);
		container.setMaximumLogicUpdateInterval(5);

		this.container = container;
		timestamp_spawn = new Date();
		timestamp_move = new Date();
		player = new Player(container.getWidth() / 2, container.getHeight(), Color.white);
		enemies.add(spawnEnemey(container));
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.draw(g);
		player.getScore().draw(g, container.getWidth()
				- g.getFont().getWidth(player.getScore().getName() + player.getScore().getScore()) - 5, 5);
		g.drawString("Lifepoints: " + player.getLifepoints(),
				container.getWidth() - g.getFont().getWidth("Lifepoints: " + player.getLifepoints()) - 5,
				container.getHeight() - g.getFont().getHeight("Lifepoints: " + player.getLifepoints()) - 5);
		if (!container.isPaused()) {
			int incHeight = 0;
			if ((new Date().getTime() - timestamp_move.getTime()) > 100) {
				incHeight = 1;
				timestamp_move = new Date();
			}
			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				enemy.draw(enemy.getX(), enemy.getY() + incHeight);
				if (enemy.getY() > container.getHeight()) {
					player.subLifepoints(1);
					destroyEnemy(enemy);
				}
			}
		} else {
			g.drawString("Loose", container.getWidth() / 2 - g.getFont().getWidth("Loose") / 2,
					container.getHeight() / 2 - g.getFont().getWidth("Loose") / 2);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (player.getLifepoints() == 0) {
			container.setPaused(true);
		}
		if (!container.isPaused()) {
			if ((new Date().getTime() - timestamp_spawn.getTime()) > 2000) {
				enemies.add(spawnEnemey(container));
				timestamp_spawn = new Date();
			}
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

	public void destroyEnemy(Enemy e) {
		enemies.remove(e);
	}

	public Enemy spawnEnemey(GameContainer container) {
		String enemyWord = settings.getWords().get(random.nextInt(settings.getWords().size()));
		return new Enemy(container.getGraphics(), random.nextInt(container.getWidth() - calWordWidth(enemyWord)), 0,
				settings.getEnemyColor(), enemyWord, calWordWidth(enemyWord));
	}

	public int calWordWidth(String name) {
		int width = 0;
		for (int i = 0; i < name.length(); i++) {
			String drawChar = Character.toString(name.charAt(i));
			width += this.container.getGraphics().getFont().getWidth(drawChar);
		}
		return width;
	}
}

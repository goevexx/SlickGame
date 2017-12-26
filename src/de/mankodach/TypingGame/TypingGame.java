package de.mankodach.TypingGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.*;

public class TypingGame extends BasicGame {
	private GameContainer container;
	private Sound backgroundSound0;
	private Sound backgroundSound1;
	private Sound backgroundSound2;
	private Sound backgroundSound3;
	private Scoreboard scoreboard;
	private Player player;
	private Enemy activeEnemy;
	private Enemy enemyToKill;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> words;
	private Date timestamp_spawn;
	private Date timestamp_move;
	private Random random;
	private int currentDifficulty;
	private final float[] difficulties = { 1, 0.75f, 0.675f, 0.6f };

	public static final String gamename = "Crazy typing Game!";
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
			container.setDisplayMode(720, 720, false);
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
		backgroundSound0 = new Sound("res/backgroundSound0.ogg");
		backgroundSound1 = new Sound("res/backgroundSound1.ogg");
		backgroundSound2 = new Sound("res/backgroundSound2.ogg");
		backgroundSound3 = new Sound("res/backgroundSound3.ogg");
		backgroundSound0.loop();
		currentDifficulty = 0;
		timestamp_spawn = new Date();
		timestamp_move = new Date();
		player = new Player(container.getWidth() / 2, container.getHeight(), Color.white);
		spawnEnemey();
		enemyToKill = null;
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
			if ((new Date().getTime() - timestamp_move.getTime()) >= 15 * difficulties[currentDifficulty]) {
				incHeight = 1;
				timestamp_move = new Date();
			}
			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				enemy.draw(enemy.getX(), enemy.getY() + incHeight);
				if (enemy.getY() > container.getHeight()) {
					enemyToKill = enemy;
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
			if (enemyToKill != null) {
				player.subLifepoints(1);
				destroyEnemy(enemyToKill);
				enemyToKill = null;
			}
			if ((new Date().getTime() - timestamp_spawn.getTime()) >= 3650 * difficulties[currentDifficulty]) {
				spawnEnemey();
				timestamp_spawn = new Date();
			}
			if (player.getScore().getScore() >= 25 && currentDifficulty == 0) {
				currentDifficulty = 1;
				backgroundSound0.stop();
				backgroundSound1.loop();
				System.out.println(currentDifficulty);
			} else if (player.getScore().getScore() >= 50 && currentDifficulty == 1) {
				currentDifficulty = 2;
				backgroundSound1.stop();
				backgroundSound2.loop();
				System.out.println(currentDifficulty);
			} else if (player.getScore().getScore() >= 100 && currentDifficulty == 2) {
				currentDifficulty = 3;
				backgroundSound2.stop();
				backgroundSound3.loop();
				System.out.println(currentDifficulty);
			}
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		if (c == 13) {
			if (activeEnemy != null) {
				activeEnemy.getWord().setTyped("");
				activeEnemy = null;
			}
		} else {
			if (activeEnemy == null) {
				ArrayList<Enemy> candidates = new ArrayList<Enemy>();
				for (Enemy enemy : enemies) {
					Word word = enemy.getWord();
					if (word.getName().charAt(0) == c) {
						candidates.add(enemy);
					}
				}
				if (candidates.size() > 0) {
					activeEnemy = candidates.get(0);
					for (Enemy candidate : candidates) {
						if (candidate.getY() > activeEnemy.getY())
							activeEnemy = candidate;
					}
				}
			}
			if (activeEnemy != null) {
				Word activeWord = activeEnemy.getWord();
				if (activeWord.getName().length() > activeWord.getTyped().length()) {
					if (activeWord.getName().charAt(activeWord.getTyped().length()) == c) {
						activeWord.addTypedLetter(c);
					}
				}
				if (activeWord.getName().equals(activeWord.getTyped())) {
					int scoreAdd = ((int) (activeWord.getName().length() / 5) > 1)
							? (int) (activeWord.getName().length() / 4)
							: 1;
					player.getScore().addScore(scoreAdd);
					destroyEnemy(activeEnemy);
					// animation einfügen
				}
			}
		}
		// }
	}

	public void destroyEnemy(Enemy e) {
		if (activeEnemy == e)
			activeEnemy = null;
		enemies.remove(e);
	}

	public void spawnEnemey() {
		String enemyWord = words.get(random.nextInt(settings.getWords().size()));
		enemies.add(new Enemy(container.getGraphics(), random.nextInt(container.getWidth() - calWordWidth(enemyWord)),
				0, settings.getEnemyColor(), enemyWord, calWordWidth(enemyWord), calWordHeight(enemyWord)));
	}

	public int calWordWidth(String name) {
		int width = 0;
		for (int i = 0; i < name.length(); i++) {
			String drawChar = Character.toString(name.charAt(i));
			width += this.container.getGraphics().getFont().getWidth(drawChar);
		}
		return width;
	}

	public int calWordHeight(String name) {
		int maxStrHeight = 0;
		for (int i = 0; i < name.length(); i++) {
			int currentStrHeight = container.getGraphics().getFont().getHeight(name.substring(i, i + 1));
			if (currentStrHeight > maxStrHeight) {
				maxStrHeight = currentStrHeight;
			}
		}
		return maxStrHeight;
	}
}
